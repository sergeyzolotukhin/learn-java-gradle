package ua.in.sz.notcomplited.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Dto {
	private final int code;
	private final String name;
}
