-- =============================================
-- Script: 01_datos_genero.sql
-- Descripción: Inserta datos iniciales de géneros cinematográficos
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla GENERO...'

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Acción', 'Películas con secuencias de acción, peleas y persecuciones');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Comedia', 'Películas diseñadas para hacer reír al público');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Drama', 'Películas que se centran en el desarrollo de personajes y conflictos emocionales');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Terror', 'Películas diseñadas para asustar y provocar miedo');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Ciencia Ficción', 'Películas sobre futuros imaginarios, tecnología avanzada y viajes espaciales');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Romance', 'Películas centradas en historias de amor y relaciones románticas');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Thriller', 'Películas de suspenso y misterio que mantienen al espectador en tensión');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Aventura', 'Películas con exploraciones, viajes y descubrimientos emocionantes');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Animación', 'Películas creadas mediante técnicas de animación');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Fantasía', 'Películas con elementos mágicos y mundos imaginarios');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Musical', 'Películas donde los personajes cantan y bailan como parte de la narrativa');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Documental', 'Películas que documentan la realidad con propósitos educativos o informativos');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Western', 'Películas ambientadas en el Viejo Oeste estadounidense');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Crimen', 'Películas centradas en actividades criminales y su investigación');

INSERT INTO GENERO (id_genero, nombre, descripcion) VALUES 
(seq_genero.NEXTVAL, 'Bélico', 'Películas sobre guerras y conflictos militares');

COMMIT;

PROMPT 'Datos insertados en GENERO: 15 registros'
PROMPT '============================================='