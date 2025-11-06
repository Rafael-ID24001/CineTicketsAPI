-- =============================================
-- Script: 01_crear_usuario.sql
-- Descripción: Crea el usuario para la base de datos del proyecto CineTicketsAPI
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como SYSTEM o un usuario con privilegios DBA

-- Eliminar el usuario si ya existe (útil para reiniciar desde cero)
BEGIN
    EXECUTE IMMEDIATE 'DROP USER cine_admin CASCADE';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -1918 THEN -- -1918 es el error "user does not exist"
            RAISE;
        END IF;
END;
/

-- Crear el usuario para el proyecto
CREATE USER cine_admin IDENTIFIED BY CineAdmin2025
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON USERS;

-- Otorgar privilegios necesarios
GRANT CONNECT TO cine_admin;
GRANT RESOURCE TO cine_admin;
GRANT CREATE SESSION TO cine_admin;
GRANT CREATE TABLE TO cine_admin;
GRANT CREATE VIEW TO cine_admin;
GRANT CREATE SEQUENCE TO cine_admin;
GRANT CREATE TRIGGER TO cine_admin;
GRANT CREATE PROCEDURE TO cine_admin;

-- Confirmar creación
COMMIT;