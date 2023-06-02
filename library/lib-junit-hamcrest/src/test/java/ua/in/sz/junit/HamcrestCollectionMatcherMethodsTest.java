package ua.in.sz.junit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import ua.in.sz.junit.service.BookService;
import ua.in.sz.junit.service.BookServiceImpl;
import ua.in.sz.junit.service.BookVO;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HamcrestCollectionMatcherMethodsTest {

	private final BookService bookService = new BookServiceImpl();

	// ================================================================================================================
	// Hamcrest collection on simple types
	// ================================================================================================================

	@Test
	void hasAtLeastOneItem() {
		List<String> titles = bookService.findTitles();

		assertThat(titles, Matchers.hasItem("1"));
	}

	@Test
	void listIsEqual() {
		List<String> titles = bookService.findTitles();

		assertThat(titles, contains("1", "2", "3"));
	}

	@Test
	void listIsEqualInAnyOrder() {
		List<String> titles = bookService.findTitles();

		assertThat(titles, containsInAnyOrder("3", "1", "2"));
	}

	@Test
	void listContainsItems() {
		List<String> titles = bookService.findTitles();

		assertThat(titles, hasItems("1", "2"));
	}

	@Test
	void hasSizeThree() {
		List<String> titles = bookService.findTitles();

		assertThat(titles, hasSize(3));
	}

	// ================================================================================================================
	// Collection of bean
	// ================================================================================================================

	@Test
	void everyItemTitleStartWith() {
		List<BookVO> books = bookService.find();

		assertThat(books, everyItem(hasProperty(BookVO.Fields.title, startsWith("T"))));
	}

	@Test
	void containsHasProperty() {
		List<BookVO> books = bookService.find();

		assertThat(books, contains(
				hasProperty(BookVO.Fields.title, is("T1")),
				hasProperty(BookVO.Fields.title, is("T2")),
				hasProperty(BookVO.Fields.title, is("T3"))
		));
	}

	@Test
	void containsAllOfHasProperty() {
		List<BookVO> books = bookService.find();

		assertThat(books, contains(
				hasProperty(BookVO.Fields.title, is("T1")),
				allOf(
						hasProperty(BookVO.Fields.title, is("T2")),
						hasProperty(BookVO.Fields.description, is("D5"))),
				hasProperty(BookVO.Fields.title, is("T3"))
		));
	}

	@Test
	void hasItemSamePropertyValuesAs() {
		List<BookVO> books = bookService.find();

		BookVO expected = BookVO.builder().title("T2").description("D5").build();

		assertThat(books, hasItem(samePropertyValuesAs(expected)));
	}

	@Test
	void hasItemSamePropertyValuesAsWithIgnore() {
		List<BookVO> books = bookService.find();

		BookVO expected = BookVO.builder().title("T2").build();

		assertThat(books, hasItem(samePropertyValuesAs(expected, BookVO.Fields.description)));
	}

	// ================================================================================================================
	// JUnit 5
	// ================================================================================================================

	@Test
	void hasBookT1AndT3() {
		List<BookVO> books = bookService.find();

		assertAll("Not all book exists",
				() -> assertEquals("T1", books.get(0).getTitle()),
				() -> assertEquals("T3", books.get(2).getTitle())
		);
	}
}