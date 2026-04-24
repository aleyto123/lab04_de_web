# Demo06 - API REST con Spring Boot

## Datos del estudiante

- **Nombre:** Bellido Chambi Rony Widmer
- **Curso:** Desarrollo de Aplicaciones Web

## Descripción del proyecto

Este proyecto es una aplicación web desarrollada con **Spring Boot** que expone una **API REST** para gestionar distintas entidades del sistema. Como parte de la práctica se implementan operaciones CRUD, validaciones, manejo global de errores y relaciones entre entidades usando **Spring Data JPA**.

El proyecto incluye módulos para:

- Gestión de productos
- Gestión de usuarios y perfiles
- Gestión de cursos
- Gestión de estudiantes
- Matrícula de estudiantes en cursos

## Objetivo

Aplicar los fundamentos del desarrollo de aplicaciones web con Java y Spring Boot, integrando:

- Arquitectura por capas
- Controladores REST
- DTOs para transferencia de datos
- Validaciones con Jakarta Validation
- Persistencia con JPA
- Manejo de excepciones
- Relaciones entre entidades

## Tecnologías utilizadas

- Java 25
- Spring Boot 4.0.5
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- MySQL Connector
- Maven

## Estructura del proyecto

```text
demo06/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/tecsup/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Arquitectura utilizada

El proyecto sigue una arquitectura por capas:

- **Controller:** recibe las solicitudes HTTP y devuelve respuestas.
- **Service:** contiene la lógica de negocio.
- **Repository:** gestiona el acceso a la base de datos.
- **Model:** representa las entidades persistentes.
- **DTO:** transporta datos de entrada con validaciones.
- **Exception:** centraliza el manejo de errores.

## Entidades principales

### Producto

Permite registrar productos con:

- nombre
- precio
- stock

### Usuario y Perfil

Se implementa una relación **uno a uno** entre `Usuario` y `Perfil`.

Un usuario contiene:

- username
- password
- perfil

### Curso

Permite registrar cursos con:

- nombre
- créditos

### Estudiante

Permite registrar estudiantes con:

- nombre
- email
- cursos inscritos

Existe una relación **muchos a muchos** entre `Estudiante` y `Curso`.

## Validaciones implementadas

El proyecto usa anotaciones de validación para asegurar datos correctos, por ejemplo:

- Campos obligatorios con `@NotBlank`
- Correos válidos con `@Email`
- Valores positivos con `@Positive`
- Valores mínimos con `@Min`
- Validación de objetos anidados con `@Valid`

Además, existe un `GlobalExceptionHandler` para devolver errores estructurados cuando los datos enviados no son válidos o cuando se produce una operación inválida.

## Configuración actual

En `application.properties` se configuró:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.h2.console.enabled=true
```

Esto indica que:

- Hibernate actualiza automáticamente las tablas
- Se muestran consultas SQL en consola
- Se desactiva Open Session in View
- La consola de H2 está habilitada

## Ejecución del proyecto

### Requisitos

- Java 25
- Maven o Maven Wrapper

### Ejecutar con Maven Wrapper

En Windows:

```bash
.\mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

### Ejecutar pruebas

```bash
./mvnw test
```

En Windows también puedes usar:

```bash
.\mvnw.cmd test
```

## Endpoints principales

### Productos

Base URL:

```text
/api/productos
```

Operaciones:

- `GET /api/productos` - listar productos
- `POST /api/productos` - registrar producto
- `GET /api/productos/{id}` - obtener producto por id
- `PUT /api/productos/{id}` - actualizar producto
- `DELETE /api/productos/{id}` - eliminar producto

Ejemplo de registro:

```json
{
  "nombre": "Laptop",
  "precio": 3500.0,
  "stock": 8
}
```

### Usuarios

Base URL:

```text
/api/usuarios
```

Operaciones:

- `GET /api/usuarios` - listar usuarios
- `POST /api/usuarios` - registrar usuario
- `GET /api/usuarios/{id}` - obtener usuario por id
- `DELETE /api/usuarios/{id}` - eliminar usuario

Ejemplo de registro:

```json
{
  "username": "rony",
  "password": "123456",
  "perfil": {
    "nombres": "Rony Widmer",
    "apellidos": "Bellido Chambi",
    "email": "rony@correo.com"
  }
}
```

### Cursos

Base URL:

```text
/api/cursos
```

Operaciones:

- `GET /api/cursos` - listar cursos
- `POST /api/cursos` - crear curso

Ejemplo:

```json
{
  "nombre": "Desarrollo Web",
  "creditos": 4
}
```

### Estudiantes

Base URL:

```text
/api/estudiantes
```

Operaciones:

- `GET /api/estudiantes` - listar estudiantes
- `POST /api/estudiantes` - crear estudiante
- `POST /api/estudiantes/{estudianteId}/cursos/{cursoId}` - agregar curso a estudiante
- `DELETE /api/estudiantes/{estudianteId}/cursos/{cursoId}` - quitar curso de estudiante
- `GET /api/estudiantes/{estudianteId}/cursos` - listar cursos de un estudiante

Ejemplo de creación:

```json
{
  "nombre": "Ana Torres",
  "email": "ana@correo.com",
  "cursoIds": [1, 2]
}
```

## Aprendizajes aplicados

Con este proyecto se ponen en práctica los siguientes temas del curso:

- Creación de servicios web REST
- Uso de Spring Boot para acelerar el desarrollo
- Persistencia de datos con JPA
- Relaciones entre entidades
- Validación de entradas
- Organización del código por responsabilidades
- Pruebas básicas del proyecto

## Conclusión

Este proyecto representa una práctica completa de desarrollo de aplicaciones web con Spring Boot, ya que integra componentes esenciales de una API moderna: rutas REST, validaciones, persistencia, relaciones entre tablas y manejo de errores. Es una base sólida para seguir ampliando funcionalidades como autenticación, documentación con Swagger, seguridad o conexión completa a MySQL.
