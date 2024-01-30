Database 
==


Pull Docker image mysql / latest
----
`docker pull mysql`


Run Mysql on docker
----
```
docker run --name mysql-container --network cardapi-service -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
```

Create Schema cardapi
--
```roomsql
CREATE SCHEMA `cardapi` DEFAULT CHARACTER SET utf8 ;
```

Spring Boot Service
==


Create JAR file
--
`mvnw install`

How to create Docker Image:
--
```
docker build --build-arg JAR_FILE=target/*.jar -t cardapi.com/cardapi .
```
How to run spring boot docker image
--
```
docker run --name spring-boot-container --network cardapi-service -p 8080:8080 cardapi.com/cardapi
```





