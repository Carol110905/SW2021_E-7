create table if not exists Profesor(
IdProfesor int(10) Not null,
Username text(75) not null,
pass text(75) not null,
Nombre text(75) not null,
primary key (IdProfesor)
);

create table if not exists Alumno(
IdAlumno int(10) Not null,
Username text(75) not null,
pass text(75) not null,
Nombre text(75) not null,
primary key (IdAlumno)
);

create table if not exists Tiene(
IdAlumno int(10) not NUll,
IdProfesor int(10) not Null,
foreign key (IdAlumno) references Alumno(IdAlumno),
foreign key (IdProfesor) references Profesor(IdProfesor)
);

create table if not exists Examen(
IdExamen int(10) not null,
IdProfesor int(10) not Null,
primary key (IdExamen),
foreign key (IdProfesor) references Profesor(IdProfesor)
);

create table if not exists Presenta(
IdExamen int(10) not Null,
IdProfesor int(10) not null,
foreign key (IdExamen) references Examen(IdExamen),
foreign key (IdProfesor) references Profesor(IdProfesor)
);

create table if not exists Preguntas(
IdPregunta int(10) not null,
IdExamen int(10) not null,
Cerradas boolean,
Abiertas boolean,
primary key (IdPregunta),
foreign key (IdExamen) references Examen(IdExamen)
);

create table if not exists Respuestas(
IdRespuestas int(10) not null,
IdPregunta int(10) not null,
Video blob,
Texto text(100),
Opcion text(100)
);
