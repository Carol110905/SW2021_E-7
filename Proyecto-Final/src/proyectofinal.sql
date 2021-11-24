-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-11-2021 a las 19:29:44
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectofinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `examen`
--

CREATE TABLE `examen` (
  `idExamen` int(255) NOT NULL,
  `Titulo` text NOT NULL,
  `IdMaestro` int(255) NOT NULL,
  `Cantidad` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maestros`
--

CREATE TABLE `maestros` (
  `id` int(255) NOT NULL,
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
  `id` int(255) NOT NULL,
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
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
