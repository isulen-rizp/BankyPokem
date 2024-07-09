# BankyPokem


Instrucciones de Docker 
1. Antes de crear la imagen de Docker se debe construir el proyecto con maven para generar el jar en el directorio target
    mvn clean install o mvn clean package 
2. Despues dirigirse al directorio donde se encuentra el Dockerfile y correr el comando 
    docker build -t bankaya-pokem .
3. Para correr la imagen consutrida ejecutar el comando:
    docker run -p 8080:8080 bankaya-pokem
    
Swagger URL:
http://localhost:8080/swagger-ui.html

Endpoint de WSDL:
http://localhost:8081/ws/pokemon?wsdl