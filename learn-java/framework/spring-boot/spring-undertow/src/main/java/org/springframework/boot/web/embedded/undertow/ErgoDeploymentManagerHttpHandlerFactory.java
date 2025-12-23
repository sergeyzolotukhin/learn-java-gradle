package org.springframework.boot.web.embedded.undertow;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.api.DeploymentManager;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.Closeable;
import java.io.IOException;

@Slf4j
public class ErgoDeploymentManagerHttpHandlerFactory implements HttpHandlerFactory {

    private final DeploymentManager deploymentManager;

    ErgoDeploymentManagerHttpHandlerFactory(DeploymentManager deploymentManager) {
        this.deploymentManager = deploymentManager;
    }

    @Override
    public HttpHandler getHandler(HttpHandler next) {
        Assert.state(next == null, "DeploymentManagerHttpHandlerFactory must be first");
        return new DeploymentManagerHandler(this.deploymentManager);
    }

    DeploymentManager getDeploymentManager() {
        return this.deploymentManager;
    }

    /**
     * {@link HttpHandler} that delegates to a {@link DeploymentManager}.
     */
    static class DeploymentManagerHandler implements HttpHandler, Closeable {

        private final DeploymentManager deploymentManager;

        private final HttpHandler handler;

        DeploymentManagerHandler(DeploymentManager deploymentManager) {
            this.deploymentManager = deploymentManager;
            try {
                this.handler = deploymentManager.start();
            }
            catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            this.handler.handleRequest(exchange);
        }

        @Override
        public void close() throws IOException {
            log.error("close", new IllegalStateException());

            try {
                this.deploymentManager.stop();
                this.deploymentManager.undeploy();
            }
            catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }

        DeploymentManager getDeploymentManager() {
            return this.deploymentManager;
        }

    }

}