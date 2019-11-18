package ua.in.sz.pattern.tostring.values;


import lombok.AllArgsConstructor;

import java.util.StringJoiner;

@AllArgsConstructor
public class StringJoinerValue {
	private String value;

	@Override
	public String toString() {
		return new StringJoiner(", ", StringJoinerValue.class.getSimpleName() + "[", "]")
				.add("value='" + value + "'")
				.toString();
	}
}
