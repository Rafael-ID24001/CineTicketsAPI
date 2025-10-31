-- =============================================
-- Script: 02_crear_tablas.sql
-- Descripción: Crea todas las tablas del sistema de venta de boletos de cine
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

-- =============================================
-- Tabla: GENERO
-- Descripción: Almacena los géneros cinematográficos
-- =============================================
CREATE TABLE GENERO (
    id_genero NUMBER(10) NOT NULL,
    nombre VARCHAR2(100) NOT NULL,
    descripcion VARCHAR2(500),
    CONSTRAINT pk_genero PRIMARY KEY (id_genero),
    CONSTRAINT uk_genero_nombre UNIQUE (nombre)
);

COMMENT ON TABLE GENERO IS 'Tabla que almacena los géneros de películas (Acción, Drama, Comedia, etc.)';
COMMENT ON COLUMN GENERO.id_genero IS 'Identificador único del género';
COMMENT ON COLUMN GENERO.nombre IS 'Nombre del género';
COMMENT ON COLUMN GENERO.descripcion IS 'Descripción del género';

-- =============================================
-- Tabla: CATALOGO
-- Descripción: Almacena catálogos de películas disponibles
-- =============================================
CREATE TABLE CATALOGO (
    id_catalogo NUMBER(10) NOT NULL,
    tipo VARCHAR2(50) NOT NULL,
    codigo VARCHAR2(20) NOT NULL,
    descripcion VARCHAR2(500),
    activo NUMBER(1) DEFAULT 1 NOT NULL,
    CONSTRAINT pk_catalogo PRIMARY KEY (id_catalogo),
    CONSTRAINT uk_catalogo_codigo UNIQUE (codigo),
    CONSTRAINT chk_catalogo_activo CHECK (activo IN (0, 1))
);

COMMENT ON TABLE CATALOGO IS 'Tabla que almacena los catálogos de películas (Cartelera actual, Próximos estrenos, etc.)';
COMMENT ON COLUMN CATALOGO.id_catalogo IS 'Identificador único del catálogo';
COMMENT ON COLUMN CATALOGO.tipo IS 'Tipo de catálogo (CARTELERA, PROXIMO_ESTRENO, etc.)';
COMMENT ON COLUMN CATALOGO.codigo IS 'Código único del catálogo';
COMMENT ON COLUMN CATALOGO.descripcion IS 'Descripción del catálogo';
COMMENT ON COLUMN CATALOGO.activo IS 'Indica si el catálogo está activo (1) o inactivo (0)';

-- =============================================
-- Tabla: PELICULA
-- Descripción: Almacena información de las películas
-- =============================================
CREATE TABLE PELICULA (
    id_pelicula NUMBER(10) NOT NULL,
    titulo VARCHAR2(200) NOT NULL,
    duracion NUMBER(3) NOT NULL,
    id_genero NUMBER(10) NOT NULL,
    clasificacion VARCHAR2(10) NOT NULL,
    sinopsis VARCHAR2(2000),
    fecha_estreno DATE NOT NULL,
    estado VARCHAR2(20) DEFAULT 'ACTIVA' NOT NULL,
    CONSTRAINT pk_pelicula PRIMARY KEY (id_pelicula),
    CONSTRAINT fk_pelicula_genero FOREIGN KEY (id_genero) REFERENCES GENERO(id_genero),
    CONSTRAINT chk_pelicula_duracion CHECK (duracion > 0),
    CONSTRAINT chk_pelicula_estado CHECK (estado IN ('ACTIVA', 'INACTIVA', 'PROXIMAMENTE'))
);

COMMENT ON TABLE PELICULA IS 'Tabla que almacena la información de las películas';
COMMENT ON COLUMN PELICULA.id_pelicula IS 'Identificador único de la película';
COMMENT ON COLUMN PELICULA.titulo IS 'Título de la película';
COMMENT ON COLUMN PELICULA.duracion IS 'Duración de la película en minutos';
COMMENT ON COLUMN PELICULA.id_genero IS 'Referencia al género principal de la película';
COMMENT ON COLUMN PELICULA.clasificacion IS 'Clasificación por edad (G, PG, PG-13, R, etc.)';
COMMENT ON COLUMN PELICULA.sinopsis IS 'Sinopsis de la película';
COMMENT ON COLUMN PELICULA.fecha_estreno IS 'Fecha de estreno de la película';
COMMENT ON COLUMN PELICULA.estado IS 'Estado de la película (ACTIVA, INACTIVA, PROXIMAMENTE)';

