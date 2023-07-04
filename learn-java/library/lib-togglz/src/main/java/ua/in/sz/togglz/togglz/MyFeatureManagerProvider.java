package ua.in.sz.togglz.togglz;

import lombok.extern.slf4j.Slf4j;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.spi.FeatureManagerProvider;
import org.togglz.core.user.NoOpUserProvider;
import ua.in.sz.togglz.MyFeatures;

import java.nio.file.Paths;

@Slf4j
public class MyFeatureManagerProvider implements FeatureManagerProvider {
	public static final String PROJECT_NAME = "lib-togglz";
	public static final String RESOURCES = "src/main/resources";
	public static final String FEATURES = "feature.properties";

	private static FeatureManager featureManager;

	@Override
	public FeatureManager getFeatureManager() {
		if (featureManager == null) {
			featureManager = new FeatureManagerBuilder()
					.featureEnum(MyFeatures.class)
					.stateRepository(new FileBasedStateRepository(Paths.get(PROJECT_NAME, RESOURCES, FEATURES).toFile()))
					.userProvider(new NoOpUserProvider())
					.build();
		}

		return featureManager;
	}

	@Override
	public int priority() {
		return 0;
	}
}
