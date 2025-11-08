-- =============================================
-- Script: 02_datos_catalogo.sql
-- Descripción: Inserta datos iniciales de catálogos
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla CATALOGO...'

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'CARTELERA', 'CART-2025-01', 'Cartelera actual - Películas en exhibición', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'PROXIMO_ESTRENO', 'PROX-2025-01', 'Próximos estrenos del mes', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'RECOMENDADO', 'RECO-2025-01', 'Películas recomendadas por la crítica', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'FAMILIAR', 'FAMI-2025-01', 'Películas aptas para toda la familia', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'ESPECIAL', 'ESPE-2025-01', 'Ciclo especial de películas clásicas', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'PREVENTA', 'PREV-2025-01', 'Películas en preventa', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'MATINEE', 'MATI-2025-01', 'Funciones matinée con descuento', 1);

INSERT INTO CATALOGO (id_catalogo, tipo, codigo, descripcion, activo) VALUES 
(seq_catalogo.NEXTVAL, 'NOCTURNO', 'NOCT-2025-01', 'Funciones nocturnas especiales', 1);

COMMIT;

PROMPT 'Datos insertados en CATALOGO: 8 registros'
PROMPT '============================================='