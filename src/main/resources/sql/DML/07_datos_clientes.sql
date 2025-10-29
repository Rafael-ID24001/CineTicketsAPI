-- =============================================
-- Script: 07_datos_clientes.sql
-- Descripción: Inserta datos iniciales de clientes
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla CLIENTE...'

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Juan Carlos Pérez', 'juan.perez@email.com', '7812-3456', SYSTIMESTAMP - INTERVAL '30' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'María Elena Rodríguez', 'maria.rodriguez@email.com', '7823-4567', SYSTIMESTAMP - INTERVAL '25' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Roberto Antonio López', 'roberto.lopez@email.com', '7834-5678', SYSTIMESTAMP - INTERVAL '20' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Ana Patricia García', 'ana.garcia@email.com', '7845-6789', SYSTIMESTAMP - INTERVAL '18' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Carlos Eduardo Martínez', 'carlos.martinez@email.com', '7856-7890', SYSTIMESTAMP - INTERVAL '15' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Sofía Isabel Hernández', 'sofia.hernandez@email.com', '7867-8901', SYSTIMESTAMP - INTERVAL '12' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Luis Fernando González', 'luis.gonzalez@email.com', '7878-9012', SYSTIMESTAMP - INTERVAL '10' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Carmen Beatriz Díaz', 'carmen.diaz@email.com', '7889-0123', SYSTIMESTAMP - INTERVAL '8' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Miguel Ángel Ramírez', 'miguel.ramirez@email.com', '7890-1234', SYSTIMESTAMP - INTERVAL '7' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Laura Cristina Torres', 'laura.torres@email.com', '7801-2345', SYSTIMESTAMP - INTERVAL '5' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Diego Alejandro Flores', 'diego.flores@email.com', '7812-3457', SYSTIMESTAMP - INTERVAL '4' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Valeria Stephanie Cruz', 'valeria.cruz@email.com', '7823-4568', SYSTIMESTAMP - INTERVAL '3' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Javier Ernesto Morales', 'javier.morales@email.com', '7834-5679', SYSTIMESTAMP - INTERVAL '2' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Gabriela Andrea Reyes', 'gabriela.reyes@email.com', '7845-6780', SYSTIMESTAMP - INTERVAL '1' DAY);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Fernando José Castro', 'fernando.castro@email.com', '7856-7891', SYSTIMESTAMP);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Daniela Paola Ortiz', 'daniela.ortiz@email.com', '7867-8902', SYSTIMESTAMP);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Andrés Felipe Mendoza', 'andres.mendoza@email.com', '7878-9013', SYSTIMESTAMP);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Isabella Mariana Silva', 'isabella.silva@email.com', '7889-0124', SYSTIMESTAMP);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Ricardo Enrique Vargas', 'ricardo.vargas@email.com', '7890-1235', SYSTIMESTAMP);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono, fecha_registro) VALUES 
(seq_cliente.NEXTVAL, 'Camila Alejandra Ruiz', 'camila.ruiz@email.com', '7801-2346', SYSTIMESTAMP);

COMMIT;

PROMPT 'Datos insertados en CLIENTE: 20 registros'
PROMPT '============================================='