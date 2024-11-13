# SpacecraftManagementApp
[![Java](https://img.shields.io/badge/Java-17-007396?style=flat&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=flat&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Liquibase](https://img.shields.io/badge/Liquibase-007D35?style=flat&logo=liquibase&logoColor=white)](https://www.liquibase.org/)
[![H2](https://img.shields.io/badge/H2-6DB33F?style=flat&logo=h2&logoColor=white)](https://www.h2database.com/)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white)](https://www.postman.com/)
![Swagger](https://img.shields.io/badge/Swagger-3-85EA2D?style=flat&logo=swagger)

**SpacecraftManagementApp** es una API REST desarrollada con **Spring Boot** que permite gestionar un conjunto de naves espaciales presentes en series y películas. La API permite realizar operaciones CRUD sobre las naves espaciales, gestionar excepciones personalizadas, y mantener la trazabilidad de las operaciones a través de un log centralizado.

La aplicación utiliza **H2** como base de datos en memoria y **Liquibase** para el manejo de las migraciones de la base de datos. Además, se incorpora un **Aspect** que registra un log cuando se consulta una nave con un ID negativo.

## Objetivo

- Desarrollo de una aplicación con **Spring Boot** para gestionar naves espaciales.
- La API REST permite realizar las siguientes acciones:
    - **GET** `/api/spacecraft`: Consultar todas las naves usando paginación.
    - **GET** `/api/spacecraft/{id}`: Consultar una nave por ID.
    - **GET** `/api/spacecraft/find?name={name}`: Consultar naves por nombre que contengan un parámetro dado (por ejemplo, "wing").
    - **POST** `/api/spacecraft`: Crear una nueva nave.
    - **PUT** `/api/spacecraft/{id}`: Modificar una nave existente.
    - **DELETE** `/api/spacecraft/{id}`: Eliminar una nave.

## Tecnologías Usadas

- **Java 17**
- **Spring Boot**
- **H2** (base de datos en memoria)
- **Liquibase** (para el manejo de migraciones de base de datos)
- **AspectJ** (para la creación de aspectos y logs)
- **Spring Cache** (para la gestión de caché)
- **JUnit** (para pruebas unitarias)
- **Postman** (para pruebas de integración)
- **Maven** (para la gestión de dependencias y construcción del proyecto)

## Endpoints Disponibles

### 1. Consultar todas las naves espaciales con paginación

- **Método**: `GET`
- **URL**: `/api/spacecraft?page={page}&size={size}`
- **Ejemplo de cURL**:

```bash
curl -X GET "http://localhost:8081/api/spacecraft?page=0&size=10"
```
## Postman cURL'S

```
curl -X GET "http://localhost:8080/api/spacecraft?page=0&size=10"


curl -X GET "http://localhost:8080/api/spacecraft/1"


curl -X GET "http://localhost:8080/api/spacecraft/find?name=wing"


curl -X POST "http://localhost:8080/api/spacecraft" -H "Content-Type: application/json" -d '{
    "name": "TIE Bomber",
    "type": "Bomber",
    "origin": "Galactic Empire"
}'


curl -X PUT "http://localhost:8080/api/spacecraft/1" -H "Content-Type: application/json" -d '{
    "name": "Updated X-Wing",
    "type": "Fighter",
    "origin": "Rebel Alliance"
}'


curl -X DELETE "http://localhost:8080/api/spacecraft/1"
```
## Cómo Funciona la Aplicación

La API permite gestionar naves espaciales de series y películas a través de los endpoints REST mencionados anteriormente. La aplicación está construida con **Spring Boot** y utiliza **H2** como base de datos en memoria, con el soporte de **Liquibase** para las migraciones de la base de datos.

### Caché

Para mejorar el rendimiento, la aplicación implementa una capa de caché, almacenando los resultados de consultas comunes en memoria.

### Aspecto para Log de ID Negativo

Cuando se consulta una nave espacial utilizando un ID negativo, se registra un log automáticamente gracias a un **Aspect** desarrollado con **AspectJ**.

### Excepciones y Manejo de Errores

La aplicación maneja excepciones personalizadas y los errores son centralizados mediante un controlador de excepciones global, proporcionando respuestas adecuadas a los usuarios.

## Instalación

### Clonar el Repositorio

```bash
git clone https://github.com/AngelLFMorante/SpacecraftManagementApp.git
cd SpacecraftManagementApp
```


## Iniciar la Aplicación

Para iniciar la aplicación, ejecuta el siguiente comando:
```bash
mvn spring-boot:run
```

## Ejecutar las Migraciones de la Base de Datos

Liquibase gestionará las migraciones de la base de datos automáticamente. No es necesario ejecutar ningún comando adicional para crear las tablas.

## Acceso a la Aplicación

La aplicación estará disponible en ```http://localhost:8081```.

## Pruebas Unitarias

El proyecto incluye pruebas unitarias para las funcionalidades principales de la aplicación. Puedes ejecutar las pruebas con el siguiente comando:
```bash
mvn test
```
## Licencia

MIT.
