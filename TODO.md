#### Type-safe Configuration Properties
https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.typesafe-configuration-properties

#### Tasks

* create remote gradle cache
* build app from scratch with remote cache

* copy jenkins docker
* create build agent
* run simple job on agent
* 

https://spring.io/blog/2022/10/21/introducing-spring-modulith

### Spring - 24. Externalized Configuration

https://docs.spring.io/spring-boot/docs/1.5.22.RELEASE/reference/html/boot-features-external-config.html

https://www.codeproject.com/Articles/1258153/SCF-Simple-Configuration-Facade

PropertySources
PropertySource


ConfigurableEnvironment
    MutablePropertySources
        CompositePropertySource
            SimpleCommandLinePropertySource


### QR code

https://blog.aspose.com/barcode/scan-qr-code-in-java/
https://stackoverflow.com/questions/18863466/reading-qrcode-with-zxing-in-java
https://www.callicoder.com/qr-code-reader-scanner-in-java-using-zxing/
https://www.dynamsoft.com/codepool/java-barcode-command-line-gui-web.html

### 44. Creating your own auto-configuration

https://docs.spring.io/spring-boot/docs/1.5.22.RELEASE/reference/html/boot-features-developing-auto-configuration.html

### How to Use the Postgres Docker Official Image

https://habr.com/ru/articles/578744/

### Docker data-only-container

https://medium.com/@rasheedamir/docker-good-bye-data-only-container-pattern-a28f90493a5a
https://stackoverflow.com/questions/40090232/create-a-pure-data-image-in-docker

#### ActiveMQ Artemis Docker images.

https://activemq.apache.org/components/artemis/documentation/latest/docker.html

#### Postgres on Docker. How can I create a database and a user?

https://stackoverflow.com/questions/26201274/postgres-on-docker-how-can-i-create-a-database-and-a-user

FROM postgres
USER postgres
RUN /etc/init.d/postgresql start &&\
psql --command "CREATE USER user WITH SUPERUSER PASSWORD 'user';" &&\
createdb -O user app

Use pg_ctl with the -w flag so the command will finish when the server has started. No more wondering about whether we have waited long enough. And we can actually stop the server the same way

sudo -u postgres pg_ctl start -w -D ${PGDATA}

sudo -u postgres psql --command "CREATE USER user WITH SUPERUSER PASSWORD 'user';" &&\
sudo -u postgres createdb -O user app

sudo -u postgres pg_ctl stop -w

https://dev.to/aws-builders/restore-postgresql-database-in-a-docker-container-3h85
https://www.postgresql.org/docs/current/server-start.html

#### Enable TCP port 2375 for external connection to Docker

https://gist.github.com/styblope/dc55e0ad2a9848f2cc3307d4819d819f
https://developer.hashicorp.com/vagrant/docs/provisioning/docker

#### How to use all CPU and RAM on the VM ?

#### Vagrant DNS
https://github.com/vagrant-landrush/landrush/blob/master/README.adoc

https://zwischenzugs.com/2017/10/27/ten-things-i-wish-id-known-before-using-vagrant/

docker image save root-docker-file:latest > root-docker-file.tar

#### How to download dependencies in gradle

https://proandroiddev.com/gradle-cache-your-builds-best-friend-4970ad32420e
https://github.com/zwbetz-gh/reuse-the-gradle-dependency-cache-with-docker

gradle dependencies
https://discuss.gradle.org/t/force-resolving-and-downloading-all-dependencies/35942
https://discuss.gradle.org/t/replacement-of-gradle-offline-dependencies-plugin/36159/2
https://stackoverflow.com/questions/23025433/how-to-clear-gradle-cache

https://www.baeldung.com/gradle-offline-mode

https://github.com/keeganwitt/docker-gradle

GRADLE_HOME

https://kodekloud.com/blog/keep-docker-container-running/#:~:text=%231%20Using%20the%20sleep%20infinity%20command,-A%20straightforward%20method&text=The%20sleep%20command%20is%20designed,is%20always%20an%20active%20process.
https://www.baeldung.com/ops/running-docker-containers-indefinitely

#### Build Cache Node User Manual

https://docs.gradle.com/build-cache-node/
https://hub.docker.com/r/gradle/build-cache-node
https://docs.gradle.org/current/userguide/build_cache.html#sec:build_cache_configure_remote

