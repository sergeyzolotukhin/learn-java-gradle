package ua.in.sz.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeatures implements Feature {
	@Label("First Feature")
	ONE,

	@Label("Second Feature")
	TWO;

	public boolean isActive() {
		return FeatureContext.getFeatureManager().isActive(this);
	}
}
