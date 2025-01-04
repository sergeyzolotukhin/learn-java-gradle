### How to run this test

1. Сорy to user home testcontainers.properties
2. Run docker engine on VM: D:\projects-java\infrastructure\docker-engine-shell\Vagrantfile
2. Build container with application: learn-java\framework\spring\spring-web-rest\Dockerfile
3. Run test.

### This project is based on the following tutorial

https://java.testcontainers.org/quickstart/junit_5_quickstart/

### Getting started with Testcontainers
https://callistaenterprise.se/blogg/teknik/2020/10/08/getting-started-with-testcontainers/

### Configure TestContainers to Use Remote Docker hosts

https://datmt.com/backend/configure-testcontainers-to-use-remote-docker-hosts/

### Testcontainers Custom configuration
https://java.testcontainers.org/features/configuration/

### How to check port is open or close on windows
https://kb.synology.com/ru-ru/DSM/tutorial/Whether_TCP_port_is_open_or_closed
telnet 127.0.0.1 2375

### Configure remote access for Docker daemon
https://docs.docker.com/config/daemon/remote-access/

### Ryuk the Resource Reaper
https://blog.worldline.tech/2023/01/04/ryuk.html

### Java API client for Docker
https://github.com/docker-java/docker-java
https://www.baeldung.com/docker-java-api
https://docs.docker.com/engine/api/sdk/

### Gradle-Docker-Plugin
https://spring.io/guides/topicals/spring-boot-docker/
https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/

### Deploy a registry server
https://docs.docker.com/registry/deploying/
https://habr.com/ru/articles/709988/
https://www.baeldung.com/ops/docker-private-registry

###  data pollution between tests
https://github.com/testcontainers/testcontainers-java/discussions/4845
https://www.reddit.com/r/PostgreSQL/comments/ex4q40/comment/fg68q59/

### How to run Postgres for testing in Docker 
https://marcopeg.com/how-to-run-postgres-for-testing-in-docker/

###  Other

https://stackoverflow.com/questions/62298539/how-to-run-testcontainers-on-a-different-host

org.testcontainers.utility.TestcontainersConfiguration.loadConfiguration
org.testcontainers.dockerclient.DockerClientProviderStrategy.getFirstValidStrategy
org.testcontainers.utility.TestcontainersConfiguration.readProperties