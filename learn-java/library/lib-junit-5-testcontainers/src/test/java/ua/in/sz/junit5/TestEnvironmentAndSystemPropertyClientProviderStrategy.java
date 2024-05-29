package ua.in.sz.junit5;

import lombok.Getter;
import org.testcontainers.dockerclient.DockerClientProviderStrategy;
import org.testcontainers.dockerclient.InvalidConfigurationException;
import org.testcontainers.dockerclient.TransportConfig;
import org.testcontainers.shaded.com.github.dockerjava.core.DefaultDockerClientConfig;
import org.testcontainers.shaded.com.github.dockerjava.core.DockerClientConfig;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.Optional;

public class TestEnvironmentAndSystemPropertyClientProviderStrategy extends DockerClientProviderStrategy {
    public static final int PRIORITY = 90;

    private final DockerClientConfig dockerClientConfig;

    @Getter
    private final boolean applicable;

    public TestEnvironmentAndSystemPropertyClientProviderStrategy() {
        // use docker-java defaults if present, overridden if our own configuration is set
        this(DefaultDockerClientConfig.createDefaultConfigBuilder());
    }

    TestEnvironmentAndSystemPropertyClientProviderStrategy(DefaultDockerClientConfig.Builder configBuilder) {
        String dockerConfigSource = TestcontainersConfiguration
                .getInstance()
                .getEnvVarOrProperty("dockerconfig.source", "auto");

        switch (dockerConfigSource) {
            case "auto":
                Optional<String> dockerHost = getSetting("docker.host");
                dockerHost.ifPresent(configBuilder::withDockerHost);
                applicable = dockerHost.isPresent();
                getSetting("docker.tls.verify").ifPresent(configBuilder::withDockerTlsVerify);
                getSetting("docker.cert.path").ifPresent(configBuilder::withDockerCertPath);
                break;
            case "autoIgnoringUserProperties":
                applicable = configBuilder.isDockerHostSetExplicitly();
                break;
            default:
                throw new InvalidConfigurationException("Invalid value for dockerconfig.source: " + dockerConfigSource);
        }

        dockerClientConfig = configBuilder.build();
    }

    private Optional<String> getSetting(final String name) {
        return Optional.ofNullable(TestcontainersConfiguration.getInstance().getEnvVarOrProperty(name, null));
    }

    @Override
    public String getDescription() {
        return (
                "Environment variables, system properties, classpath and defaults. Resolved dockerHost=" +
                        dockerClientConfig.getDockerHost()
        );
    }

    @Override
    protected int getPriority() {
        return PRIORITY;
    }

    @Override
    public TransportConfig getTransportConfig() {
        return TransportConfig
                .builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .build();
    }
}
