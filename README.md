# Challenge-Birras-Santander

Proyecto entrevista tecnica Santander Tecnologias

### Tecnología Utilizada
- Intellij IDE
- Java 8
- Maven
- Spring Boot
- Mysql 8.0
- Docker
  - Docker-compose
- ActiveMQ

### Modelo de Arquitectura

Se presenta el siguiente modelo de arquitectura, como solucion a generar independencia y escalabilidad entre los distintos micro-servicios.

![arquitectura](https://github.com/agreggio/challenge-birras-santander/blob/develop/uml/challengeMeetUps.png)

Nota: El micro-servicio pom-enablers-interaction-admin-message se representa en el modelo pero no esta desarrollado, su funcionalidad es tomar los mensajes de la cola AMQ y enviar correo a los invitados a la MeetUp.

### Modelo de datos

![modelo](https://github.com/agreggio/challenge-birras-santander/blob/develop/uml/model.png)

### Ejecucion del proyecto

#### Requisitos previos
Para ejecutar el proyecto de forma local se debe tener previamente instalado las siguientes herramientas
- Git
- Docker
- Docker-compose
- Maven
- Java

1. Clonar proyecto 

git clone https://github.com/agreggio/challenge-birras-santander.git

2. Ejecutar comando bash (Linux)

bash deploy.sh

3. Debe esperar unos minutos para que descarge las imagenes docker, compile y disponibilice los endpoint

#### Alternativa de ejecucion

En caso de que falle la ejecucion mediante docker-compose, debera compilar y ejecutar cada proyecto de forma independiente. En cada proyecto se encuentras las variables de entorno para su configuracion

#### Lista de servicios

##### challenge-birras-santander-login
- http://localhost:8080/swagger-ui.html

##### challenge-birras-santander-meet-up
- http://localhost:8081/swagger-ui.html

##### Broker AMQ
- http://localhost:8161/admin

##### MyPhpAdmin
- http://localhost:8085/

### Collection Postman



