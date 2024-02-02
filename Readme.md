Description
--
This is an assignment involving a cost matrix per country with corresponding endpoints for CRUD under `/country-costs`.
Another endpoint `/payment-cards-cost` is provided that accepts a PAN card number and sends only its first digits (BIN part) to an external endpoint at https://bintable.com/get-api that sends back information along with its country origin.
**Note**: The https://bintable.com/get-api seems that it has outdated information showing my Greek MasterCard BIN as one coming from US (!)

Architecture Decision
--
The implementation is based on light version of Domain Driven Design principles following loosely the Ports & Adapters / Hexagonal Architecture. 

The work is influenced by `Tom Hombergs` work on https://leanpub.com/get-your-hands-dirty-on-clean-architecture and https://www.youtube.com/watch?v=cPH5AiqLQTo

The reason of this choice is that it structures the code in a more domain-rich way defining each use case is a separate class with the smallest possible dependencies, minimizing coupling (without introducing much duplication) that usually comes with MVC / N-Tier architectures.

Testing is becoming easier since the dependencies are usually very small (e.g specific queries/ports) so mocking is easier leading to cleaner tests.

This architecture is definitely a great choice for complex domains and systems that need to evolve in a modular monolith or a set of microservices. Not a great fit for CRUD apps with little to no business logic but still as projects evolve they tend to accumulate business logic in those cases too.

Contrary to pure or heavier Hexagonal Architectures, it was decided that controllers won't be adapters and ports since we won't need any other kind of endpoints (e.g file writing etc).

**Note**: Spring Boot and JPA, Jackson have their own constraints that don't play that well with DDD e.g they always need empty constructors on Entities/Value Objects which breaks a lot of principles of pure DDD

Finally for simplicity and less code, Domain Entities and Jpa/ORM Entities are handled in the same classes which is also something to avoid in pure DDD.

Features:
---
- OpenJDK 21, Maven and Docker (Requirements)
- MySQL and Spring Boot Dockerization (through Docker Compose)
- Dockerization
- Lombok for less boilerplate code
- Flyway migrations
- Spring Doc OpenApi Documentation

Future Work:
---
- **Security** Considerations (protecting the api with apikeys and the rest of endpoints with a token e.g JWT ). Removing temp apiKey and all the sensitive credentials from application.yaml and docker_compose.yml
- **Logging** Considerations (we could add logging in GlobalExceptionAdvice to log all the errors from all the API endpoints)
- **Scalability** Considerations so that we can have up to 7000 requests per minute. We need to do JMeter tests on a production-grade machine to find the limits.
  We could introduce caching for a minute on the costs matrix in case we don't really care for stale 1min data. We could also cache or even prefetch the BIN, country pairs so we don't need to hit external endpoints
- We could enable **High Availability** of this service having multiple instances loaded, yet we are constrained for CRUD write operations from MySQL Database and external network round trip to end point.

API Documentation
--
http://localhost:8080/swagger-ui/index.html

Creation of application.yaml
--
please rename `application.dist.yaml` to `application.yaml` and replace `API_KEY` from my email attachment or your own `API_KEY` for https://api.bintable.com/v1/

Create JAR file and start Docker 
--
In root of project run:
```
mvn package -DskipTests
```

and then run
```
docker compose up
```

- The database service starts at external port: `3307` and has the schema cardapi created. The data is attached as a volume in a simbling to the git repository directory `../mysql-data`
- The spring boot service starts at external port: `8080`

For Docker Development Cycle (not needed for local) to rebuild images we run the commands below:
--
```
mvn clean
mvn package -DskipTests
docker-compose pull
docker-compose up --force-recreate --build -d
docker image prune -f
docker compose up
```
or
```
sh refresh-docker.sh
```

credit: https://stackoverflow.com/questions/49316462/how-to-update-existing-images-with-docker-compose

Troubleshooting
--
I had an issue in some cases with MySql in Docker that would create ROOT user without password, disallowing connections, so I had to manually remove folder `../mysql-data` and then run docker after pruning volumes, so it creates the user properly
```
docker volume prune && sh refresh-docker.sh
```
