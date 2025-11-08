-- =============================================
-- Script: 09_datos_boletos.sql
-- Descripción: Inserta datos iniciales de boletos y sus relaciones con ventas
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin
-- NOTA: Este script asume que ya se ejecutaron los scripts de funciones, clientes, ventas y asientos

PROMPT 'Insertando datos en la tabla BOLETO y BOLETO_VENTA...'

-- =============================================
-- Venta 1: Cliente 1, 2 boletos para función 1
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 1, 1, 5, SYSDATE - 5, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (1, 1, 7.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 1, 1, 6, SYSDATE - 5, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (2, 1, 7.50);

-- =============================================
-- Venta 2: Cliente 1, 2 boletos para función 2
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 2, 1, 10, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (3, 2, 6.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 2, 1, 11, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (4, 2, 6.50);

-- =============================================
-- Venta 3: Cliente 2, 3 boletos para función 7
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 7, 2, 130, SYSDATE - 4, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (5, 3, 7.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 7, 2, 131, SYSDATE - 4, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (6, 3, 7.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 7, 2, 132, SYSDATE - 4, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (7, 3, 7.00);

-- =============================================
-- Venta 4: Cliente 3, 2 boletos VIP para función 18
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 18, 3, 1050, SYSDATE - 3, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (8, 4, 14.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 18, 3, 1051, SYSDATE - 3, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (9, 4, 14.00);

-- =============================================
-- Venta 5: Cliente 4, 2 boletos para función 16
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 16, 4, 600, SYSDATE - 3, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (10, 5, 8.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 16, 4, 601, SYSDATE - 3, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (11, 5, 8.50);

-- =============================================
-- Venta 6: Cliente 5, 3 boletos para función 13
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 13, 5, 250, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (12, 6, 8.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 13, 5, 251, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (13, 6, 8.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 13, 5, 252, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (14, 6, 8.50);

-- =============================================
-- Venta 7: Cliente 6, 2 boletos para función 14
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 14, 6, 450, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (15, 7, 10.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 14, 6, 451, SYSDATE - 2, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (16, 7, 10.00);

-- =============================================
-- Venta 8: Cliente 7, 2 boletos para función 17
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 17, 7, 650, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (17, 8, 8.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 17, 7, 651, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (18, 8, 8.00);

-- =============================================
-- Venta 9: Cliente 8, 2 boletos VIP para función 19
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 19, 8, 1030, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (19, 9, 15.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 19, 8, 1031, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (20, 9, 15.00);

-- =============================================
-- Venta 10: Cliente 9, 2 boletos para función 22
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 22, 9, 1300, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (21, 10, 7.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 22, 9, 1301, SYSDATE - 1, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (22, 10, 7.50);

-- =============================================
-- Venta 11: Cliente 10, 2 boletos para función 5
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 5, 10, 900, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (23, 11, 11.50);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 5, 10, 901, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (24, 11, 11.50);

-- =============================================
-- Venta 12: Cliente 11, 1 boleto para función 23
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 23, 11, 1400, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (25, 12, 12.00);

-- =============================================
-- Venta 13: Cliente 12, 2 boletos IMAX para función 4
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 4, 12, 850, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (26, 13, 7.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 4, 12, 851, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (27, 13, 7.00);

-- =============================================
-- Venta 14: Cliente 13, 2 boletos para función 29
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 29, 13, 950, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (28, 14, 13.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 29, 13, 951, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (29, 14, 13.00);

-- =============================================
-- Venta 15: Cliente 14, 2 boletos VIP para función 20
-- =============================================
INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 20, 14, 1100, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (30, 15, 8.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, id_asiento, fecha_compra, estado) VALUES 
(seq_boleto.NEXTVAL, 20, 14, 1101, SYSDATE, 'VALIDO');

INSERT INTO BOLETO_VENTA (id_boleto, id_venta, precio_unitario) VALUES (31, 15, 8.00);

-- Actualizar estado de asientos ocupados
UPDATE ASIENTO SET estado = 'OCUPADO' WHERE id_asiento IN (
    SELECT id_asiento FROM BOLETO WHERE estado = 'VALIDO'
);

COMMIT;