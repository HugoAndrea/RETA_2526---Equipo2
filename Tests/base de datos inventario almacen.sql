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
	numInforme int primary key auto_increment not null,
    tipo enum('inventario','por categoria','localizacion') not null
);

create table categoria(
	idCategoria int primary key auto_increment not null,
    tipo enum('PC´s practicas','componentes hardware','equipos de red','cableado estructurado','herramientas','material fungible') not null
);

-- create table PCsPracticas(

-- );