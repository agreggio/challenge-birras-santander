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

Nota: El micro-servicio pom-enablers-interaction-admin-message se representa en el modelo pero no esta desarrollado, su funcionalidad es tomar los mensajes de la cola AMQ y enviar email a los invitados a la MeetUp.

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

En caso de que falle la ejecucion mediante docker-compose, debera compilar y ejecutar cada micro-servicio de forma independiente.


challenge-birras-santander-meet-up
```code
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/santander_db?createDatabaseIfNotExist\=true&serverTimezone\=UTC&useLegacyDatetimeCode\=false
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=Santander123
KEY_WEATHER=7d1046095emsh5c40257db3ecc8fp14f89bjsn370c2ada41d8
HOST_WEATHER=community-open-weather-map.p.rapidapi.com
URL_WEATHER=https://community-open-weather-map.p.rapidapi.com/forecast/daily?q\=argentina&ar&units\=metric&cnt\=16
FIXED_RATE=600000
AMQ_USER=admin
AMQ_PASS=admin
RESEND_MQ_DELAY=1000000
MQ_QUEUE=meetUp
MANAGER_AMQ_URL=tcp://localhost:61616
```

challenge-birras-santander-login
```code
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/santander_db?createDatabaseIfNotExist\=true&TZ\=America/Argentina/Buenos_Aires
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=Santander123
```

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

[Collection](https://github.com/agreggio/challenge-birras-santander/blob/develop/postman/challenge.birras.santander.postman_collection.json)