#### Using Gradle to Build and Deploy Docker Containers

https://medium.com/@AlexanderObregon/using-gradle-to-build-and-deploy-docker-containers-d4738cada039
https://bmuschko.github.io/gradle-docker-plugin/current/user-guide/
https://tomgregory.com/gradle/bmuschko-docker-gradle-plugin-review/
https://tomgregory.com/gradle/automating-docker-builds-with-gradle/
https://janetalkscode.wordpress.com/2018/05/06/docker-adventures-palantir-docker-plugin/

https://codeal.medium.com/guide-simple-and-powerful-integration-tests-with-gradle-and-docker-compose-7a27bd06a0cd

#### Gradle Docker plugins

https://github.com/bmuschko/gradle-docker-plugin
https://github.com/palantir/gradle-docker
https://github.com/Transmode/gradle-docker


#### Docker build context

https://www.cloudbees.com/blog/3-different-ways-to-provide-docker-build-context
https://docs.docker.com/build/building/context/

#### Git server
https://railsware.com/blog/taming-the-git-daemon-to-quickly-share-git-repository/
https://github.com/sdelrio/git-daemon
https://www.thegeekdiary.com/git-daemon-a-really-simple-server-for-git-repositories/

https://github.com/jkarlosb/git-server-docker

https://linuxhint.com/setup_git_http_server_docker/

GitLab, Gitea, Bitbucket or Gogs

#### Composite build

https://docs.gradle.org/current/userguide/resolution_rules.html

https://github.com/AlexMAS/gradle-composite-build-example/tree/master
https://github.com/gradle/multi-project-composite-gradle-plugins-builds
https://docs.gradle.org/current/userguide/composite_builds.html
https://ncorti.com/blog/gradle-plugins-and-composite-builds
https://medium.com/codequest/gradle-multi-project-build-substituting-jar-dependencies-with-local-projects-4a5323f8680b

https://github.com/square/gradle-dependencies-sorter/tree/main

#### Docker log limit
daemon.json -> {“log-driver”:“json-file”,“log-opt”:[{“max-size”:“2m”},{“max-file”:“5”}],“log-level”:“info”}

#### Session expire
https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html
https://spring.io/guides/tutorials/spring-security-and-angular-js

#### Git 

https://www.atlassian.com/git/tutorials/refs-and-the-reflog#:~:text=A%20refspec%20maps%20a%20branch,push%20and%20git%20fetch%20behavior.

#### Jenkins

https://code-partners.com/understanding-cloudbees-workspace-caching-in-jenkins-pipelines/
https://argus-sec.com/blog/engineering-blog/how-you-can-use-git-reference-repository-to-reduce-jenkins-disk-space/
https://www.jenkins.io/projects/gsoc/2020/project-ideas/git-plugin-caching/
https://plugins.jenkins.io/jobcacher/
https://www.aviator.co/blog/how-to-optimize-jenkins-pipeline-performance/

https://www.jenkins.io/doc/book/pipeline/docker/
https://www.jenkins.io/doc/book/pipeline/syntax/#agent
https://plugins.jenkins.io/git/

#### limit of a Docker container

https://www.quora.com/What-is-the-maximum-size-limit-of-a-Docker-container#:~:text=Docker%20container%20has%20a%20default,%22storage%2Dopt%22%20flag.
https://docs.docker.com/reference/cli/docker/container/run/#storage-opt

##### docker volume with limited size of disk space

https://stackoverflow.com/questions/52089499/create-docker-volume-with-limited-size
https://docs.docker.com/reference/cli/docker/volume/create/

##### docker host with limited size of disk space

https://opvizor.com/blog/prevent-docker-host-to-run-out-of-disk-space
https://www.baeldung.com/linux/docker-fix-no-space-error

#### Vagrant disk

https://developer.hashicorp.com/vagrant/docs/disks/virtualbox/common-issues
https://stackoverflow.com/questions/45038248/vagrant-detach-vdi-file-before-destroy-or-just-dont-remove-it

#### linux LVM

https://dev.to/shankarsurya035/how-to-create-lvm-partition-in-linux-dgo

#### Maven in dicker

https://hub.docker.com/_/maven
https://github.com/carlossg/docker-maven

#### Building a Vagrant Box from Start to Finish

