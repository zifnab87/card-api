mvn clean
mvn package -DskipTests
docker-compose pull
docker-compose up --force-recreate --build -d
docker image prune -f
docker compose up