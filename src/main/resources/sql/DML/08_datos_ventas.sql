-- =============================================
-- Script: 08_datos_ventas.sql
-- Descripción: Inserta datos iniciales de ventas
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla VENTA...'

-- Ventas del cliente 1 (Juan Carlos Pérez)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 1, SYSTIMESTAMP - INTERVAL '5' DAY, 15.00, 'TARJETA');

INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 1, SYSTIMESTAMP - INTERVAL '2' DAY, 13.00, 'EFECTIVO');

-- Ventas del cliente 2 (María Elena Rodríguez)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 2, SYSTIMESTAMP - INTERVAL '4' DAY, 21.00, 'TARJETA');

-- Ventas del cliente 3 (Roberto Antonio López)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 3, SYSTIMESTAMP - INTERVAL '3' DAY, 28.00, 'TRANSFERENCIA');

-- Ventas del cliente 4 (Ana Patricia García)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 4, SYSTIMESTAMP - INTERVAL '3' DAY, 17.00, 'DIGITAL');

-- Ventas del cliente 5 (Carlos Eduardo Martínez)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 5, SYSTIMESTAMP - INTERVAL '2' DAY, 25.50, 'TARJETA');

-- Ventas del cliente 6 (Sofía Isabel Hernández)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 6, SYSTIMESTAMP - INTERVAL '2' DAY, 20.00, 'EFECTIVO');

-- Ventas del cliente 7 (Luis Fernando González)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 7, SYSTIMESTAMP - INTERVAL '1' DAY, 16.00, 'TARJETA');

-- Ventas del cliente 8 (Carmen Beatriz Díaz)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 8, SYSTIMESTAMP - INTERVAL '1' DAY, 30.00, 'TARJETA');

-- Ventas del cliente 9 (Miguel Ángel Ramírez)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 9, SYSTIMESTAMP - INTERVAL '1' DAY, 15.00, 'EFECTIVO');

-- Ventas del cliente 10 (Laura Cristina Torres)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 10, SYSTIMESTAMP, 23.00, 'DIGITAL');

-- Ventas del cliente 11 (Diego Alejandro Flores)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 11, SYSTIMESTAMP, 12.00, 'TARJETA');

-- Ventas del cliente 12 (Valeria Stephanie Cruz)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 12, SYSTIMESTAMP, 14.00, 'EFECTIVO');

-- Ventas del cliente 13 (Javier Ernesto Morales)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 13, SYSTIMESTAMP, 26.00, 'TARJETA');

-- Ventas del cliente 14 (Gabriela Andrea Reyes)
INSERT INTO VENTA (id_venta, id_cliente, fecha_venta, total, metodo_pago) VALUES 
(seq_venta.NEXTVAL, 14, SYSTIMESTAMP, 16.00, 'DIGITAL');

COMMIT;