-- =============================================
-- Tabla: PELICULA_CATALOGO (Tabla intermedia N:N)
-- Descripción: Relaciona películas con catálogos
-- =============================================
CREATE TABLE PELICULA_CATALOGO (
    id_pelicula NUMBER(10) NOT NULL,
    id_catalogo NUMBER(10) NOT NULL,
    fecha_agregado DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT pk_pelicula_catalogo PRIMARY KEY (id_pelicula, id_catalogo),
    CONSTRAINT fk_pc_pelicula FOREIGN KEY (id_pelicula) REFERENCES PELICULA(id_pelicula) ON DELETE CASCADE,
    CONSTRAINT fk_pc_catalogo FOREIGN KEY (id_catalogo) REFERENCES CATALOGO(id_catalogo) ON DELETE CASCADE
);

COMMENT ON TABLE PELICULA_CATALOGO IS 'Tabla intermedia que relaciona películas con catálogos';
COMMENT ON COLUMN PELICULA_CATALOGO.id_pelicula IS 'Referencia a la película';
COMMENT ON COLUMN PELICULA_CATALOGO.id_catalogo IS 'Referencia al catálogo';
COMMENT ON COLUMN PELICULA_CATALOGO.fecha_agregado IS 'Fecha en que se agregó la película al catálogo';

-- =============================================
-- Tabla: SALA_DE_CINE
-- Descripción: Almacena información de las salas del cine
-- =============================================
CREATE TABLE SALA_DE_CINE (
    id_sala NUMBER(10) NOT NULL,
    nombre VARCHAR2(100) NOT NULL,
    capacidad_total NUMBER(3) NOT NULL,
    tipo_sala VARCHAR2(20) NOT NULL,
    estado VARCHAR2(20) DEFAULT 'DISPONIBLE' NOT NULL,
    CONSTRAINT pk_sala_de_cine PRIMARY KEY (id_sala),
    CONSTRAINT uk_sala_nombre UNIQUE (nombre),
    CONSTRAINT chk_sala_capacidad CHECK (capacidad_total > 0),
    CONSTRAINT chk_sala_tipo CHECK (tipo_sala IN ('REGULAR', 'VIP', '3D', 'IMAX', '4DX')),
    CONSTRAINT chk_sala_estado CHECK (estado IN ('DISPONIBLE', 'MANTENIMIENTO', 'FUERA_DE_SERVICIO'))
);

COMMENT ON TABLE SALA_DE_CINE IS 'Tabla que almacena la información de las salas del cine';
COMMENT ON COLUMN SALA_DE_CINE.id_sala IS 'Identificador único de la sala';
COMMENT ON COLUMN SALA_DE_CINE.nombre IS 'Nombre de la sala';
COMMENT ON COLUMN SALA_DE_CINE.capacidad_total IS 'Capacidad total de asientos de la sala';
COMMENT ON COLUMN SALA_DE_CINE.tipo_sala IS 'Tipo de sala (REGULAR, VIP, 3D, IMAX, 4DX)';
COMMENT ON COLUMN SALA_DE_CINE.estado IS 'Estado de la sala (DISPONIBLE, MANTENIMIENTO, FUERA_DE_SERVICIO)';

-- =============================================
-- Tabla: ASIENTO
-- Descripción: Almacena los asientos de cada sala
-- =============================================
CREATE TABLE ASIENTO (
    id_asiento NUMBER(10) NOT NULL,
    id_sala NUMBER(10) NOT NULL,
    fila VARCHAR2(2) NOT NULL,
    numero NUMBER(3) NOT NULL,
    tipo_asiento VARCHAR2(20) NOT NULL,
    estado VARCHAR2(20) DEFAULT 'DISPONIBLE' NOT NULL,
    CONSTRAINT pk_asiento PRIMARY KEY (id_asiento),
    CONSTRAINT fk_asiento_sala FOREIGN KEY (id_sala) REFERENCES SALA_DE_CINE(id_sala) ON DELETE CASCADE,
    CONSTRAINT uk_asiento_sala_fila_numero UNIQUE (id_sala, fila, numero),
    CONSTRAINT chk_asiento_numero CHECK (numero > 0),
    CONSTRAINT chk_asiento_tipo CHECK (tipo_asiento IN ('REGULAR', 'VIP', 'DISCAPACITADO')),
    CONSTRAINT chk_asiento_estado CHECK (estado IN ('DISPONIBLE', 'OCUPADO', 'MANTENIMIENTO'))
);

