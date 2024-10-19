create database pokemon;
use pokemon;

create table pokemon(
	id int auto_increment primary key,
    numeroPokedex int not null,
    entrenador int,
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

create table entrenador(
	id int primary key auto_increment,
    nombre varchar(50) not null,
    edad int,
    genero varchar(10)
);

create table mochila(
	id int auto_increment primary key,
    espacio int,
    pokelas int, 
    caramelos int, 
    posiones int
);

/* Agregar columna a pokemones */
alter table pokemon
add column entrenador int after numeroPokedex;

alter table pokemon 
add column vida double after nombre,
add column experiencia double after vida;


/* agregar evoluciones en pokedex */ 
alter table pokedex 
add column idPreEvolucion int after numeroPokedex, 
add column idEvolucion int after idPreEvolucion;

/* agregar columna mochila a entrenador */ 
alter table entrenador
add column mochila int not null;

/* relacion pokemon - entrenador */ 
alter table pokemon
add constraint foreign key (entrenador) references entrenador(id);

/* relacion pokemon - pokedex */
alter table pokemon
add constraint foreign key (numeroPokedex) references pokedex(numeroPokedex);

/* relacion entrenador - mochila */ 
alter table entrenador
add constraint foreign key (mochila) references mochila(id);

alter table pokemon
add column vida double,
add column experiencia double;

select * from pokemon.pokedex;