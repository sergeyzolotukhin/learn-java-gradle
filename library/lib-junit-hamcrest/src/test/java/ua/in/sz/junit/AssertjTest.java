package ua.in.sz.junit;

import org.junit.jupiter.api.Test;
import ua.in.sz.junit.service.BookService;
import ua.in.sz.junit.service.BookServiceImpl;
import ua.in.sz.junit.service.BookVO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertjTest {
    private final BookService bookService = new BookServiceImpl();

    @Test
    void containsOne() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).contains("1");
    }

    @Test
    void containsSeveral() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).contains("1", "2");
    }

    @Test
    void containsOnly() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).containsOnly("3", "1", "2");
    }

    @Test
    void containsExactly() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).containsExactly("1", "2", "3");
    }

    // ================================================================================================================
    // Collection of bean
    // ================================================================================================================

    @Test
    void everyItemTitleStartWith() {
        List<BookVO> books = bookService.find();

        assertThat(books).extracting(BookVO::getTitle).allMatch(t -> t.startsWith("TD"));
    }
}
