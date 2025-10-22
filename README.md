# Backend - Parcial 2 (Spring Boot)

Proyecto para el parcial de programación. La consigna del parcial es construir una API REST con Spring Boot que permita:

- Listar cursos, profesores y estudiantes.
- Crear nuevos cursos con su profesor.
- Asignar estudiantes a un curso.
- Devolver la lista de cursos en los que está un estudiante.

## Descripción rápida

Esta API expone recursos para gestionar Profesores, Estudiantes y Cursos. Las relaciones y notas de la consigna se implementan de la siguiente forma:

- Profesor: datos básicos (id, nombre, email).
- Estudiante: datos básicos (id, nombre, matrícula).
- Curso: nombre, profesor asociado y lista de estudiantes.
- Un Profesor puede tener varios Cursos. (en Curso: @ManyToOne hacia Profesor)
- Un Curso puede tener varios Estudiantes y un Estudiante puede pertenecer a varios Cursos. (relación @ManyToMany)

La aplicación está preparada para ejecutarse con una base en memoria (H2) para facilitar pruebas locales.

## Tecnologías

- Java 17 (o la versión configurada en el proyecto)
- Spring Boot (Web, Data JPA)
- H2 Database (desarrollo)
- Maven o Gradle (según configuración del proyecto)
- (Opcional) Lombok para reducir boilerplate

## Modelos (resumen)

- Profesor
  - id (Long)
  - nombre (String)
  - email (String)

- Estudiante
  - id (Long)
  - nombre (String)
  - matricula (String)

- Curso
  - id (Long)
  - nombre (String)
  - profesor (Profesor)
  - estudiantes (List<Estudiante>)

Relaciones:
- Curso -> Profesor : @ManyToOne
- Curso <-> Estudiante : @ManyToMany

## Endpoints principales

Base path sugerido: /api

- Profesores
  - GET /profesor
    - Lista todos los profesores.
  - GET /profesor/findById?id={id}
    - Obtener un profesor por id.
  - POST /profesor
    - Crear un profesor.
    - Body (ejemplo): { "nombre": "Juan Perez", "email": "juan@example.com" }

- Estudiantes
  - GET /estudiante
    - Lista todos los estudiantes.
  - POST /estudiante
    - Crear un estudiante.
    - Body (ejemplo): { "nombre": "María López", "matricula": "2025001" }
  - GET /estudiante/getCursosByEstudianteId?estudianteId=3
    - Devuelve la lista de cursos en los que está el estudiante indicado.

- Cursos
  - GET /curso
    - Lista todos los cursos (incluye profesor y lista de estudiantes).
  - GET /curso/findById?id={id}
    - Obtener un curso por id.
  - POST /curso
    - Crear un curso y asociarle un profesor.
    - Body (ejemplo):
      {
        "nombre": "Programación I",
        "profesorId": 1
      }
    - Alternativa: permitir enviar un objeto profesor embebido según implementación.
  - POST /curso/addEstudiante?cursoId={cursoId}&estudianteId={estudianteId}
    - Asignar (matricular) un estudiante a un curso.
    - Devuelve el curso actualizado o un status 204/200 según implementación.

Notas:
- Se recomienda validar que profesorId y estudianteId existan antes de crear/actualizar relaciones.
- Para eliminar asignaciones o recursos, pueden agregarse endpoints DELETE si es necesario.

## Ejemplos (curl)

Crear profesor:
curl -X POST -H "Content-Type: application/json" -d '{"nombre":"Juan Perez","email":"juan@example.com"}' http://localhost:8080/api/profesores

Crear estudiante:
curl -X POST -H "Content-Type: application/json" -d '{"nombre":"María López","matricula":"2025001"}' http://localhost:8080/api/estudiantes

Crear curso con profesor:
curl -X POST -H "Content-Type: application/json" -d '{"nombre":"Programación I","profesorId":1}' http://localhost:8080/api/cursos

Asignar estudiante a curso:
curl -X POST http://localhost:8080/curso/addEstudiante?cursoId=1&estudianteId=1

Listar cursos de un estudiante:
curl http://localhost:8080/estudiante/getCursosByEstudianteId?estudianteId=1

## Validaciones y manejo de errores

- Verificar existencia de recursos referenciados (profesorId, estudianteId) y devolver 404 si no existen.
- Validar campos obligatorios (p. ej. nombre no vacío).
- Manejar errores de integridad (duplicados, relaciones inconsistentes) con respuestas claras y códigos HTTP adecuados (400, 404, 409, 500).

## Cómo ejecutar (local)

1. Clonar el repositorio
   git clone https://github.com/Ednesor/backend-parcial-2-spring.git
   cd backend-parcial-2-spring

2. Compilar y ejecutar (Maven)
   mvn clean spring-boot:run

   O si usa Gradle:
   ./gradlew bootRun

3. La API quedará disponible por defecto en:
   http://localhost:8080

4. (Opcional) Consola H2:
   - Si está habilitada en application.properties:
   - http://localhost:8080/h2-console
   - JDBC URL por defecto: jdbc:h2:mem:testdb
   - Usuario/Password según configuración

## Recomendaciones de implementación

- Usar DTOs para entrada/salida y evitar exponer entidades JPA directamente.
- Controlar la serialización para evitar ciclos en relaciones bidireccionales (usar @JsonManagedReference / @JsonBackReference o DTOs).
- Implementar pruebas unitarias y de integración para los controladores y servicios.
- Manejar transacciones en servicios que modifiquen varias entidades.

## Estructura sugerida del proyecto

- src/main/java/...
  - controller (API REST controllers)
  - service (lógica de negocio)
  - repository (Spring Data JPA)
  - model/entity (entidades JPA)
  - dto (objetos de transferencia)
- src/main/resources
  - application.properties (configuración)
  - data.sql (opcional: datos de prueba)

## Autor

Edgardo Funes — Parcial 2

## Licencia

Licencia del proyecto MIT.
