create database pokemon;
use pokemon;

create table misPokemon(
	id int auto_increment primary key,
    numeroPokedex int not null,
    nombre varchar(50) not null,
    tipo varchar(50) not null,
    nivel int default 1
);

create table ataques(
	id int auto_increment primary key, 
    nombre varchar(50) not null,
    tipo varchar(50) not null,
    dañoBase decimal(4,2)
);

create table pokedex(
	numeroPokedex int not null primary key,
    nombre varchar(50) not null,
    tipo varchar(25) not null,
    descripcion varchar (70)
);

alter table pokedex 
add column idPreEvolucion int after numeroPokedex, 
add column idEvolucion int after idPreEvolucion;

select * from pokemon.pokedex;