package ua.in.sz.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.jaxb.model.Book;
import ua.in.sz.jaxb.model.Page;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

@Slf4j
public class JaxbMain {
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Book book = new Book();
        book.setId(1L);
        book.setName("book 1");
        book.setAuthor("author 1");
        book.setDate(new Date());

        Page pageA = new Page();
        pageA.setNo(2L);

        Page pageB = new Page();
        pageB.setNo(2L);

        book.setPages(Arrays.asList(pageA, pageB));

        StringWriter sw = new StringWriter();
        marshaller.marshal(book, sw);

        log.info("{}", sw);
    }
}