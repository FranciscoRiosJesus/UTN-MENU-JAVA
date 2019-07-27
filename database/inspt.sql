-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2018 a las 05:35:51
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inspt`
--
CREATE DATABASE IF NOT EXISTS `inspt` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `inspt`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `legajo` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellido` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`legajo`, `nombre`, `apellido`) VALUES
(1, 'Marvin', 'The Paranoid Android'),
(2, 'Slartibartfast', 'Magrathean'),
(4, 'Zaphod', 'Beeblebrox'),
(5, 'Ford', 'Prefect'),
(6, 'Tricia', 'McMillan'),
(7, 'Francisco', 'Rios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos_materias`
--

CREATE TABLE `alumnos_materias` (
  `legajo` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `id_profesor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `alumnos_materias`
--

INSERT INTO `alumnos_materias` (`legajo`, `id_materia`, `id_profesor`) VALUES
(4, 12, 3),
(2, 1, 1),
(1, 1, 1),
(1, 2, 3),
(5, 6, 1),
(4, 3, 2),
(1, 3, 1),
(4, 1, 1),
(5, 1, 1),
(6, 13, 4),
(2, 2, 3),
(1, 2, 1),
(4, 12, 2),
(2, 13, 4),
(1, 13, 4),
(4, 2, 1),
(4, 13, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correlativas`
--

CREATE TABLE `correlativas` (
  `llave` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `id_matcorrelativa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `correlativas`
--

INSERT INTO `correlativas` (`llave`, `id_materia`, `id_matcorrelativa`) VALUES
(1, 5, 1),
(2, 6, 2),
(4, 13, 2),
(5, 13, 6),
(6, 14, 14),
(7, 16, 14),
(8, 17, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `id_materia` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`id_materia`, `nombre`) VALUES
(1, 'Analisis Matematico I'),
(2, 'Programacion I'),
(3, 'Ingles Tecnico I'),
(5, 'Analisis Matematico II'),
(6, 'Programacion II'),
(12, 'Economia II'),
(13, 'Programacion III'),
(14, 'Base de Datos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `id_profesor` int(11) NOT NULL,
  `nombre_apellido` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`id_profesor`, `nombre_apellido`) VALUES
(1, 'Benjamin Sisko'),
(2, 'Keiko O\'Brien'),
(3, 'Odo'),
(4, 'Gul Dukat'),
(5, 'Monica Khun');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores_materias`
--

CREATE TABLE `profesores_materias` (
  `id_profesor` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `llave` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `profesores_materias`
--

INSERT INTO `profesores_materias` (`id_profesor`, `id_materia`, `llave`) VALUES
(1, 2, 1),
(1, 1, 2),
(2, 3, 3),
(1, 5, 10),
(1, 3, 11),
(3, 2, 12),
(3, 12, 13),
(3, 5, 14),
(2, 12, 15),
(1, 6, 16),
(4, 5, 18),
(4, 13, 19),
(2, 2, 20),
(1, 13, 21),
(5, 6, 22);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`legajo`),
  ADD KEY `legajo` (`legajo`);

--
-- Indices de la tabla `alumnos_materias`
--
ALTER TABLE `alumnos_materias`
  ADD KEY `legajo` (`legajo`);

--
-- Indices de la tabla `correlativas`
--
ALTER TABLE `correlativas`
  ADD PRIMARY KEY (`llave`),
  ADD KEY `id_materia` (`id_materia`),
  ADD KEY `id_materia_2` (`id_materia`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`id_materia`),
  ADD KEY `id_materia` (`id_materia`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`id_profesor`),
  ADD KEY `id_profesor` (`id_profesor`);

--
-- Indices de la tabla `profesores_materias`
--
ALTER TABLE `profesores_materias`
  ADD PRIMARY KEY (`llave`),
  ADD KEY `id_profesor` (`id_profesor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `legajo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `correlativas`
--
ALTER TABLE `correlativas`
  MODIFY `llave` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `id_materia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `profesores`
--
ALTER TABLE `profesores`
  MODIFY `id_profesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `profesores_materias`
--
ALTER TABLE `profesores_materias`
  MODIFY `llave` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
