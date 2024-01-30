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

***Troubleshooting:*** In case that a problem with sql dialect arises, it is due to the fact that database is not yet ready even though spring boot depends on it in docker-compose.yml (we need a proper health check there)... try docker compose up once more - it should fix it

