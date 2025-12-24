https://undertow.io/

VM: java-shell
JDK home path: /usr

ps aux | grep java
kill -9 2705

https://www.jetbrains.com/help/idea/2024.3/run-targets.html?reference.run.targets&keymap=Windows#run

org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext.doClose
org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.doClose
org.springframework.boot.web.embedded.undertow.DeploymentManagerHttpHandlerFactory.DeploymentManagerHandler

=======================================================================================================================

[spring boot]
UndertowServletWebServerFactory
ServletWebServerApplicationContext
ErgoDeploymentManagerHttpHandlerFactory
WebServerStartStopLifecycle
WebServerGracefulShutdownLifecycle
UndertowServletWebServer
UndertowWebServer

[spring]
AbstractApplicationContext
DefaultLifecycleProcessor

[undertow]
ServletContainerImpl
Undertow
DeploymentManagerImpl

=======================================================================================================================
How to close application context

SpringApplicationShutdownHook - run  ->  AbstractApplicationContext - close

=======================================================================================================================

org.springframework.context.support.AbstractApplicationContext.doClose

Order of closing
0. Publish shutdown event.
1. Stop all Lifecycle beans, to avoid delays during individual destruction.
2. Destroy all cached singletons in the context's BeanFactory.
    - org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor
    - org.springframework.beans.factory.DisposableBean.destroy
    - java.lang.AutoCloseable