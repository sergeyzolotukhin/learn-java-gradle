package ua.in.sz.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.jaxb.model.Book;
import ua.in.sz.jaxb.model.page.FirstPage;
import ua.in.sz.jaxb.model.Page;
import ua.in.sz.jaxb.model.page.SecondPage;
import ua.in.sz.jaxb.model.page.ThirdPage;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

@Slf4j
public class JaxbMain {
    public static void main(String[] args) throws Exception {

        StringWriter sw = new StringWriter();
        marshaller().marshal(createBook(), sw);

        log.info("{}", sw);

        StringReader reader = new StringReader(sw.toString());
        Book book = (Book) unmarshaller().unmarshal(reader);

        log.info("{}", book.getPages());
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        return jaxbContext().createUnmarshaller();
    }

    private static JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(
                Book.class,
                Page.class,
                FirstPage.class,
                SecondPage.class,
                ThirdPage.class
        );
    }

    private static Marshaller marshaller() throws JAXBException {
        Marshaller marshaller = jaxbContext().createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        return marshaller;
    }

    private static Book createBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("book 1");
        book.setAuthor("author 1");
        book.setDate(new Date());

        Page a = new Page();
        a.setNo(2L);

        FirstPage b = new FirstPage();
        b.setNo(2L);
        b.setName("page 3");

        SecondPage c = new SecondPage();
        c.setNo(3L);
        c.setDescription("page 3 description");

        ThirdPage d = new ThirdPage();
        d.setNo(4L);
        d.setComment("page 3 comment");

        book.setPages(Arrays.asList(a, b, c, d));

        return book;
    }
}