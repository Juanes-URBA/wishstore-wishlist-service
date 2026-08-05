# wishstore-wishlist-service

## Descripción

Microservicio encargado de la gestión de listas de deseos (*Wishlist*) dentro
del ecosistema **WishStore**. Permite a los usuarios agregar, consultar y
eliminar productos de su lista de deseos, validando la existencia del
producto contra el **Catalog Service** y registrando cada operación en el
**History Service**.

> Este README se completa en la Entrega 5 con el detalle final de endpoints,
> ejemplos y colección Postman.

## Tecnologías

- Java 21
- Spring Boot 3.x
- Maven
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL Driver
- Lombok
- Spring Cloud OpenFeign
- Spring Boot DevTools
- Docker

## Arquitectura
Cliente
↓
WishlistController
↓
WishlistService
↓
WishlistServiceImpl
↓
WishlistRepository CatalogClient HistoryClient
↓
MySQL

## Dependencias

Ver `pom.xml` en la raíz del proyecto.

## Instalación

```bash
git clone https://github.com/Juanes-URBA/wishstore-wishlist-service.git
cd wishstore-wishlist-service
mvn clean install
```

