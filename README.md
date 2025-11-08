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

### Herramientas Recomendadas
- IntelliJ IDEA (Community o Ultimate)
- SQL Developer, DBeaver o cualquier cliente SQL
- Postman o similar para pruebas de API
- Oracle Data Base

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Rafael-ID24001/CineTicketsAPI.git
cd CineTicketsAPI
```

### 2. Configurar la Base de Datos

Sigue las instrucciones detalladas en [DEVELOPMENT.md](DEVELOPMENT.md) para:
- Ejecutar los scripts DDL para crear la estructura
- Ejecutar los scripts DML para insertar datos iniciales

### 3. Ejecutar la clase principal

``` CineTicketsApiApplication.java ```

La aplicación estará disponible en `http://localhost:8080/cine-tickets/api`


## Documentación

- [DEVELOPMENT.md](DEVELOPMENT.md) - Guía completa de configuración del entorno de desarrollo


## Convenciones de Desarrollo

### Branches
- `main` - Rama principal estable
- `develop` - Rama de desarrollo
- `feature/due*` - Ramas para nuevas características

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

## Licencia
[MIT License](LICENSE)

Este proyecto es desarrollado con fines académicos para la Universidad de El Salvador.

**Universidad de El Salvador**  
**Facultad de Ingeniería en Desasrrollo de Software**  
**Programación Orientada a Objetos - Ciclo IV 2025**
