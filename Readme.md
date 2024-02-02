Create JAR file and start Docker 
--
In root of project run:
```
mvn package -DskipTests
```

and then 
```
docker compose up
```

- The database service starts at port: 3307 and has the schema cardapi created
- The spring boot service starts at port: 8080

For Development Cycle to rebuild images we do the below:
==
```
mvn clean
mvn package -DskipTests
docker-compose pull
docker-compose up --force-recreate --build -d
docker image prune -f
docker compose up
```
credit: https://stackoverflow.com/questions/49316462/how-to-update-existing-images-with-docker-compose