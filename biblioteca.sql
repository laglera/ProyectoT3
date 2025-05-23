-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2025 a las 10:25:20
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `id` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `director` varchar(255) DEFAULT NULL,
  `año` int(11) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `sinopsis` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id`, `titulo`, `director`, `año`, `genero`, `sinopsis`) VALUES
(2, 'El Señor de los Anillos', 'Peter Jackson', 2001, 'Fantasía', 'Una épica aventura para destruir el Anillo Único.'),
(3, 'Titanic', 'James Cameron', 1997, 'Drama', 'Historia de amor en el trágico viaje del Titanic.'),
(4, 'Inception', 'Christopher Nolan', 2010, 'Ciencia Ficción', 'Un ladrón experto en infiltrarse en los sueños para robar secretos.'),
(5, 'Forrest Gump', 'Robert Zemeckis', 1994, 'Drama', 'La vida de Forrest Gump, un hombre que presencia eventos históricos clave.'),
(6, 'The Dark Knight', 'Christopher Nolan', 2008, 'Acción', 'Batman lucha contra el caos desatado por el Joker en Gotham City.'),
(7, 'Pulp Fiction', 'Quentin Tarantino', 1994, 'Crimen', 'Historias interconectadas llenas de crimen y humor negro.'),
(8, 'Avatar', 'James Cameron', 2009, 'Ciencia Ficción', 'Un soldado explora el mundo de Pandora y su cultura extraterrestre.'),
(9, 'El padrino', 'Francis Ford Coppola', 1972, 'Crimen', 'Historia de la familia Corleone y su ascenso en el mundo de la mafia.'),
(10, 'La La Land', 'Damien Chazelle', 2016, 'Musical', 'Historia de amor entre un pianista de jazz y una actriz en Los Ángeles.'),
(11, 'Gladiator', 'Ridley Scott', 2000, 'Acción', 'Un general romano busca venganza contra el emperador que destruyó su vida.'),
(12, 'Toy Story', 'John Lasseter', 1995, 'Animación', 'Las aventuras de Woody y Buzz en un mundo donde los juguetes cobran vida.'),
(13, 'Star Wars Episodio IV', 'George Lucas', 1977, 'Ciencia Ficción', 'La lucha de la Alianza Rebelde contra el Imperio liderado por Darth Vader.'),
(14, 'Matrix', 'Lana Wachowski y Lilly Wachowski', 1999, 'Ciencia Ficción', 'Un hacker descubre la verdad sobre la realidad y el mundo que lo rodea.'),
(15, 'El club de la lucha', 'David Fincher', 1999, 'Drama', 'Un hombre atrapado en su monótona vida encuentra una salida en un club secreto.'),
(16, 'Django Unchained', 'Quentin Tarantino', 2012, 'Western', 'Un esclavo liberado busca salvar a su esposa con la ayuda de un cazarrecompensas.'),
(17, 'Parásitos', 'Bong Joon-ho', 2019, 'Drama', 'Dos familias de diferentes clases sociales se entrelazan en una historia impactante.'),
(18, 'El gran Gatsby', 'Baz Luhrmann', 2013, 'Drama', 'Un escritor se adentra en el mundo glamuroso y trágico de Gatsby.'),
(19, 'Jurassic Park', 'Steven Spielberg', 1993, 'Aventura', 'La reapertura de un parque temático con dinosaurios lleva al caos absoluto.'),
(20, 'Harry Potter y la piedra filosofal', 'Chris Columbus', 2001, 'Fantasía', 'Un niño descubre que es mago y entra al mundo de Hogwarts.'),
(21, 'El sexto sentido', 'M. Night Shyamalan', 1999, 'Terror', 'Un niño puede ver y hablar con los muertos, revelando una impactante verdad.'),
(22, 'It', 'Andy Muschietti', 2017, 'Terror', 'Un grupo de niños enfrenta a un mal ancestral que toma forma de payaso.'),
(23, 'Rocky', 'John G. Avildsen', 1976, 'Drama', 'Un boxeador sube de nivel y se enfrenta a un reto histórico en su carrera.'),
(24, 'Piratas del Caribe La maldición del Perla Negra', 'Gore Verbinski', 2003, 'Aventura', 'El capitán Jack Sparrow busca recuperar su barco y derrotar una maldición.'),
(25, 'Vengadores Endgame', 'Anthony y Joe Russo', 2019, 'Acción', 'Los Vengadores buscan revertir las acciones de Thanos y restaurar el universo.'),
(26, 'Deadpool', 'Tim Miller', 2016, 'Acción', 'Un exsoldado con habilidades de regeneración se convierte en un antihéroe.'),
(27, 'El Rey León', 'Rob Minkoff y Roger Allers', 1994, 'Animación', 'Un joven león debe reclamar su lugar como rey de la sabana africana.'),
(28, 'Coco', 'Lee Unkrich', 2017, 'Animación', 'Un niño viaja al mundo de los muertos para descubrir su verdadera historia familiar.'),
(29, 'Wall-E', 'Andrew Stanton', 2008, 'Animación', 'Un pequeño robot en una Tierra devastada descubre el significado del amor.'),
(30, 'Up', 'Pete Docter', 2009, 'Animación', 'Un anciano y un niño viajan en una casa flotante para cumplir un sueño de vida.'),
(31, 'Los increíbles', 'Brad Bird', 2004, 'Animación', 'Una familia de superhéroes lucha contra un villano que amenaza el mundo.'),
(32, 'Joker', 'Todd Phillips', 2019, 'Drama', 'La transformación de Arthur Fleck en el temido villano Joker.'),
(33, 'El laberinto del fauno', 'Guillermo del Toro', 2006, 'Fantasía', 'Una niña descubre un mundo mágico en plena posguerra española.'),
(34, 'Interestelar', 'Christopher Nolan', 2014, 'Ciencia Ficción', 'Exploradores viajan a través de un agujero de gusano en busca de un nuevo hogar.'),
(35, 'Gravity', 'Alfonso Cuarón', 2013, 'Ciencia Ficción', 'Dos astronautas intentan sobrevivir tras un accidente en el espacio.'),
(36, 'Logan', 'James Mangold', 2017, 'Acción', 'El último viaje de Wolverine en un futuro distópico.'),
(37, 'Kill Bill Vol 1', 'Quentin Tarantino', 2003, 'Acción', 'Una asesina busca venganza contra quienes intentaron matarla.'),
(38, 'Hacia rutas salvajes', 'Sean Penn', 2007, 'Drama', 'Un joven abandona todo para vivir en la naturaleza de Alaska.'),
(39, 'La forma del agua', 'Guillermo del Toro', 2017, 'Fantasía', 'Una mujer muda se enamora de una criatura acuática capturada en un laboratorio.'),
(40, 'El exorcista', 'William Friedkin', 1973, 'Terror', 'Una niña es poseída por una entidad demoníaca y un sacerdote intenta salvarla.'),
(41, 'El resplandor', 'Stanley Kubrick', 1980, 'Terror', 'Un hombre enloquece en un hotel aislado mientras su familia lucha por sobrevivir.'),
(42, 'Los juegos del hambre', 'Gary Ross', 2012, 'Ciencia Ficción', 'Una joven participa en un juego mortal donde solo uno puede sobrevivir.'),
(43, 'Blade Runner 2049', 'Denis Villeneuve', 2017, 'Ciencia Ficción', 'Un agente especial descubre secretos que pueden cambiar el futuro de la humanidad.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `Contraseña` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `Nombre`, `Correo`, `Telefono`, `Contraseña`) VALUES
(1, 'ale', 'ale@gmail.com', '1212321212', 'asdf'),
(4, 'ale2', 'ale2@gmail.com', '6969696969', '1234'),
(5, 'aleproye', 'ajp0020@alu.medac.es', '666999666', '1234'),
(6, 'admin', 'admin@gmail.com', '666 666 666', '1234'),
(7, 'JUAN', 'JUAN@MEDAC.COM', '333333333', '1234'),
(8, 'prueba', 'prueba@gmail.com', '666666666', '1234'),
(10, 'prueba', 'prueba2@gmail.com', '666666666', '1234'),
(11, 'prueba3', 'prueba3@gmail.com', '555555555', '1234');

--
-- Disparadores `usuarios`
--
DELIMITER $$
CREATE TRIGGER `TRG_usuarios_AUDIT` AFTER INSERT ON `usuarios` FOR EACH ROW BEGIN
    INSERT INTO usuarios_log (
        usuario_id,
        fecha_hora,
        nombre_insertado
    ) VALUES (
        NEW.id,          -- Sin dos puntos
        NOW(),           -- Función de fecha/hora de MySQL
        NEW.nombre       -- Sin dos puntos
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_log`
--

CREATE TABLE `usuarios_log` (
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_hora` timestamp NOT NULL DEFAULT current_timestamp(),
  `nombre_insertado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios_log`
--

INSERT INTO `usuarios_log` (`usuario_id`, `fecha_hora`, `nombre_insertado`) VALUES
(8, '2025-05-22 08:23:15', 'prueba'),
(10, '2025-05-22 08:23:31', 'prueba'),
(11, '2025-05-22 08:24:13', 'prueba3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Correo` (`Correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
