-- =============================================
-- Script: 03_datos_pelicula.sql
-- Descripción: Inserta datos iniciales de películas
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla PELICULA...'

-- Películas de Acción (id_genero = 1)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Rápidos y Furiosos X', 142, 1, 'PG-13', 
'Dom Toretto y su familia deben enfrentarse a su enemigo más letal que regresa del pasado.', 
TO_DATE('2025-01-15', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Misión Imposible: Destino Final', 163, 1, 'PG-13', 
'Ethan Hunt y su equipo se enfrentan a la amenaza más peligrosa de sus carreras.', 
TO_DATE('2025-02-01', 'YYYY-MM-DD'), 'ACTIVA');

-- Películas de Comedia (id_genero = 2)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'La Boda de mi Mejor Amigo 2', 110, 2, 'PG', 
'Una comedia romántica sobre amor, amistad y segundas oportunidades.', 
TO_DATE('2025-01-20', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Vecinos en Guerra', 98, 2, 'R', 
'Dos vecinos rivales deben unirse para salvar su vecindario.', 
TO_DATE('2025-03-15', 'YYYY-MM-DD'), 'PROXIMAMENTE');

-- Películas de Drama (id_genero = 3)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'El Último Adiós', 135, 3, 'PG-13', 
'Una emotiva historia sobre familia, pérdida y redención.', 
TO_DATE('2025-01-10', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Sueños Rotos', 122, 3, 'R', 
'Un drama intenso sobre las consecuencias de decisiones del pasado.', 
TO_DATE('2025-02-20', 'YYYY-MM-DD'), 'ACTIVA');

-- Películas de Terror (id_genero = 4)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'La Casa Maldita', 105, 4, 'R', 
'Una familia se muda a una casa con un oscuro secreto.', 
TO_DATE('2025-01-25', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Pesadillas Nocturnas', 93, 4, 'R', 
'El terror se apodera de un pequeño pueblo cuando aparecen misteriosas criaturas.', 
TO_DATE('2025-04-01', 'YYYY-MM-DD'), 'PROXIMAMENTE');

-- Películas de Ciencia Ficción (id_genero = 5)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Galaxia Infinita', 155, 5, 'PG-13', 
'Una épica aventura espacial que explora los confines del universo.', 
TO_DATE('2025-02-10', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Inteligencia Artificial: Despertar', 140, 5, 'PG-13', 
'Una IA desarrolla consciencia y cuestiona su existencia.', 
TO_DATE('2025-03-01', 'YYYY-MM-DD'), 'PROXIMAMENTE');

-- Películas de Romance (id_genero = 6)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Amor en París', 115, 6, 'PG', 
'Una historia de amor que florece en la ciudad de la luz.', 
TO_DATE('2025-02-14', 'YYYY-MM-DD'), 'ACTIVA');

-- Películas de Thriller (id_genero = 7)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'El Vigilante', 128, 7, 'R', 
'Un detective persigue a un asesino serial que siempre está un paso adelante.', 
TO_DATE('2025-01-30', 'YYYY-MM-DD'), 'ACTIVA');

-- Películas de Aventura (id_genero = 8)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'El Tesoro Perdido', 138, 8, 'PG', 
'Un grupo de exploradores busca un legendario tesoro en la selva amazónica.', 
TO_DATE('2025-02-05', 'YYYY-MM-DD'), 'ACTIVA');

-- Películas de Animación (id_genero = 9)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Aventuras Mágicas', 95, 9, 'G', 
'Una aventura animada llena de magia y amistad para toda la familia.', 
TO_DATE('2025-01-18', 'YYYY-MM-DD'), 'ACTIVA');

INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'Los Guardianes del Reino', 102, 9, 'PG', 
'Héroes animales deben proteger su reino de fuerzas oscuras.', 
TO_DATE('2025-03-20', 'YYYY-MM-DD'), 'PROXIMAMENTE');

-- Películas de Fantasía (id_genero = 10)
INSERT INTO PELICULA (id_pelicula, titulo, duracion, id_genero, clasificacion, sinopsis, fecha_estreno, estado) VALUES 
(seq_pelicula.NEXTVAL, 'El Reino Encantado', 145, 10, 'PG', 
'Una joven descubre un portal a un mundo mágico lleno de criaturas fantásticas.', 
TO_DATE('2025-02-25', 'YYYY-MM-DD'), 'ACTIVA');

-- Insertar relaciones de películas con catálogos
PROMPT 'Insertando relaciones PELICULA_CATALOGO...'

-- Películas en CARTELERA (id_catalogo = 1)
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (1, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (2, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (3, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (5, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (6, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (7, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (9, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (11, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (12, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (13, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (14, 1, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (16, 1, SYSDATE);

-- Películas en PROXIMO_ESTRENO (id_catalogo = 2)
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (4, 2, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (8, 2, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (10, 2, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (15, 2, SYSDATE);

-- Películas RECOMENDADAS (id_catalogo = 3)
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (2, 3, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (5, 3, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (9, 3, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (16, 3, SYSDATE);

-- Películas FAMILIARES (id_catalogo = 4)
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (3, 4, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (13, 4, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (14, 4, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (15, 4, SYSDATE);
INSERT INTO PELICULA_CATALOGO (id_pelicula, id_catalogo, fecha_agregado) VALUES (16, 4, SYSDATE);

COMMIT;

PROMPT 'Datos insertados en PELICULA: 16 registros'
PROMPT 'Relaciones insertadas en PELICULA_CATALOGO: 29 registros'
PROMPT '============================================='