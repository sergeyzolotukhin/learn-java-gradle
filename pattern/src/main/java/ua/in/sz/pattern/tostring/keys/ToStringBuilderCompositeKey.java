package ua.in.sz.pattern.tostring.keys;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringBuilderCompositeKey extends AbstractCompositeKey {

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("fields", fields)
				.append("params", params)
				.append("hash", hash())
				.toString();
	}
}
