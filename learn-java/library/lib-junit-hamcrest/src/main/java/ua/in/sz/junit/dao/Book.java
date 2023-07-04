package ua.in.sz.junit.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Book {
	private final String name;

}
