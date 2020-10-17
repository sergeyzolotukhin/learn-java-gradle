package ua.in.sz.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.in.sz.junit.dao.Book;
import ua.in.sz.junit.dao.BookQuery;
import ua.in.sz.junit.dao.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
class BookMockitoRepositoryTest {

	@Mock
	BookRepository bookRepository;

	@Test
	void find() {
		doReturn(books("Book 1")).when(bookRepository).find(roleEqualTo("role-1"));
		doReturn(books("Book 2", "Book 3")).when(bookRepository).find(roleEqualTo("role-2"));

		List<Book> books = bookRepository.find(BookQuery.builder().role("role-1").build());

		assertEquals(1, books.size());
		assertEquals("Book 1", books.get(0).getName());

		books = bookRepository.find(BookQuery.builder().role("role-2").build());

		assertEquals(2, books.size());
		assertEquals("Book 2", books.get(0).getName());
		assertEquals("Book 3", books.get(1).getName());
	}

	private BookQuery roleEqualTo(String operand) {
		return argThat(hasProperty("role", equalTo(operand)));
	}

	private List<Book> books(String ... titles) {
		return Arrays.stream(titles).map(Book::of).collect(Collectors.toList());
	}
}