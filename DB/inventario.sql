create database if not exists inventario;
use inventario;

create table usuario(
	idUsuario int primary key auto_increment not null,
    nombre varchar(50) not null,
    contrasenia varchar(25) not null,
    rol enum('administrador','profesor')
);

alter table usuario 
modify contrasenia varchar(255);-- modificación 1


create table visitas(-- visitas
	numVisita int primary key auto_increment not null,
    fecha date not null,
    idUsuario int,
    foreign key(idUsuario) references usuario(idUsuario),
    CHECK (fecha is not null)
);



create table informe(
	numInforme int primary key auto_increment not null,
    fecha date not null,
    descripcion varchar(100) not null,
    idUsuario int,
	foreign key(idUsuario) references usuario(idUsuario),
    CHECK (fecha is not null)
);


create table categoria(
	idCategoria int primary key auto_increment not null,
    nombre varchar(50) not null
);

create table armario(
	idArmario int primary key auto_increment not null,
	nombre varchar(50) not null
);

create table balda(
	idBalda int primary key auto_increment not null,
	numBalda int not null,
	idArmario int,
	foreign key(idArmario) references armario(idArmario),
	CHECK (numBalda > 0 and numBalda < 12) -- habrá que mirar cuantas baldas hay
);

create table ubicacion(
	idUbicacion int primary key auto_increment not null,
	posicion varchar(25) not null,
	idBalda int,
	foreign key(idBalda) references balda(idBalda)
);

create table objetoInventario(
	idObjetoInventario int primary key auto_increment not null,
	nombre varchar(30) not null,
	idUbicacion int,
	idCategoria int,
	foreign key (idUbicacion) references ubicacion(idUbicacion),
	foreign key (idCategoria) references categoria(idCategoria)
);

alter table objetoinventario
add column descripcion varchar(200),
add column cantidad int not null default 1,
add column fechaAlta date not null,
add column observaciones varchar(200);-- modificación 2

create table estado(
	idEstado int primary key auto_increment not null,
	nombre varchar(30) not null
);

create table historialEstado(
	idHistorialEstado int primary key auto_increment not null,
	fecha date not null,
	idObjetoInventario int,
	idEstado int,
	idUsuario int,
	foreign key(idObjetoInventario) references objetoInventario(idObjetoInventario),
	foreign key(idEstado) references estado(idEstado),
	foreign key(idUsuario) references usuario(idUsuario),
	CHECK (fecha is not null)
);



create table movimiento(
	idMovimiento int primary key auto_increment not null,
	tipo enum('entrada','salida','prestamo','devolucion') not null,
	cantidad int not null,
	fecha date not null,
	idUsuario int,
	idObjetoInventario int,
	foreign key(idObjetoInventario) references objetoInventario(idObjetoInventario),
	foreign key(idUsuario) references usuario(idUsuario),
	CHECK (fecha is not null),
	CHECK (cantidad > 0)
);

