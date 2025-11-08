# Guía de Configuración - Desarrollo Local

## Tabla de Contenidos
1. [Configuración del Proyecto Java](#configuración-del-proyecto-java)
2. [Configuración de Base de Datos Oracle](#configuración-de-base-de-datos-oracle)
3. [Configuración de Conexión en Spring Boot](#configuración-de-conexión-en-spring-boot)
4. [Solución de Problemas](#solución-de-problemas-comunes)

---

## Configuración del Proyecto Java

### Requisitos Previos
- Java 17 o superior
- Gradle 7.x o superior
- IntelliJ IDEA (recomendado) o cualquier IDE compatible

### Configurar SDK

Asegúrate de tener configurado el JDK 17 en tu proyecto:

1. Abre la estructura del proyecto (File > Project Structure)
2. En "Project Settings > Project", selecciona SDK 17
3. En "Project Settings > Modules", verifica que el Language Level sea 17
4. Aplica los cambios

### Configurar Gradle

El proyecto utiliza Gradle como gestor de dependencias:

1. Abre las configuraciones de Gradle (File > Settings > Build, Execution, Deployment > Build Tools > Gradle)
2. Selecciona "Gradle JVM" como JDK 17
3. Asegúrate de que "Build and run using" esté configurado como "Gradle"
4. Sincroniza el proyecto con Gradle

---

## Configuración de Base de Datos Oracle

### Prerrequisitos
- Oracle Database (XE 21c) instalado o corriendo en contenedor Docker
- SQL Developer, DBeaver o herramienta similar para ejecutar scripts SQL

### Conexión Inicial como SYSTEM o Administrador de Base de Datos

Para ejecutar los scripts de creación, primero necesitas conectarte como usuario SYSTEM:

## Ejecución de Scripts SQL

### Orden de Ejecución

**IMPORTANTE:** Los scripts deben ejecutarse en el orden exacto indicado para evitar errores de dependencias entre tablas y datos.

### Fase 1: Data Definition Language (DDL)

Esta fase crea la estructura de la base de datos: usuario, tablas, secuencias e índices.

#### Script 1: Crear Usuario

```sql
@resources/sql/DDL/01_crear_usuario.sql
```

**Propósito:** Crea el usuario `cine_admin` con los privilegios necesarios para gestionar la base de datos del proyecto.

**Credenciales creadas:**
- Usuario: `cine_admin`
- Contraseña: `CineAdmin2025`

#### Script 2: Cambiar de Conexión

**CRÍTICO:** Después de ejecutar el script anterior, debes desconectarte del usuario `system` y crear una nueva conexión con las siguientes credenciales:

- Username: `cine_admin`
- Password: `CineAdmin2025`
- Service Name: `XEPDB1`
- Host: `localhost`
- Port: `1521`

**Todos los scripts siguientes deben ejecutarse conectado como `cine_admin`.**

#### Script 3: Crear Tablas

```sql
@resources/sql/DDL/02_crear_tablas.sql
```

**Propósito:** Crea las 11 tablas del sistema con todas sus constraints (Primary Keys, Foreign Keys, Unique, Check).

**Tablas creadas:**
- GENERO
- CATALOGO
- PELICULA
- PELICULA_CATALOGO
- SALA_DE_CINE
- ASIENTO
- FUNCION
- CLIENTE
- VENTA
- BOLETO
- BOLETO_VENTA

#### Script 4: Crear Secuencias

```sql
@resources/sql/DDL/03_crear_secuencias.sql
```

**Propósito:** Crea 9 secuencias para la generación automática de IDs en las tablas principales.

#### Script 5: Crear Índices

```sql
@resources/sql/DDL/04_crear_indices.sql
```

**Propósito:** Crea 29 índices para optimizar las consultas más frecuentes del sistema.

### Fase 2: Data Manipulation Language (DML)

Esta fase inserta los datos iniciales en todas las tablas.

**NOTA:** Los scripts DML deben ejecutarse en el orden indicado debido a las dependencias entre tablas (Foreign Keys).

#### Script 1: Datos de Géneros
```sql
@resources/sql/DML/01_datos_genero.sql
```
Inserta 15 géneros cinematográficos (Acción, Comedia, Drama, Terror, etc.)

#### Script 2: Datos de Catálogos
```sql
@resources/sql/DML/02_datos_catalogo.sql
```
Inserta 8 catálogos (Cartelera, Próximos Estrenos, Recomendados, etc.)

#### Script 3: Datos de Películas
```sql
@resources/sql/DML/03_datos_pelicula.sql
```
Inserta 16 películas con sus relaciones a géneros y catálogos (29 relaciones en total)

#### Script 4: Datos de Salas
```sql
@resources/sql/DML/04_datos_salas.sql
```
Inserta 10 salas de diferentes tipos (Regular, 3D, VIP, IMAX, 4DX)

#### Script 5: Datos de Asientos
```sql
@resources/sql/DML/05_datos_asientos.sql
```
Genera aproximadamente 1,200 asientos distribuidos en las 10 salas.

**NOTA:** Este script puede tardar entre 1 y 3 minutos en ejecutarse completamente.

#### Script 6: Datos de Funciones
```sql
@resources/sql/DML/06_datos_funciones.sql
```
Inserta 34 funciones (horarios de proyección) para hoy y mañana.

#### Script 7: Datos de Clientes
```sql
@resources/sql/DML/07_datos_clientes.sql
```
Inserta 20 clientes registrados con sus datos de contacto.

#### Script 8: Datos de Ventas
```sql
@resources/sql/DML/08_datos_ventas.sql
```
Inserta 15 transacciones de venta con diferentes métodos de pago.

#### Script 9: Datos de Boletos
```sql
@resources/sql/DML/09_datos_boletos.sql
```
Inserta 31 boletos vendidos y sus relaciones con ventas (tabla BOLETO_VENTA).

### Verificación de la Instalación

Después de ejecutar todos los scripts, puedes verificar que los datos se insertaron correctamente:

```sql
SELECT 'GENERO' AS TABLA, COUNT(*) AS REGISTROS FROM GENERO
UNION ALL
SELECT 'CATALOGO', COUNT(*) FROM CATALOGO
UNION ALL
SELECT 'PELICULA', COUNT(*) FROM PELICULA
UNION ALL
SELECT 'PELICULA_CATALOGO', COUNT(*) FROM PELICULA_CATALOGO
UNION ALL
SELECT 'SALA_DE_CINE', COUNT(*) FROM SALA_DE_CINE
UNION ALL
SELECT 'ASIENTO', COUNT(*) FROM ASIENTO
UNION ALL
SELECT 'FUNCION', COUNT(*) FROM FUNCION
UNION ALL
SELECT 'CLIENTE', COUNT(*) FROM CLIENTE
UNION ALL
SELECT 'VENTA', COUNT(*) FROM VENTA
UNION ALL
SELECT 'BOLETO', COUNT(*) FROM BOLETO
UNION ALL
SELECT 'BOLETO_VENTA', COUNT(*) FROM BOLETO_VENTA;
```

**Resultados esperados:**

| Tabla | Registros Esperados |
|-------|---------------------|
| GENERO | 15 |
| CATALOGO | 8 |
| PELICULA | 16 |
| PELICULA_CATALOGO | 29 |
| SALA_DE_CINE | 10 |
| ASIENTO | ~1,200 |
| FUNCION | 34 |
| CLIENTE | 20 |
| VENTA | 15 |
| BOLETO | 31 |
| BOLETO_VENTA | 31 |

---

## Configuración de Conexión en Spring Boot

### Archivo application.properties

Edita el archivo ubicado en: `src/main/resources/application.properties`

```properties
# Datasource Oracle
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/{TU_SID}
spring.datasource.username=cine_admin
spring.datasource.password=CineAdmin2025
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

## Solución de Problemas
### Resetear la Base de Datos

Si necesitas reiniciar desde cero (por ejemplo, si cometiste algún error durante la instalación):

### Paso 1: Eliminar el Usuario

Conéctate como `system` y ejecuta:

```sql
DROP USER cine_admin CASCADE;
```

Este comando elimina el usuario `cine_admin` y todos los objetos de base de datos asociados (tablas, secuencias, índices, datos).

### Paso 2: Volver a Ejecutar los Scripts

Sigue nuevamente todos los pasos desde la sección "Ejecución de Scripts SQL".
