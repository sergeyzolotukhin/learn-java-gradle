package ua.in.sz.junit.dao;

import java.util.List;

public interface BookRepository {
	List<Book> find(BookQuery query);
}
