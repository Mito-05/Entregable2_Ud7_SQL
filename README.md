# Sistema de Gestión de Gimnasios

**Autor:** Manuel Vasco López

## Descripción del Proyecto
Este proyecto es una aplicación desarrollada en **Java 25** diseñada para la gestión integral de una red de gimnasios y sus respectivos socios. El sistema permite realizar operaciones de persistencia sobre centros deportivos y clientes, facilitando su almacenamiento, asignación y mantenimiento de datos de manera eficiente.

La arquitectura se basa en el estándar **Jakarta Persistence (JPA)**, utilizando **ObjectDB** como motor de base de datos orientada a objetos, lo que permite un manejo sencillo de las relaciones complejas de muchos a muchos (`@ManyToMany`) sin necesidad de configurar servidores SQL tradicionales.

## Estructura del Código
El proyecto sigue una arquitectura modular para separar claramente las responsabilidades:

* **`modelo/Gimnasio.java`**: POJO (Plain Old Java Object) que define la entidad `Gimnasio` y su estructura base.
* **`modelo/Socio.java`**: POJO que define la entidad `Socio`, actuando como la parte propietaria de la relación bidireccional.
* **`dao/GimnasioDAO.java`**: Capa de acceso a datos (Data Access Object) para la entidad gimnasio. Encapsula operaciones CRUD y consultas JPQL avanzadas (agrupaciones y filtrados complejos).
* **`dao/SocioDAO.java`**: Capa de acceso a datos para la entidad socio. Controla las inscripciones, desvinculaciones y cálculos estadísticos de los usuarios.
* **`Main.java`**: Punto de entrada de la aplicación. Gestiona la conexión, orquesta el flujo operacional y ejecuta las demostraciones de manera controlada.

## Funcionalidades Implementadas
El sistema permite realizar las siguientes operaciones:

1. **Gestión de registros (CRUD):** Inserción, actualización y borrado de gimnasios y socios mediante identificadores únicos.
2. **Control de Afiliaciones:** Asignación y baja de socios en gimnasios específicos manteniendo la consistencia de datos.
3. **Consultas de filtrado:**
    * Obtención de los socios apuntados a un gimnasio concreto.
    * Obtención de los gimnasios asociados a un socio específico.
    * Filtrado de gimnasios con baja ocupación (menos de 10 socios).
    * Búsqueda del gimnasio más económico por ciudad especificada.
    * Listado de socios sin ninguna afiliación activa.
4. **Estadísticas y Análisis:**
    * Recuento del volumen de socios inscritos por cada gimnasio.
    * Listado de los 5 gimnasios con la cuota mensual más elevada.
    * Cálculo de la media de edad global de todos los socios registrados.

## Persistencia de Datos
El proyecto utiliza un archivo local `fitness.odb` para almacenar toda la información de los objetos de manera directa.

- **Idempotencia y Estabilidad:** El código está diseñado para cumplir de manera estricta las directrices de ejecución repetitiva. Las operaciones de prueba limpian sus propios registros residuales utilizando las mismas instancias en memoria para el ciclo de inserción, edición y borrado. Esto garantiza que los resultados de los análisis analíticos permanezcan constantes e invariables en cada ejecución del programa.

## Requisitos de Ejecución
- Java 25 o superior.
- Librerías de Jakarta Persistence.
- ObjectDB configurado en el entorno del proyecto.

---
*Desarrollado por Manuel Vasco López.*