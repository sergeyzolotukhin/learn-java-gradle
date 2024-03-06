I am using this repository to learn a different aspect of programming in the Java programming language.
I am using the Gradle build tool here.

### Java

#### spring - The spring folder contains everything which relates to the Spring framework itself.
#### hibernate - The hibernate folder contains everything which relates to the Hibernate framework itself.
#### library - The library folder contains everything which relates to Java libraries.

### Gradle

#### learn-gradle - The learn-gradle directory contains everything which relates to the Gradle build tool

#### How to make a gradle silent

Uncomment the following line
org.gradle.logging.level=quiet

Copy the gradle.properties into the following location:
c:\Users\szolotukhin\.gradle

#### Gradle slow start up

https://stackoverflow.com/questions/71559269/why-does-gradle-initializing-sometimes-take-very-long

org.gradle.logging.level=quiet
org.gradle.vfs.watch=true

#### Github Markdown

https://docs.github.com/ru/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
https://docs.github.com/ru/get-started/writing-on-github/working-with-advanced-formatting/organizing-information-with-collapsed-sections

#### Hot Swap

https://plugins.jetbrains.com/plugin/9552-hotswapagent
http://hotswapagent.org/

https://medium.com/javarevisited/jdk-17-with-dcevm-and-hotswapagent-4fee7095617a

#### Gradle: Caching the Downloaded Dependencies

https://www.thoughtworks.com/insights/blog/platforms/gradle-build-optimization-pt1
https://docs.gradle.org/current/userguide/dependency_resolution.html#sec:dependency_cache
https://docs.gradle.org/current/userguide/dependency_resolution.html#sub:cache_copy

https://docs.gradle.org/current/userguide/performance.html
https://docs.gradle.org/current/userguide/configuration_cache.html#config_cache
https://www.grammarly.com/blog/engineering/build-optimization-mechanisms-gitlab-gradle-docker/
https://github.com/gradle/gradle/issues/17511
