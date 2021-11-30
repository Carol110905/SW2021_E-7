drop database proyecto_accesibilidad;
create database if not exists proyecto_accesibilidad;
use proyecto_accesibilidad;

create table if not exists Profesor(
IdProfesor int(10) Not null auto_increment,
Username text(75) not null,
pass text(75) not null,
Nombre text(75) not null,
primary key (IdProfesor)
);

create table if not exists Alumno(
IdAlumno int(10) Not null auto_increment,
Username text(75) not null,
pass text(75) not null,
Nombre text(75) not null,
primary key (IdAlumno)
);
