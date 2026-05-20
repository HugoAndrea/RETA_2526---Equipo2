create database if not exists inventario; /*Creación de la base de datos si no existe ya,
                                           así se evitan duplicaciones en el sistema */
use inventario;


create table usuario(-- Personal que accede al sistema
	idUsuario int primary key auto_increment not null, /*Autoincrement para que genere 
                                                      IDs automáticamente cada vez que 
                                                      se inserte una fila nueva*/
    nombre varchar(50) not null,
    contrasenia varchar(25) not null,
    rol enum('administrador','profesor')
);

alter table usuario 
modify contrasenia varchar(255); /* Aumenté los caracteres que puede tener las contraseñas para permitir 
                                    la encriptación de estas.*/

-- insert into usuario (idUsuario,nombre,contrasenia,rol) -- inserción de una fila en la tabla usuario
-- values (1,'admin','admin123','administrador');

create table visitas(-- Control de accesos al sistema, permite saber quién ha accedidio y cuando.
	numVisita int primary key auto_increment not null,
    fecha date not null,
    idUsuario int,
    foreign key(idUsuario) references usuario(idUsuario)/* llave foranea que conecta esta 
                                                         tabla con la de usuarios. */
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

insert into categoria (nombre) values ('Informatica'),
                                      ('Electronica'),
                                      ('Herramientas'),
                                      ('Consumible'),
                                      ('Red y comunicaciones');

create table armario(-- Cada uno de los armarios del taller
	idArmario int primary key auto_increment not null,
	nombre varchar(50) not null
);

insert into armario (nombre) values ('Armario A'),
                                    ('Armario B');

create table balda(-- Cada balda de los armarios
	idBalda int primary key auto_increment not null,
	numBalda int not null,
	idArmario int,
	foreign key(idArmario) references armario(idArmario),
	CHECK (numBalda > 0 and numBalda < 25) /* no puede haber haber balda 0
                                            ni balda 25 porque no existen */
);

insert into balda (numBalda, idArmario) values (1, 1),
											   (2, 1),
                                               (3, 1),
                                               (1, 2),
											   (2, 2);

create table ubicacion(-- Ubicación de la balda
	idUbicacion int primary key auto_increment not null,
	posicion varchar(25) not null,
	idBalda int,
	foreign key(idBalda) references balda(idBalda)-- Revisar esta FK
);

INSERT INTO ubicacion (posicion, idBalda) VALUES ('Izquierda',  1),
												 ('Centro',     1),
												 ('Derecha',    1),
                                                 ('Izquierda',  2),
                                                 ('Derecha',    2),
                                                 ('Izquierda',  3),
                                                 ('Derecha',    3),
                                                 ('Izquierda',  4),
                                                 ('Derecha',    4),
                                                 ('Izquierda',  5),
                                                 ('Derecha',    5);

create table objetoInventario(-- Almacena los objetos que se guardan en los armarios
	idObjetoInventario int primary key auto_increment not null,
	nombre varchar(30) not null,
	idUbicacion int,
	idCategoria int,
	foreign key (idUbicacion) references ubicacion(idUbicacion),
	foreign key (idCategoria) references categoria(idCategoria)
);

alter table objetoInventario -- Columnas añadidas a objetoInventario
add column descripcion varchar(200),
add column cantidad int not null default 1,
add column fechaAlta date not null,
add column observaciones varchar(200);


create table estado(-- estado de los objetos
	idEstado int primary key auto_increment not null,
	nombre varchar(30) not null
);

insert into estado (idEstado, nombre) VALUES (1, 'Disponible'),
                                             (2, 'Prestado'),
                                             (3, 'Averiado'),
                                             (4, 'En reparacion');

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
	check (cantidad > 0)
);

-- TRIGGERS

-- 1 Cuando se crea un objeto, se registra automáticamente su estado inicial como 'Disponible' (ID 1).

delimiter $$ /* Este comando sirve para cambiar el caracter que separa las sentencias,
              en este caso cambio el ; por $$,
              esto lo hago para definir un bloque de código que contiene varios ; dentro,
              de esta manera se ejecutará como debe.*/
              
create trigger tr_objeto_inventario_alta -- Nombre del trigger
after insert on objetoInventario -- El trigger se ejecutará al insertar un objeto en la tabla objetoInventario
for each row -- El código se ejecutará individualmente para cada fila insertada.
begin -- Aquí empiezan las instrucciones que se van a aplicar cuando salte el trigger.
    insert into historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
    values (new.fechaAlta, new.idObjetoInventario, 1, null);
end$$ -- Aquí acaba el bloque de código del trigger

delimiter ; /*Vuelvo a cambiar el caracter para cerrar sentencias al que está por defecto,
             podría hacer esto después de todos los triggers, pero prefiero encapsular 
             cada uno para evitar posibles errores.*/

-- 2 Incrementa o decrementa la columna 'cantidad' de la tabla 'objetoInventario' según el tipo de movimiento insertado.

delimiter $$ 

create trigger tr_actualizar_cantidad_inventario
after insert on movimiento
for each row
begin
    if new.tipo in ('entrada', 'devolucion') then /* Si el movimiento es uno de estos dos
													 que son los que incrementan el inventario*/  
        update objetoInventario -- Selecciona esta tabla para modificarla
        set cantidad = cantidad + new.cantidad /* Suma la cantidad del movimiento a la cantidad
											      actual de objetos en el inventario*/
        where idObjetoInventario = new.idObjetoInventario;/* Filtra la actualización exclusivamente para 
                                                             el artículo que generó el movimiento.*/
    elseif new.tipo in ('salida', 'prestamo') then /*Por otra parte, si el movimiento sustrae objetos
                                                     se realizará la operación inversa*/
        update objetoInventario
        set cantidad = cantidad - new.cantidad
        where idObjetoInventario = new.idObjetoInventario;
    end if;
end$$

delimiter ;


-- 3 Vincula directamente movimientos con la tabla de historial de estados.

delimiter $$

create trigger tr_actualizar_estado_por_movimiento
after insert on movimiento
for each row
begin
    
    if new.tipo = 'prestamo' then -- Si es un préstamo, cambia el estado a 'Prestado' (idEstado = 2)
        insert into historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
        values (new.fecha, new.idObjetoInventario, 2, new.idUsuario);
        
    
    elseif new.tipo = 'devolucion' then -- Si es una devolución, cambia el estado a 'Disponible' (idEstado = 1)
        insert into historialEstado (fecha, idObjetoInventario, idEstado, idUsuario)
        values (new.fecha, new.idObjetoInventario, 1, new.idUsuario);
    end if;
end$$

delimiter ;