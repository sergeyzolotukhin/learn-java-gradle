package ua.in.sz.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ua.in.sz.junit.service.BookService;
import ua.in.sz.junit.service.BookServiceImpl;
import ua.in.sz.junit.service.BookVO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html">1</a>
 * <a href="https://reflectoring.io/assertj-lists/">1</a>
 */
public class AssertjTest {
    private final BookService bookService = new BookServiceImpl();

    @Test
    @Tag("smock")
    @Tag("integration")
    void containsOne() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).contains("1");
    }

    @Test
    @Tag("integration")
    void containsSeveral() {
        List<String> titles = bookService.findTitles();

        assertThat(titles).contains("1", "2");
    }

    @Test
    @Tag("smock")
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

        assertThat(books)
                .extracting(BookVO::getTitle)
                .allMatch(t -> t.startsWith("T"));
    }

    @Test
    void tuple() {
        List<BookVO> books = bookService.find();

        assertThat(books)
                .extracting(BookVO::getTitle, BookVO::getDescription)
                .contains(
                        Assertions.tuple("T3", "D6"),
                        Assertions.tuple("T2", "D5")
                );
    }

    @RepeatedTest(4)
    void repeatedTest(){
        List<String> titles = bookService.findTitles();

        assertThat(titles)
                .hasSize(3);
    }

    @Test
    void returnsTest(){
        BookVO book = BookVO.builder()
                .title("T")
                .description("D")
                .build();

        assertThat(book)
                .returns("T", BookVO::getTitle)
                .returns("D", BookVO::getDescription);
    }
}
