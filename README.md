# 🛒 E-commerce API - Spring Boot + React

Proyecto personal en desarrollo donde estoy construyendo un sistema de compra y venta de productos para profundizar en arquitectura backend con **Java + Spring Boot**, integrándolo con un **frontend en React + TypeScript**.

El objetivo del proyecto es aplicar buenas prácticas de desarrollo, seguridad y diseño de APIs mientras documento el proceso de aprendizaje.

---

# 🚀 Tecnologías

Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- JPA / Hibernate
- Mockito (tests)
- Maven

Frontend
- React
- TypeScript
- Vite

Infraestructura
- Docker
- PostgreSQL
- REST API

---

# 🔐 Autenticación

El sistema implementa autenticación basada en **JWT (JSON Web Token)** utilizando **Spring Security**.

Características:

- Login con generación de token
- Protección de endpoints
- Sistema de **roles**
- Autorización basada en permisos

---

# 🧩 Funcionalidades actuales

- Registro y login de usuarios
- Sistema de roles
- Listado de productos
- Navegación a publicaciones específicas
- Sistema de carrito
- Modelo de compra
- Sistema de reviews
- Filtros de búsqueda de productos
- API REST estructurada

---

# 🗄️ Modelo de datos

El sistema modela las siguientes entidades principales:

- User
- Role
- Product
- Cart
- Purchase
- PurchaseDetail
- Review

Las relaciones permiten soportar lógica de:

- compra de productos
- historial de compras
- carrito de usuario
- calificaciones entre comprador y vendedor

---

# 🐳 Docker

Tanto la **API como la base de datos** pueden ejecutarse en contenedores Docker.

Para levantar el proyecto:

```bash
docker-compose up --build
