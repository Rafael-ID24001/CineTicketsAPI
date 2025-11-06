-- =============================================
-- Script: 04_crear_indices.sql
-- Descripción: Crea índices para optimizar consultas frecuentes
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

-- =============================================
-- Índices para la tabla PELICULA
-- =============================================
-- =============================================
-- Índices para la tabla PELICULA
-- =============================================
-- Índice para búsquedas por título
CREATE INDEX idx_pelicula_titulo ON PELICULA(titulo);

-- Índice para búsquedas por fecha de estreno
CREATE INDEX idx_pelicula_fecha_estreno ON PELICULA(fecha_estreno);

-- Índice para búsquedas por estado
CREATE INDEX idx_pelicula_estado ON PELICULA(estado);

-- Índice para FK de género
CREATE INDEX idx_pelicula_genero ON PELICULA(id_genero);

-- =============================================
-- Índices para la tabla FUNCION
-- =============================================
-- Índice para búsquedas por fecha/hora
CREATE INDEX idx_funcion_fecha_hora ON FUNCION(fecha_hora);

-- Índice para búsquedas por estado
CREATE INDEX idx_funcion_estado ON FUNCION(estado);

-- Índice compuesto para búsquedas por película y sala
CREATE INDEX idx_funcion_pelicula_sala ON FUNCION(id_pelicula, id_sala);

-- Índice para FK de sala
CREATE INDEX idx_funcion_sala ON FUNCION(id_sala);

-- =============================================
-- Índices para la tabla ASIENTO
-- =============================================
-- Índice para FK de sala
CREATE INDEX idx_asiento_sala ON ASIENTO(id_sala);

-- Índice para búsquedas por estado
CREATE INDEX idx_asiento_estado ON ASIENTO(estado);

-- Índice compuesto para búsquedas de asientos disponibles por sala
CREATE INDEX idx_asiento_sala_estado ON ASIENTO(id_sala, estado);

-- =============================================
-- Índices para la tabla BOLETO
-- =============================================
-- Índice para FK de función
CREATE INDEX idx_boleto_funcion ON BOLETO(id_funcion);

-- Índice para FK de cliente
CREATE INDEX idx_boleto_cliente ON BOLETO(id_cliente);

-- Índice para FK de asiento
CREATE INDEX idx_boleto_asiento ON BOLETO(id_asiento);

-- Índice para búsquedas por fecha de compra
CREATE INDEX idx_boleto_fecha_compra ON BOLETO(fecha_compra);

-- Índice para búsquedas por estado
CREATE INDEX idx_boleto_estado ON BOLETO(estado);

-- =============================================
-- Índices para la tabla VENTA
-- =============================================
-- Índice para FK de cliente
CREATE INDEX idx_venta_cliente ON VENTA(id_cliente);

-- Índice para búsquedas por fecha de venta
CREATE INDEX idx_venta_fecha ON VENTA(fecha_venta);

-- Índice para búsquedas por método de pago
CREATE INDEX idx_venta_metodo_pago ON VENTA(metodo_pago);

-- =============================================
-- Índices para la tabla CLIENTE
-- =============================================
-- Índice para búsquedas por nombre
CREATE INDEX idx_cliente_nombre ON CLIENTE(nombre);

-- Índice para búsquedas por fecha de registro
CREATE INDEX idx_cliente_fecha_registro ON CLIENTE(fecha_registro);

-- =============================================
-- Índices para la tabla CATALOGO
-- =============================================
-- Índice para búsquedas por tipo
CREATE INDEX idx_catalogo_tipo ON CATALOGO(tipo);

-- Índice para búsquedas por estado activo
CREATE INDEX idx_catalogo_activo ON CATALOGO(activo);

-- =============================================
-- Índices para la tabla SALA_DE_CINE
-- =============================================
-- Índice para búsquedas por tipo de sala
CREATE INDEX idx_sala_tipo ON SALA_DE_CINE(tipo_sala);

-- Índice para búsquedas por estado
CREATE INDEX idx_sala_estado ON SALA_DE_CINE(estado);

-- =============================================
-- Índices para tablas intermedias
-- =============================================
-- Índices adicionales para PELICULA_CATALOGO
CREATE INDEX idx_pc_catalogo ON PELICULA_CATALOGO(id_catalogo);
CREATE INDEX idx_pc_fecha_agregado ON PELICULA_CATALOGO(fecha_agregado);

-- Índices adicionales para BOLETO_VENTA
CREATE INDEX idx_bv_venta ON BOLETO_VENTA(id_venta);

