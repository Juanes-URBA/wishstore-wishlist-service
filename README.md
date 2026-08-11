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

| Dependencia | Propósito |
|---|---|
| spring-boot-starter-web | Exposición de endpoints REST |
| spring-boot-starter-data-jpa | Persistencia con MySQL |
| spring-boot-starter-validation | Validación de DTOs |
| mysql-connector-j | Driver JDBC de MySQL |
| lombok | Reducción de código boilerplate |
| spring-cloud-starter-openfeign | Clientes declarativos hacia Catalog y History Service |
| spring-boot-devtools | Recarga en caliente en desarrollo |

## Instalación

```bash
git clone https://github.com/Juanes-URBA/wishstore-wishlist-service.git
cd wishstore-wishlist-service
mvn clean install
```
## Endpoints

| Método | Ruta | Descripción | Código éxito |
|---|---|---|---|
| GET | `/wishlist` | Lista todos los registros | 200 |
| GET | `/wishlist/{id}` | Obtiene un registro por id | 200 / 404 |
| GET | `/wishlist/user/{userId}` | Lista los registros de un usuario | 200 |
| POST | `/wishlist` | Crea un nuevo registro | 201 / 400 / 404 / 409 |
| DELETE | `/wishlist/{id}` | Elimina un registro por id | 204 / 404 |
