

Create JAR file
==
mvnw install

How to create Docker Image:
==
docker build --build-arg JAR_FILE=target/*.jar -t cardapi.com/cardapi .

How to run docker image
==
docker run -p 8080:8080 cardapi.com/cardapi