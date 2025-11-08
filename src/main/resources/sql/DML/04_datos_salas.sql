-- =============================================
-- Script: 04_datos_salas.sql
-- Descripción: Inserta datos iniciales de salas de cine
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla SALA_DE_CINE...'

-- Salas Regulares
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 1', 120, 'REGULAR', 'DISPONIBLE');

INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 2', 120, 'REGULAR', 'DISPONIBLE');

INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 3', 100, 'REGULAR', 'DISPONIBLE');

-- Salas 3D
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 4 - 3D', 150, '3D', 'DISPONIBLE');

INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 5 - 3D', 150, '3D', 'DISPONIBLE');

-- Salas VIP
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala VIP 1', 50, 'VIP', 'DISPONIBLE');

INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala VIP 2', 60, 'VIP', 'DISPONIBLE');

-- Sala IMAX
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala IMAX', 200, 'IMAX', 'DISPONIBLE');

-- Sala 4DX
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 4DX', 80, '4DX', 'DISPONIBLE');

-- Sala en mantenimiento
INSERT INTO SALA_DE_CINE (id_sala, nombre, capacidad_total, tipo_sala, estado) VALUES 
(seq_sala_de_cine.NEXTVAL, 'Sala 6', 100, 'REGULAR', 'MANTENIMIENTO');

COMMIT;

PROMPT 'Datos insertados en SALA_DE_CINE: 10 registros'
PROMPT '============================================='