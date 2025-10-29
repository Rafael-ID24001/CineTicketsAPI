-- =============================================
-- Script: 05_datos_asientos.sql
-- Descripción: Inserta datos iniciales de asientos para cada sala
-- Autor: Tatiana Carolina Martínez Franco (MF24026)
-- Fecha: 2025
-- =============================================

-- IMPORTANTE: Este script debe ejecutarse conectado como cine_admin

PROMPT 'Insertando datos en la tabla ASIENTO...'
PROMPT 'Este proceso puede tardar unos minutos...'

-- =============================================
-- Procedimiento para generar asientos de una sala
-- =============================================
DECLARE
    v_id_sala NUMBER;
    v_capacidad NUMBER;
    v_tipo_sala VARCHAR2(20);
    v_filas NUMBER;
    v_asientos_por_fila NUMBER;
    v_letra_fila CHAR(1);
    v_tipo_asiento VARCHAR2(20);
    v_contador NUMBER := 0;
BEGIN
    -- Iterar sobre cada sala
    FOR sala IN (SELECT id_sala, nombre, capacidad_total, tipo_sala FROM SALA_DE_CINE WHERE estado != 'FUERA_DE_SERVICIO') LOOP
        v_id_sala := sala.id_sala;
        v_capacidad := sala.capacidad_total;
        v_tipo_sala := sala.tipo_sala;
        
        -- Determinar distribución según capacidad
        IF v_capacidad <= 50 THEN
            v_filas := 5;
            v_asientos_por_fila := CEIL(v_capacidad / v_filas);
        ELSIF v_capacidad <= 100 THEN
            v_filas := 8;
            v_asientos_por_fila := CEIL(v_capacidad / v_filas);
        ELSIF v_capacidad <= 150 THEN
            v_filas := 10;
            v_asientos_por_fila := CEIL(v_capacidad / v_filas);
        ELSE
            v_filas := 12;
            v_asientos_por_fila := CEIL(v_capacidad / v_filas);
        END IF;
        
        -- Generar asientos
        FOR i IN 1..v_filas LOOP
            -- Determinar letra de fila (A-Z)
            v_letra_fila := CHR(64 + i);
            
            FOR j IN 1..v_asientos_por_fila LOOP
                -- Determinar tipo de asiento
                IF v_tipo_sala = 'VIP' THEN
                    v_tipo_asiento := 'VIP';
                ELSIF j IN (1, v_asientos_por_fila) AND i = 1 THEN
                    -- Asientos para discapacitados en extremos de primera fila
                    v_tipo_asiento := 'DISCAPACITADO';
                ELSE
                    v_tipo_asiento := 'REGULAR';
                END IF;
                
                -- Insertar asiento
                INSERT INTO ASIENTO (id_asiento, id_sala, fila, numero, tipo_asiento, estado)
                VALUES (seq_asiento.NEXTVAL, v_id_sala, v_letra_fila, j, v_tipo_asiento, 'DISPONIBLE');
                
                v_contador := v_contador + 1;
                
                -- Commit cada 100 registros para mejor performance
                IF MOD(v_contador, 100) = 0 THEN
                    COMMIT;
                END IF;
            END LOOP;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('Asientos generados para ' || sala.nombre || ': ' || (v_filas * v_asientos_por_fila));
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Total de asientos generados: ' || v_contador);
END;
/

-- Marcar algunos asientos en mantenimiento (para realismo)
UPDATE ASIENTO 
SET estado = 'MANTENIMIENTO' 
WHERE id_asiento IN (
    SELECT id_asiento 
    FROM ASIENTO 
    WHERE fila = 'E' AND numero IN (7, 8) AND id_sala = 1
);

COMMIT;

PROMPT 'Datos insertados en ASIENTO completados'
PROMPT '============================================='