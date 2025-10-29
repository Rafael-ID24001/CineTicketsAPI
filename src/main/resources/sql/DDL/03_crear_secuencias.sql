-- =============================================
-- Script: 03_crear_secuencias.sql
-- Descripción: Crea las secuencias para generar IDs automáticamente
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

-- =============================================
-- Secuencia para GENERO
-- =============================================
-- Secuencia para generar IDs de géneros
CREATE SEQUENCE seq_genero
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para CATALOGO
-- =============================================
-- Secuencia para generar IDs de catálogos
CREATE SEQUENCE seq_catalogo
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para PELICULA
-- =============================================
-- Secuencia para generar IDs de películas
CREATE SEQUENCE seq_pelicula
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para SALA_DE_CINE
-- =============================================
-- Secuencia para generar IDs de salas
CREATE SEQUENCE seq_sala_de_cine
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para ASIENTO
-- =============================================
-- Secuencia para generar IDs de asientos
CREATE SEQUENCE seq_asiento
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para FUNCION
-- =============================================
-- Secuencia para generar IDs de funciones
CREATE SEQUENCE seq_funcion
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para CLIENTE
-- =============================================
-- Secuencia para generar IDs de clientes
CREATE SEQUENCE seq_cliente
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para VENTA
-- =============================================
-- Secuencia para generar IDs de ventas
CREATE SEQUENCE seq_venta
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- =============================================
-- Secuencia para BOLETO
-- =============================================
-- Secuencia para generar IDs de boletos
CREATE SEQUENCE seq_boleto
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;