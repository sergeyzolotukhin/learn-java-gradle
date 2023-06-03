package ua.in.sz.junit.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Builder
@ToString
@FieldNameConstants
public class AuthorVO {
	private String name;
	private String surname;
}
