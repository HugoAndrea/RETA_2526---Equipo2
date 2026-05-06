create database if not exists inventario;
create table perfil(
	id int primary key auto_increment null,
	tipo enum('administrador','profesor') not null
);

create table administrador(
	id int primary key auto_increment not null,
	nombre varchar(30) not null,
	contrasenia varchar(50) not null
);

create table profesor(
	id int primary key auto_increment not null,
	nombre varchar(30) not null,
	contrasenia varchar(50) not null
);

create table vistas(
	numVista int primary key auto_increment not null,
    nombre varchar(30) not null
);

create table taller(
	id int primary key auto_increment not null,
	nombre varchar(30) not null
);

create table informe(
	numero int primary key auto_increment not null,
    tipo enum('inventario','por categoria','localizacion') not null
);

create table categoria(
	id int primary key auto_increment not null,
    tipo enum('PC´s practicas','componentes hardware','equipos de red','cableado estructurado','herramientas','material fungible') not null
);

create table PCsPracticas(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);

create table componentesHardware(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);

create table equiposRed(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);

create table cableadoEstructurado(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);

create table herramientas(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);

create table materialFungible(
	id int primary key auto_increment not null,
    nombre varchar(30) not null,
    descripcion varchar(100) not null,
    estado boolean not null,
    cantidad int not null,
    codigoBalda varchar(30) not null,
    fechaAlta datetime not null,
    observaciones varchar(100)
);