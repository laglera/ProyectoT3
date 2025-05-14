-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Crear la tabla de películas
CREATE TABLE IF NOT EXISTS peliculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    año INT,
    genero VARCHAR(100),
    sinopsis TEXT
);

-- Insertar 50 películas de prueba
INSERT INTO peliculas (titulo, director, año, genero, sinopsis) VALUES
('Interstellar', 'Christopher Nolan', 2014, 'Ciencia Ficción', 'Un viaje interestelar para salvar a la humanidad.'),
('El Señor de los Anillos', 'Peter Jackson', 2001, 'Fantasía', 'Una épica aventura para destruir el Anillo Único.'),
('Titanic', 'James Cameron', 1997, 'Drama', 'Historia de amor en el trágico viaje del Titanic.'),
('Inception', 'Christopher Nolan', 2010, 'Ciencia Ficción', 'Un ladrón experto en infiltrarse en los sueños para robar secretos.'),
('Forrest Gump', 'Robert Zemeckis', 1994, 'Drama', 'La vida de Forrest Gump, un hombre que presencia eventos históricos clave.'),
('The Dark Knight', 'Christopher Nolan', 2008, 'Acción', 'Batman lucha contra el caos desatado por el Joker en Gotham City.'),
('Pulp Fiction', 'Quentin Tarantino', 1994, 'Crimen', 'Historias interconectadas llenas de crimen y humor negro.'),
('Avatar', 'James Cameron', 2009, 'Ciencia Ficción', 'Un soldado explora el mundo de Pandora y su cultura extraterrestre.'),
('El padrino', 'Francis Ford Coppola', 1972, 'Crimen', 'Historia de la familia Corleone y su ascenso en el mundo de la mafia.'),
('La La Land', 'Damien Chazelle', 2016, 'Musical', 'Historia de amor entre un pianista de jazz y una actriz en Los Ángeles.'),
('Gladiador', 'Ridley Scott', 2000, 'Acción', 'Un general romano busca venganza contra el emperador que destruyó su vida.'),
('Toy Story', 'John Lasseter', 1995, 'Animación', 'Las aventuras de Woody y Buzz en un mundo donde los juguetes cobran vida.'),
('Star Wars: Episodio IV', 'George Lucas', 1977, 'Ciencia Ficción', 'La lucha de la Alianza Rebelde contra el Imperio liderado por Darth Vader.'),
('Matrix', 'Lana Wachowski y Lilly Wachowski', 1999, 'Ciencia Ficción', 'Un hacker descubre la verdad sobre la realidad y el mundo que lo rodea.'),
('El club de la pelea', 'David Fincher', 1999, 'Drama', 'Un hombre atrapado en su monótona vida encuentra una salida en un club secreto.'),
('Django Unchained', 'Quentin Tarantino', 2012, 'Western', 'Un esclavo liberado busca salvar a su esposa con la ayuda de un cazarrecompensas.'),
('Parásitos', 'Bong Joon-ho', 2019, 'Drama', 'Dos familias de diferentes clases sociales se entrelazan en una historia impactante.'),
('El gran Gatsby', 'Baz Luhrmann', 2013, 'Drama', 'Un escritor se adentra en el mundo glamuroso y trágico de Gatsby.'),
('Jurassic Park', 'Steven Spielberg', 1993, 'Aventura', 'La reapertura de un parque temático con dinosaurios lleva al caos absoluto.'),
('Harry Potter y la piedra filosofal', 'Chris Columbus', 2001, 'Fantasía', 'Un niño descubre que es mago y entra al mundo de Hogwarts.'),
('El sexto sentido', 'M. Night Shyamalan', 1999, 'Terror', 'Un niño puede ver y hablar con los muertos, revelando una impactante verdad.'),
('It', 'Andy Muschietti', 2017, 'Terror', 'Un grupo de niños enfrenta a un mal ancestral que toma forma de payaso.'),
('Rocky', 'John G. Avildsen', 1976, 'Drama', 'Un boxeador sube de nivel y se enfrenta a un reto histórico en su carrera.'),
('Piratas del Caribe: La maldición del Perla Negra', 'Gore Verbinski', 2003, 'Aventura', 'El capitán Jack Sparrow busca recuperar su barco y derrotar una maldición.'),
('Vengadores: Endgame', 'Anthony y Joe Russo', 2019, 'Acción', 'Los Vengadores buscan revertir las acciones de Thanos y restaurar el universo.'),
('Deadpool', 'Tim Miller', 2016, 'Acción', 'Un exsoldado con habilidades de regeneración se convierte en un antihéroe.'),
('El Rey León', 'Rob Minkoff y Roger Allers', 1994, 'Animación', 'Un joven león debe reclamar su lugar como rey de la sabana africana.'),
('Coco', 'Lee Unkrich', 2017, 'Animación', 'Un niño viaja al mundo de los muertos para descubrir su verdadera historia familiar.'),
('Wall-E', 'Andrew Stanton', 2008, 'Animación', 'Un pequeño robot en una Tierra devastada descubre el significado del amor.'),
('Up', 'Pete Docter', 2009, 'Animación', 'Un anciano y un niño viajan en una casa flotante para cumplir un sueño de vida.'),
('Los increíbles', 'Brad Bird', 2004, 'Animación', 'Una familia de superhéroes lucha contra un villano que amenaza el mundo.'),
('Joker', 'Todd Phillips', 2019, 'Drama', 'La transformación de Arthur Fleck en el temido villano Joker.'),
('El laberinto del fauno', 'Guillermo del Toro', 2006, 'Fantasía', 'Una niña descubre un mundo mágico en plena posguerra española.'),
('Interestelar', 'Christopher Nolan', 2014, 'Ciencia Ficción', 'Exploradores viajan a través de un agujero de gusano en busca de un nuevo hogar.'),
('Gravity', 'Alfonso Cuarón', 2013, 'Ciencia Ficción', 'Dos astronautas intentan sobrevivir tras un accidente en el espacio.'),
('Logan', 'James Mangold', 2017, 'Acción', 'El último viaje de Wolverine en un futuro distópico.'),
('Kill Bill Vol. 1', 'Quentin Tarantino', 2003, 'Acción', 'Una asesina busca venganza contra quienes intentaron matarla.'),
('Hacia rutas salvajes', 'Sean Penn', 2007, 'Drama', 'Un joven abandona todo para vivir en la naturaleza de Alaska.'),
('La forma del agua', 'Guillermo del Toro', 2017, 'Fantasía', 'Una mujer muda se enamora de una criatura acuática capturada en un laboratorio.'),
('El exorcista', 'William Friedkin', 1973, 'Terror', 'Una niña es poseída por una entidad demoníaca y un sacerdote intenta salvarla.'),
('El resplandor', 'Stanley Kubrick', 1980, 'Terror', 'Un hombre enloquece en un hotel aislado mientras su familia lucha por sobrevivir.'),
('Los juegos del hambre', 'Gary Ross', 2012, 'Ciencia Ficción', 'Una joven participa en un juego mortal donde solo uno puede sobrevivir.'),
('Blade Runner 2049', 'Denis Villeneuve', 2017, 'Ciencia Ficción', 'Un agente especial descubre secretos que pueden cambiar el futuro de la humanidad.');

