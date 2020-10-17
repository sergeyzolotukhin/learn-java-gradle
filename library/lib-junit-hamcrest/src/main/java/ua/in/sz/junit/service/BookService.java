package ua.in.sz.junit.service;

import java.util.List;

public interface BookService {
	List<String> findTitles();

	List<BookVO> find();
}
