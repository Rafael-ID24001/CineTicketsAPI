-- =============================================
-- Script: 06_datos_funciones.sql
-- Descripción: Inserta datos iniciales de funciones/horarios
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla FUNCION...'

-- =============================================
-- Funciones para HOY y próximos días
-- =============================================

-- Rápidos y Furiosos X (Película 1) - Sala 1
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 7.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8.50, 'PROGRAMADA');

-- Misión Imposible (Película 2) - Sala IMAX
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 2, 8, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 12.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 2, 8, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), 14.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 2, 8, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 22:00:00', 'YYYY-MM-DD HH24:MI:SS'), 14.00, 'PROGRAMADA');

-- La Boda de mi Mejor Amigo 2 (Película 3) - Sala 2
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 3, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 3, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 7.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 3, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8.00, 'PROGRAMADA');

-- El Último Adiós (Película 5) - Sala 3
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 5, 3, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 5, 3, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 7.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 5, 3, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 20:30:00', 'YYYY-MM-DD HH24:MI:SS'), 8.50, 'PROGRAMADA');

-- La Casa Maldita (Película 7) - Sala 4 3D
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 7, 4, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 21:00:00', 'YYYY-MM-DD HH24:MI:SS'), 10.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 7, 4, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 23:30:00', 'YYYY-MM-DD HH24:MI:SS'), 10.00, 'PROGRAMADA');

-- Galaxia Infinita (Película 9) - Sala 5 3D
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 9, 5, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 10.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 9, 5, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 11.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 9, 5, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), 11.50, 'PROGRAMADA');

-- Amor en París (Película 11) - Sala VIP 1
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 11, 6, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 15.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 11, 6, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 21:00:00', 'YYYY-MM-DD HH24:MI:SS'), 16.00, 'PROGRAMADA');

-- El Vigilante (Película 12) - Sala VIP 2
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 12, 7, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), 15.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 12, 7, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), 16.00, 'PROGRAMADA');

-- Aventuras Mágicas (Película 14) - Sala 4DX
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 9, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 12.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 9, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 12.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 9, TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 13.00, 'PROGRAMADA');

-- =============================================
-- Funciones para MAÑANA
-- =============================================

-- Rápidos y Furiosos X - Mañana
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 7.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 1, 1, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8.50, 'PROGRAMADA');

-- Galaxia Infinita - Mañana IMAX
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 9, 8, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 14.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 9, 8, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), 15.00, 'PROGRAMADA');

-- Aventuras Mágicas - Mañana
INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5.50, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6.00, 'PROGRAMADA');

INSERT INTO FUNCION (id_funcion, id_pelicula, id_sala, fecha_hora, precio_boleto, estado) VALUES 
(seq_funcion.NEXTVAL, 14, 2, TO_TIMESTAMP(TO_CHAR(SYSDATE + 1, 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 7.00, 'PROGRAMADA');

COMMIT;

PROMPT 'Datos insertados en FUNCION: 34 registros'
PROMPT '============================================='