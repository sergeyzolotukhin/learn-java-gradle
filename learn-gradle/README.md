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
