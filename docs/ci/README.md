https://plantuml.com/ru/preprocessing
https://crashedmind.github.io/PlantUMLHitchhikersGuide/PlantUMLSpriteLibraries/plantuml_sprites.html

#### master build
Use case: we will use it for checking that it is a live

* populate remote grade cache
* create database docker image
* run unit tests
* run integration tests
* faster (incremental build)

#### branch build
Use case: I will use it to check a PR before I will merge it to the master

* fast as possible
* run unit tests
* run integration tests

#### local build
Use case: I will use it for developing

* easy as possible (single click)
* fast as possible (incremental build)
* use remote gradle cache


#### e2e build
Use case:

* run e2e tests

##### Keep the Build Fast
https://martinfowler.com/articles/continuousIntegration.html