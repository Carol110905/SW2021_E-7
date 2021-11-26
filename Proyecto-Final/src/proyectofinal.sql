


CREATE TABLE `examen` (
  `idExamen` int(255) NOT NULL,
  `Titulo` text NOT NULL,
  `IdMaestro` int(255) NOT NULL,
  `Cantidad` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




CREATE TABLE 'RelacionUE' (
  'idExamen' int(255) NOT NULL,
  'idUsuarios' int(255) NOT NULL,

    FOREIGN KEY(idExamen) REFERENCES examen(idExamen),
    FOREIGN KEY(idUsuarios) REFERENCES usuarios(idUsuario));
)
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maestros`
--

CREATE TABLE `maestros` (
  `idMaestro` int(255) NOT NULL,
  `Username` int(250) NOT NULL,
  `Password` int(250) NOT NULL,
  `Nombre` int(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `NoPregunta` int(255) NOT NULL,
  `IdExamen` int(255) NOT NULL,
  `Pregunta` text NOT NULL,
  `VideoPregunta` mediumblob NOT NULL,
  `Respuesta` int(255) NOT NULL,
  `VideoRespuesta` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(255) NOT NULL,
  `Username` text NOT NULL,
  `Password` text NOT NULL,
  `Nombre` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `Username`, `Password`, `Nombre`) VALUES
(1, 'Joaquin01', '123456', 'Joaquin Alberto');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
