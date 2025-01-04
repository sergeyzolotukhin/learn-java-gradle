package ua.in.sz.junit;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import ua.in.sz.junit.service.BookService;
import ua.in.sz.junit.service.BookServiceImpl;
import ua.in.sz.junit.service.BookVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;

class HamcrestCustomMatcherToEqualBuilderTest {

	private BookService bookService = new BookServiceImpl();

	// ================================================================================================================
	// custom matcher
	// ================================================================================================================

	@Test
	void everyItemTitleStartWith() {
		List<BookVO> expected = Lists.newArrayList(
				BookVO.builder().title("T1").build(),
				BookVO.builder().title("T2").build(),
				BookVO.builder().title("T3").build()
		);

		List<BookVO> actualBooks = bookService.find();

		assertThat(actualBooks, contains(refEqList(expected, BookVO.Fields.title)));
	}

	// ================================================================================================================
	//
	// ================================================================================================================

	static <E> List<Matcher<? super E>> refEqList(Iterable<E> items, String... fieldNames) {
		List<Matcher<? super E>> list = new ArrayList<>();

		for (E item : items) {
			list.add(refEq(item, Arrays.asList(fieldNames)));
		}

		return list;
	}

	static <E> Matcher<? super E> refEq(E bean, List<String> fieldNames) {
		return Matchers.allOf(fieldNames.stream()
						.map(name -> hasProperty(name, isValue(bean, name)))
						.collect(toList()));
	}

	@SneakyThrows
	private static <E> Matcher<Object> isValue(E bean, String fieldName) {
		return Matchers.is(FieldUtils.readField(bean, fieldName, true));
	}

}