COMMENT ON TABLE ASIENTO IS 'Tabla que almacena los asientos de cada sala';
COMMENT ON COLUMN ASIENTO.id_asiento IS 'Identificador único del asiento';
COMMENT ON COLUMN ASIENTO.id_sala IS 'Referencia a la sala a la que pertenece el asiento';
COMMENT ON COLUMN ASIENTO.fila IS 'Fila del asiento (A, B, C, etc.)';
COMMENT ON COLUMN ASIENTO.numero IS 'Número del asiento dentro de la fila';
COMMENT ON COLUMN ASIENTO.tipo_asiento IS 'Tipo de asiento (REGULAR, VIP, DISCAPACITADO)';
COMMENT ON COLUMN ASIENTO.estado IS 'Estado del asiento (DISPONIBLE, OCUPADO, MANTENIMIENTO)';

-- =============================================
-- Tabla: FUNCION
-- Descripción: Almacena las funciones/horarios de proyección
-- =============================================
CREATE TABLE FUNCION (
    id_funcion NUMBER(10) NOT NULL,
    id_pelicula NUMBER(10) NOT NULL,
    id_sala NUMBER(10) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    precio_boleto NUMBER(6,2) NOT NULL,
    estado VARCHAR2(20) DEFAULT 'PROGRAMADA' NOT NULL,
    CONSTRAINT pk_funcion PRIMARY KEY (id_funcion),
    CONSTRAINT fk_funcion_pelicula FOREIGN KEY (id_pelicula) REFERENCES PELICULA(id_pelicula),
    CONSTRAINT fk_funcion_sala FOREIGN KEY (id_sala) REFERENCES SALA_DE_CINE(id_sala),
    CONSTRAINT chk_funcion_precio CHECK (precio_boleto > 0),
    CONSTRAINT chk_funcion_estado CHECK (estado IN ('PROGRAMADA', 'EN_CURSO', 'FINALIZADA', 'CANCELADA'))
);

COMMENT ON TABLE FUNCION IS 'Tabla que almacena las funciones o horarios de proyección de películas';
COMMENT ON COLUMN FUNCION.id_funcion IS 'Identificador único de la función';
COMMENT ON COLUMN FUNCION.id_pelicula IS 'Referencia a la película que se proyecta';
COMMENT ON COLUMN FUNCION.id_sala IS 'Referencia a la sala donde se proyecta';
COMMENT ON COLUMN FUNCION.fecha_hora IS 'Fecha y hora de la función';
COMMENT ON COLUMN FUNCION.precio_boleto IS 'Precio del boleto para esta función';
COMMENT ON COLUMN FUNCION.estado IS 'Estado de la función (PROGRAMADA, EN_CURSO, FINALIZADA, CANCELADA)';

-- =============================================
-- Tabla: CLIENTE
-- Descripción: Almacena información de los clientes
-- =============================================
CREATE TABLE CLIENTE (
    id_cliente NUMBER(10) NOT NULL,
    nombre VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    telefono VARCHAR2(15),
    fecha_registro TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT uk_cliente_email UNIQUE (email),
    CONSTRAINT chk_cliente_email CHECK (email LIKE '%_@__%.__%')
);

COMMENT ON TABLE CLIENTE IS 'Tabla que almacena la información de los clientes';
COMMENT ON COLUMN CLIENTE.id_cliente IS 'Identificador único del cliente';
COMMENT ON COLUMN CLIENTE.nombre IS 'Nombre completo del cliente';
COMMENT ON COLUMN CLIENTE.email IS 'Correo electrónico del cliente';
COMMENT ON COLUMN CLIENTE.telefono IS 'Teléfono del cliente';
COMMENT ON COLUMN CLIENTE.fecha_registro IS 'Fecha y hora de registro del cliente';

