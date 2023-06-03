package ua.in.sz.junit.service;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter
@Builder
@ToString
@FieldNameConstants
public class BookVO {
	private final String title;
	private final String description;
	private List<AuthorVO> authors;
}
