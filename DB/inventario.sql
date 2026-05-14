create database if not exists inventario;-- Creación de la base de datos si no existe ya,
                                         -- así se evitan duplicaciones en el sistema
use inventario;


create table usuario(-- Personal que accede al sistema
	idUsuario int primary key auto_increment not null,-- Autoincrement para que genere 
                                                      -- IDs automáticamente cada vez que 
                                                      -- se inserte una fila nueva
    nombre varchar(50) not null,
    contrasenia varchar(25) not null,
    rol enum('administrador','profesor')
);

alter table usuario -- Aumenté los caracteres que puede tener las contraseñas para permitir 
                    -- la encriptación de estas.
modify contrasenia varchar(255);


create table visitas(-- Control de accesos al sistema, permite saber quién ha accedidio y cuando.
	numVisita int primary key auto_increment not null,
    fecha date not null,
    idUsuario int,
    foreign key(idUsuario) references usuario(idUsuario)-- llave foranea que conecta esta 
                                                        -- tabla con la de usuarios.
);



create table informe(-- Guarda partes, averías e incidencias redactadas por los usuarios.
	numInforme int primary key auto_increment not null,
    fecha date not null,
    descripcion varchar(100) not null,
    idUsuario int,
	foreign key(idUsuario) references usuario(idUsuario)
);


create table categoria(-- Categoría de los objetos
	idCategoria int primary key auto_increment not null,
    nombre varchar(50) not null
);

create table armario(-- Cada uno de los armarios del taller
	idArmario int primary key auto_increment not null,
	nombre varchar(50) not null
);

create table balda(-- Cada balda de los armarios
	idBalda int primary key auto_increment not null,
	numBalda int not null,-- redundante
	idArmario int,
	foreign key(idArmario) references armario(idArmario),
	CHECK (numBalda > 0 and numBalda < 25) -- no puede haber haber balda 0
                                           -- ni balda 25 porque no existen
);

create table ubicacion(-- Ubicación del armario
	idUbicacion int primary key auto_increment not null,
	posicion varchar(25) not null,
	idBalda int,
	foreign key(idBalda) references balda(idBalda)-- Revisar esta FK
);

create table objetoInventario(-- Almacena los objetos que se guardan en los armarios
	idObjetoInventario int primary key auto_increment not null,
	nombre varchar(30) not null,
	idUbicacion int,
	idCategoria int,
	foreign key (idUbicacion) references ubicacion(idUbicacion),
	foreign key (idCategoria) references categoria(idCategoria)
);

alter table objetoinventario -- Columnas añadidas a objetoInventario
add column descripcion varchar(200),
add column cantidad int not null default 1,
add column fechaAlta date not null,
add column observaciones varchar(200);


create table estado(-- estado de los objetos
	idEstado int primary key auto_increment not null,
	nombre varchar(30) not null
);

create table historialEstado(-- Historial de estados de objetos
	idHistorialEstado int primary key auto_increment not null,
	fecha date not null,
	idObjetoInventario int,
	idEstado int,
	idUsuario int,
	foreign key(idObjetoInventario) references objetoInventario(idObjetoInventario),
	foreign key(idEstado) references estado(idEstado),
	foreign key(idUsuario) references usuario(idUsuario)
);



create table movimiento(-- movimientos de los objetos de los armarios
	idMovimiento int primary key auto_increment not null,
	tipo enum('entrada','salida','prestamo','devolucion') not null,
	cantidad int not null,
	fecha date not null,
	idUsuario int,
	idObjetoInventario int,
	foreign key(idObjetoInventario) references objetoInventario(idObjetoInventario),
	foreign key(idUsuario) references usuario(idUsuario),
	CHECK (cantidad > 0)
);

-- TRIGGERS

-- 1 Cuando se crea un objeto, se registra automáticamente su estado inicial como 'Disponible' (ID 1).
DELIMITER $$-- Este comando sirve para cambiar el caracter que separa las sentencias,
             -- en este caso cambio el ; por $$.alter
             -- esto lo hago para definir un bloque de código que contiene varios ; dentro,
             -- de esta manera se ejecutará como debe.
CREATE TRIGGER tr_objeto_inventario_alta
AFTER INSERT ON objetoInventario
FOR EACH ROW
BEGIN
    INSERT INTO historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
    VALUES (NEW.fechaAlta, NEW.idObjetoInventario, 1, NULL);
END$$

DELIMITER ;

-- 2 Incrementa o decrementa la columna 'cantidad' de la tabla 'objetoInventario' según el tipo de movimiento insertado.

DELIMITER $$ 

CREATE TRIGGER tr_actualizar_cantidad_inventario
AFTER INSERT ON movimiento
FOR EACH ROW
BEGIN
    IF NEW.tipo IN ('entrada', 'devolucion') THEN
        UPDATE objetoInventario
        SET cantidad = cantidad + NEW.cantidad
        WHERE idObjetoInventario = NEW.idObjetoInventario;
    ELSEIF NEW.tipo IN ('salida', 'prestamo') THEN
        UPDATE objetoInventario
        SET cantidad = cantidad - NEW.cantidad
        WHERE idObjetoInventario = NEW.idObjetoInventario;
    END IF;
END$$

DELIMITER ;


-- Vincula directamente movimientos con la tabla de historial de estados.

DELIMITER $$

CREATE TRIGGER tr_actualizar_estado_por_movimiento
AFTER INSERT ON movimiento
FOR EACH ROW
BEGIN
    -- Si es un préstamo, cambia el estado a 'Prestado' (idEstado = 2)
    IF NEW.tipo = 'prestamo' THEN
        INSERT INTO historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
        VALUES (NEW.fecha, NEW.idObjetoInventario, 2, NEW.idUsuario);
        
    -- Si es una devolución, cambia el estado a 'Disponible' (idEstado = 1)
    ELSEIF NEW.tipo = 'devolucion' THEN
        INSERT INTO historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
        VALUES (NEW.fecha, NEW.idObjetoInventario, 1, NEW.idUsuario);
    END IF;
END$$

DELIMITER ;