-- =============================================
-- Tabla: VENTA
-- Descripción: Almacena las transacciones de venta
-- =============================================
CREATE TABLE VENTA (
    id_venta NUMBER(10) NOT NULL,
    id_cliente NUMBER(10) NOT NULL,
    fecha_venta TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    total NUMBER(8,2) NOT NULL,
    metodo_pago VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_venta PRIMARY KEY (id_venta),
    CONSTRAINT fk_venta_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    CONSTRAINT chk_venta_total CHECK (total >= 0),
    CONSTRAINT chk_venta_metodo_pago CHECK (metodo_pago IN ('EFECTIVO', 'TARJETA', 'TRANSFERENCIA', 'DIGITAL'))
);

COMMENT ON TABLE VENTA IS 'Tabla que almacena las transacciones de venta';
COMMENT ON COLUMN VENTA.id_venta IS 'Identificador único de la venta';
COMMENT ON COLUMN VENTA.id_cliente IS 'Referencia al cliente que realizó la compra';
COMMENT ON COLUMN VENTA.fecha_venta IS 'Fecha y hora de la venta';
COMMENT ON COLUMN VENTA.total IS 'Monto total de la venta';
COMMENT ON COLUMN VENTA.metodo_pago IS 'Método de pago utilizado';

-- =============================================
-- Tabla: BOLETO
-- Descripción: Almacena los boletos vendidos
-- =============================================
CREATE TABLE BOLETO (
    id_boleto NUMBER(10) NOT NULL,
    id_funcion NUMBER(10) NOT NULL,
    id_cliente NUMBER(10) NOT NULL,
    id_asiento NUMBER(10) NOT NULL,
    fecha_compra DATE DEFAULT SYSDATE NOT NULL,
    estado VARCHAR2(20) DEFAULT 'VALIDO' NOT NULL,
    CONSTRAINT pk_boleto PRIMARY KEY (id_boleto),
    CONSTRAINT fk_boleto_funcion FOREIGN KEY (id_funcion) REFERENCES FUNCION(id_funcion),
    CONSTRAINT fk_boleto_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    CONSTRAINT fk_boleto_asiento FOREIGN KEY (id_asiento) REFERENCES ASIENTO(id_asiento),
    CONSTRAINT uk_boleto_funcion_asiento UNIQUE (id_funcion, id_asiento),
    CONSTRAINT chk_boleto_estado CHECK (estado IN ('VALIDO', 'USADO', 'CANCELADO', 'REEMBOLSADO'))
);

COMMENT ON TABLE BOLETO IS 'Tabla que almacena los boletos vendidos';
COMMENT ON COLUMN BOLETO.id_boleto IS 'Identificador único del boleto';
COMMENT ON COLUMN BOLETO.id_funcion IS 'Referencia a la función para la cual se vendió el boleto';
COMMENT ON COLUMN BOLETO.id_cliente IS 'Referencia al cliente que compró el boleto';
COMMENT ON COLUMN BOLETO.id_asiento IS 'Referencia al asiento asignado';
COMMENT ON COLUMN BOLETO.fecha_compra IS 'Fecha de compra del boleto';
COMMENT ON COLUMN BOLETO.estado IS 'Estado del boleto (VALIDO, USADO, CANCELADO, REEMBOLSADO)';

-- =============================================
-- Tabla: BOLETO_VENTA (Tabla intermedia N:N)
-- Descripción: Relaciona boletos con ventas
-- =============================================
CREATE TABLE BOLETO_VENTA (
    id_boleto NUMBER(10) NOT NULL,
    id_venta NUMBER(10) NOT NULL,
    precio_unitario NUMBER(6,2) NOT NULL,
    CONSTRAINT pk_boleto_venta PRIMARY KEY (id_boleto, id_venta),
    CONSTRAINT fk_bv_boleto FOREIGN KEY (id_boleto) REFERENCES BOLETO(id_boleto) ON DELETE CASCADE,
    CONSTRAINT fk_bv_venta FOREIGN KEY (id_venta) REFERENCES VENTA(id_venta) ON DELETE CASCADE,
    CONSTRAINT chk_bv_precio CHECK (precio_unitario > 0)
);

COMMENT ON TABLE BOLETO_VENTA IS 'Tabla intermedia que relaciona boletos con ventas';
COMMENT ON COLUMN BOLETO_VENTA.id_boleto IS 'Referencia al boleto';
COMMENT ON COLUMN BOLETO_VENTA.id_venta IS 'Referencia a la venta';
COMMENT ON COLUMN BOLETO_VENTA.precio_unitario IS 'Precio del boleto en esta venta';