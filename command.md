
docker run -it --rm gradle-wrapper bash
docker run -it --rm long-running-gradle bash


docker exec -it priceless_meninsky git pull
docker exec -it priceless_meninsky gradle build -x test
docker container stop priceless_meninsky