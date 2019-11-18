package ua.in.sz.pattern.tostring.keys;

import java.util.StringJoiner;

public class StringJoinerCompositeKey extends AbstractCompositeKey {

	@Override
	public String toString() {
		return new StringJoiner(", ", toPrefix() + "[", "]")
				.add("fields=" + fields)
				.add("params=" + params)
				.add("hash=" + hash())
				.toString();
	}

	private String toPrefix() {
		return getClass().getSimpleName();
	}
}
