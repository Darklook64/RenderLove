/*Script de la base de datos de mySql*/ 
create database bd_renderPop;
use bd_renderPop;

create table chica(
    id int auto_increment,
    nombre varchar(50),
    edad int,
    ocupacion varchar(50),
    primary key(id)
);

create table medidorPassion(
	id int auto_increment,
	passion int,
    idChica int,
    primary key(id),
    foreign key(idChica) references chica(id)

);


create table medidorAtraccion(
	id int auto_increment,
    atraccion int,
    idChica int,
    primary key(id),
    foreign key(idChica) references chica(id)
);

create table medidorDiversion(
	id int auto_increment,
    diversion int,
    idChica int,
    primary key(id),
    foreign key(idChica) references chica(id)

);

insert into chica values(null,'Abie Bet',19,'Estudiante');
insert into chica values(null,'Katherine',22,'Cantante');

insert into medidorPassion values(null,0,1);
insert into medidorAtraccion values(null,0,1);
insert into medidorDiversion values(null,10,1);

insert into medidorPassion values(null,0,2);
insert into medidorAtraccion values(null,0,2);
insert into medidorDiversion values(null,10,2);


select * from chica;

select * from medidorAtraccion where idChica='1';
update medidorPassion set passion='0';