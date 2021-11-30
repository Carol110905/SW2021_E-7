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

create table if not exists Materia(
IdMateria int(10) not null auto_increment,
Nombre text(25) not null,
IdProfesor int(10) not null,
primary key (IdMateria),
FOREIGN KEY (IdProfesor) REFERENCES Profesor(IdProfesor) 
);


create table if not exists cursa(
IdMateria int(10) not null auto_increment,
IdAlumno int(10) not null,
FOREIGN KEY (IdMateria) REFERENCES Materia(IdMateria),
FOREIGN key (IdAlumno) REFERENCES Alumno(IdAlumno)
);