https://www.engineyard.com/blog/building-a-vagrant-box-from-start-to-finish/

#### Java Agent

https://www.baeldung.com/java-instrumentation

#### Gradle vagrant
https://github.com/bmuschko/gradle-vagrant-plugin
https://linchpiner.github.io/gradle-devops-plugins.html

#### Gradle Build Hooks
https://www.oreilly.com/library/view/gradle-beyond-the/9781449373801/ch03.html
https://docs.gradle.org/current/userguide/init_scripts.html

#### Gradle Using local gradle plugin in a composite build
https://stackoverflow.com/questions/72733760/using-local-gradle-plugin-in-a-composite-build-using-groovy-convention-scripts
https://docs.gradle.org/current/samples/sample_sharing_convention_plugins_with_build_logic.html

#### gradle standalone plugin
https://docs.gradle.org/current/userguide/plugins.html

#### Gradle Docker
https://stackoverflow.com/questions/58441158/persisting-gradle-daemon-after-docker-exec-it-container-name-gradle-build

#### How can i checkout particular commit from the git scm from jenkins pipeline

https://stackoverflow.com/questions/68892532/how-can-i-checkout-particular-commit-from-the-git-scm-from-jenkins-pipeline
https://stackoverflow.com/questions/62926385/jenkins-git-get-all-commits-in-the-last-push-in-pull-requests


#### This plugin allows containers to be dynamically provisioned as Jenkins nodes using Docker. It is a Jenkins Cloud plugin for Docker.

https://www.jenkins.io/doc/book/installing/docker/
https://github.com/jenkinsci/docker/blob/master/README.md

https://github.com/jenkinsci/docker-plugin
https://github.com/jenkinsci/ssh-agents-plugin/blob/main/doc/CONFIGURE.md
https://github.com/jenkinsci/agent-setup-plugin
https://github.com/jenkinsci/docker-ssh-agent
https://github.com/jenkinsci/docker-commons-plugin
https://github.com/jenkinsci/docker-workflow-plugin
https://github.com/jenkinsci/docker-agent
https://github.com/jenkinsci/docker
https://github.com/jenkinsci/docker-build-step-plugin
https://github.com/jenkinsci/docker-fixtures

#### Git Jenkins
https://github.com/jenkinsci/git-plugin
https://github.com/jenkinsci/git-client-plugin
https://github.com/jenkinsci/git-server-plugin

#####

https://github.com/jenkinsci/junit-realtime-test-reporter-plugin
https://github.com/jenkinsci/dark-theme-plugin
https://github.com/jenkinsci/ansible-plugin
https://github.com/jenkinsci/gradle-plugin
https://github.com/jenkinsci/database-plugin
https://github.com/jenkinsci/parameterized-trigger-plugin
https://github.com/jenkinsci/plugin-installation-manager-tool
https://github.com/jenkinsci/groovy-plugin
https://github.com/jenkinsci/allure-plugin
https://github.com/jenkinsci/ansicolor-plugin
https://github.com/jenkinsci/parallel-test-executor-plugin
https://github.com/jenkinsci/winrm-client-plugin

#### Window Task Scheduler

https://stackoverflow.com/questions/39745154/how-to-create-a-scheduler-task-in-window-task-scheduler-using-java

https://plugins.jetbrains.com/plugin/8403-breakpoints-manager
https://github.com/marcin-bukowiecki/breakpoint-manager-plugin/tree/master

#### Debugging dependencies in Gradle. How to use dependencyInsight ?

set JAVA_HOME=C:\soft\java-20
gradlew.bat  :learn-java:library:lib-activemq-artemis:dependencyInsight --configuration testRuntimeClasspath --dependency junit-platform-commons

https://proandroiddev.com/debugging-dependencies-in-gradle-54c8be444849
https://solidsoft.wordpress.com/2014/11/13/gradle-tricks-display-dependencies-for-all-subprojects-in-multi-project-build/

https://discuss.gradle.org/t/custom-task-to-execute-all-unit-tests-in-all-subprojects-aka-modules/47604
https://discuss.gradle.org/t/executing-tasks-by-name-on-multi-project/46046
https://stackoverflow.com/questions/26509427/execute-gradle-task-on-sub-projects

##### not working 
gradlew.bat  dependencyInsight --configuration testRuntimeClasspath --dependency junit-platform-commons

