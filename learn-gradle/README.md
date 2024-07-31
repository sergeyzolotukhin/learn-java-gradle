#### Gradle

gradle jar taskTree --with-inputs --with-outputs

#### URLs

https://docs.gradle.org/current/userguide/structuring_software_products.html#structure_large_projects
https://docs.gradle.org/current/userguide/declaring_dependencies_between_subprojects.html#declaring_dependencies_between_subprojects
https://spring.io/guides/gs/multi-module/
https://docs.gradle.org/current/userguide/multi_project_builds.html
https://docs.gradle.org/current/userguide/intro_multi_project_builds.html
https://docs.gradle.org/current/userguide/multi_project_builds.html?_ga=2.99717658.565926200.1668804013-563256538.1659293152#sec:depending_on_output_of_another_project

#### dependencies 

gradle learn-gradle:impl:dependencies --configuration implementation

[Platform](https://www.baeldung.com/gradle-6-features)
[The Java Platform Plugin](https://docs.gradle.org/current/userguide/java_platform_plugin.html)

[Java Library plugin configurations](https://docs.gradle.org/current/userguide/java_library_plugin.html)

#### 

gradle learn-gradle:platform:dependencies --configuration api
gradle learn-gradle:api:dependencies --configuration compileClasspath

gradle learn-gradle:api:dependencyInsight --configuration api --dependency org.springframework:spring-core
gradle learn-gradle:api:dependencyInsight --configuration compileClasspath --dependency org.springframework:spring-core

https://docs.gradle.org/current/userguide/viewing_debugging_dependencies.html
https://docs.gradle.org/current/userguide/java_plugin.html
https://docs.gradle.org/current/userguide/declaring_dependencies.html
https://www.baeldung.com/gradle-6-features
https://docs.gradle.org/current/userguide/java_platform_plugin.html
https://docs.gradle.org/current/userguide/single_versions.html
https://docs.gradle.org/current/userguide/platforms.html

#### Gradle alias

https://stackoverflow.com/questions/72852329/define-alias-for-gradle-task-with-custom-argument

#### deprecation process ???

#### How to use gradle local store?
https://stackoverflow.com/questions/10834111/where-does-gradle-store-downloaded-jars-on-the-local-file-system
https://docs.gradle.org/current/userguide/directory_layout.html

#### Configuration

https://dev.to/autonomousapps/configuration-roles-and-the-blogging-industrial-complex-21mn
https://github.com/autonomousapps/blog-configuration-roles/blob/main/build-logic/configuration-roles/src/main/kotlin/mutual/aid/ProducerTask.kt

https://docs.gradle.org/current/userguide/java_library_plugin.html
https://docs.gradle.org/current/userguide/java_plugin.html

https://medium.com/agorapulse-stories/gradle-configurations-explained-4b9608dd5e35

https://docs.gradle.org/current/userguide/java_library_plugin.html#java_library_plugin
https://docs.gradle.org/current/userguide/java_plugin.html

https://plugins.gradle.org/docs/publish-plugin
https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html

### Gradle: Building a distribution from source !!!!
To create a Gradle distribution from the source tree you can run either of the following:

    ./gradlew :distributions-full:binDistributionZip

This will create a minimal distribution at `subprojects/distributions-full/build/distributions/gradle-<version>-bin.zip`, just what's needed to run Gradle (i.e. no sources nor docs).

You can then use it as a Gradle Wrapper local distribution in a Gradle based project by using a `file:/` URL pointing to the built distribution:

    ./gradlew wrapper --gradle-distribution-url=file:/path/to/gradle-<version>-bin.zip

To create a full distribution (includes sources and docs):

    ./gradlew :distributions-full:allDistributionZip

The full distribution will be created at `subprojects/distributions-full/build/distributions/gradle-<version>-all.zip`. You can then use it as a Gradle Wrapper local distribution:

    ./gradlew wrapper --gradle-distribution-url=file:/path/to/gradle-<version>-all.zip

#### Gradle dependencies
https://github.com/StubbornJava/StubbornJava/blob/master/build.gradle
