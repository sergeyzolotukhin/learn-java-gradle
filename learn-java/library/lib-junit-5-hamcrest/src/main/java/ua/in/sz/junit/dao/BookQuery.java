package ua.in.sz.junit.dao;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BookQuery {
	private String role;
	private List<String> secondaryRoles;
}
