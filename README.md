# CineTickets API

Sistema de gestión de venta de boletos para cines desarrollado con Spring Boot y Oracle Database.

## Descripción del Proyecto

CineTickets API es una aplicación backend que gestiona la venta de boletos de cine, permitiendo administrar películas, salas, funciones, clientes y ventas de manera eficiente y escalable.

## Características Principales

- Gestión completa de películas y géneros cinematográficos
- Administración de salas y asientos con diferentes tipos (Regular, VIP, 3D, IMAX, 4DX)
- Sistema de funciones (horarios de proyección)
- Control de disponibilidad de asientos en tiempo real
- Registro y gestión de clientes
- Procesamiento de ventas con múltiples métodos de pago
- Generación y validación de boletos

## Tecnologías Utilizadas

- **Backend:** Java 17, Spring Boot 3.x
- **Base de Datos:** Oracle Database XE 21c
- **ORM:** Spring Data JPA / Hibernate
- **Gestor de Dependencias:** Gradle
- **Contenedorización:** Docker

## Estructura de la Base de Datos

El sistema cuenta con 11 tablas organizadas en tres módulos principales:

### Módulo de Contenido
- `GENERO`: Clasificación de películas
- `CATALOGO`: Agrupaciones de películas
- `PELICULA`: Información detallada de películas
- `PELICULA_CATALOGO`: Relación muchos a muchos entre películas y catálogos

### Módulo de Infraestructura
- `SALA_DE_CINE`: Salas del cine
- `ASIENTO`: Asientos por sala
- `FUNCION`: Horarios de proyección

### Módulo de Ventas
- `CLIENTE`: Clientes registrados
- `VENTA`: Transacciones de venta
- `BOLETO`: Boletos individuales
- `BOLETO_VENTA`: Relación muchos a muchos entre boletos y ventas

## Requisitos del Sistema

### Software Requerido
- Java Development Kit (JDK) 17 o superior
- Docker y Docker Compose
- Gradle 7.x o superior
- Git

### Herramientas Recomendadas
- IntelliJ IDEA (Community o Ultimate)
- SQL Developer, DBeaver o cualquier cliente SQL
- Postman o similar para pruebas de API

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Rafael-ID24001/CineTicketsAPI.git
cd CineTicketsAPI
```

### 2. Configurar la Base de Datos

Sigue las instrucciones detalladas en [DEVELOPMENT.md](DEVELOPMENT.md) para:
- Levantar el contenedor Docker con Oracle XE 21c
- Ejecutar los scripts DDL para crear la estructura
- Ejecutar los scripts DML para insertar datos iniciales

### 3. Configurar la Aplicación

Edita el archivo `src/main/resources/application.properties` con las credenciales de tu base de datos:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=cine_admin
spring.datasource.password=CineAdmin2025
```



La aplicación estará disponible en `http://localhost:8080`

## Scripts de Base de Datos

### Scripts DDL (Data Definition Language)
Ubicados en `resources/sql/DDL/`:
1. `01_crear_usuario.sql` - Crea el usuario de base de datos
2. `02_crear_tablas.sql` - Crea todas las tablas del sistema
3. `03_crear_secuencias.sql` - Crea secuencias para IDs automáticos
4. `04_crear_indices.sql` - Crea índices para optimización

### Scripts DML (Data Manipulation Language)
Ubicados en `resources/sql/DML/`:
1. `01_datos_genero.sql` - 15 géneros de películas
2. `02_datos_catalogo.sql` - 8 catálogos
3. `03_datos_pelicula.sql` - 16 películas
4. `04_datos_salas.sql` - 10 salas de diferentes tipos
5. `05_datos_asientos.sql` - Aproximadamente 1,200 asientos
6. `06_datos_funciones.sql` - 34 funciones programadas
7. `07_datos_clientes.sql` - 20 clientes
8. `08_datos_ventas.sql` - 15 ventas
9. `09_datos_boletos.sql` - 31 boletos

## Documentación

- [DEVELOPMENT.md](DEVELOPMENT.md) - Guía completa de configuración del entorno de desarrollo
- [API Documentation](#) - Documentación de endpoints (próximamente)

## Estructura del Proyecto

```
CineTicketsAPI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ues/occ/cinetickets/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── sql/
│   │           ├── DDL/
│   │           └── DML/
│   └── test/
├── build.gradle
├── DEVELOPMENT.md
└── README.md
```

## Convenciones de Desarrollo

### Branches
- `main` - Rama principal estable
- `develop` - Rama de desarrollo
- `feature/due*` - Ramas para nuevas características
- `hotfix/*` - Ramas para correcciones urgentes

### Commits
Seguimos la convención de Conventional Commits:
- `feat:` - Nueva característica
- `fix:` - Corrección de bug
- `docs:` - Cambios en documentación
- `refactor:` - Refactorización de código
- `test:` - Adición o modificación de tests

## Equipo de Desarrollo

### Integrantes del Proyecto
- Brandon Emmanuel Morales Baños MB23039
- Cledy Yamileth Alfaro Murillo AM19116
- Erick Lisandro Campos Magaña CM22118
- Rafael Armando Ibáñez Diego ID24001
- Tatiana Carolina Martínez Franco MF24026


### Responsabilidades por Tarea

#### Tarea 1: Scripts de Base de Datos
- **Responsable:** Tatiana Carolina Martínez Franco (MF24026)
- Scripts DDL y DML completos
- Documentación de base de datos

#### Tarea 2: Modelos y Repositorios
- **Responsable:** Por asignar
- Entidades JPA
- Repositorios Spring Data

#### Tarea 3: Servicios y Controladores
- **Responsable:** Por asignar
- Lógica de negocio
- Endpoints REST

## Estado del Proyecto

**Versión Actual:** 1.0.0-SNAPSHOT

**Características Completadas:**
- [x] Diseño de base de datos
- [x] Scripts DDL
- [x] Scripts DML con datos de prueba
- [x] Documentación de configuración

**En Desarrollo:**
- [ ] Modelos de entidades JPA
- [ ] Repositorios
- [ ] Servicios de negocio
- [ ] Controladores REST
- [ ] Tests unitarios
- [ ] Tests de integración

## Contribuir al Proyecto

1. Crea una rama desde `develop` con el formato `feature/nombre-caracteristica`
2. Realiza tus cambios siguiendo las convenciones establecidas
3. Asegúrate de que tu código compile sin errores
4. Crea un Pull Request hacia `develop`
5. Espera la revisión de al menos un compañero del equipo

## Licencia

Este proyecto es desarrollado con fines académicos para la Universidad de El Salvador.

## Contacto

Para preguntas o sugerencias sobre el proyecto, contacta a cualquier miembro del equipo de desarrollo.

---

**Universidad de El Salvador**  
**Facultad de Ingeniería en Desasrrollo de Software**  
**Programación Orientada a Objetos - Ciclo IV 2025**