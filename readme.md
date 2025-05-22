Mini juego multiplayer en websockets.  

Websockets con stomp.
Manjeo de usuarios.
Streams y paralelismo.
Almacenamiento en DB de estadísticas y atributos de personajes.

## Temas Aplicados

* [x] API REST con Spring Boot.
  * Para input del usuario.
  * Selección y creación de personajes.
  * Consulta de estadísticas.
* [x] Controladores, Servicios, Repositorios.
  * El acceso al modelo se hace a través de esas capas.
* [x] Inyección de Dependencias (Spring).
  * Las hace Spring automáticamente en los atributos @Autowired.
* [x] Configuración de Beans (Spring).
  * Se inicializa el bean del web socket.
* [x] Unit Testing (JUnit, Mockito).
  * Pruebas básicas a los servicios, haciendo mock de los repositorios, y a las validaciones de jakarta.validation.
* [] Manejo de Excepciones.
  * El ControllerAdvice de null excepcion que vimos en el curso; se prueba su ejecución en un unit test.
* [x] Collections y Genéricos.  
  * Uso de genéricos básico, no se creó ninguna clase ni método genéricos.
  * Uso de ConcurrentHashMap para acceso concurrente y consultas O(1).
* [x] Lambdas y Streams.
  * GameState es un stream de todos los PlayerStates.
* [] Patrón de Diseño (Factory, Repository, DTO).
  * DTO. Faltó usar la librería de mapstruct y crear un DTO apropiado para create.
* [x] Streams Paralelas
  * Para detección de colisiones, cada frame se prueba si un personaje colisiona un rectángulo del escenario.
* [ ] Programación Modular.
* [ ] Implementación de seguridad básica (Spring Security).  
* [x] Sockets
  * El ciclo del juego corre con websockets con Stomp, que manda el estado del juego a todos los clientes 30 veces por segundo.
* [ ] Programación Java API.
