package ua.in.sz.yaml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.json.BookDto;
import ua.in.sz.json.PageDto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class YamlCustomApplication {
    public static void main(String[] args) throws IOException {
        YAMLFactory factory = YAMLFactory.builder()
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .build();

        ObjectMapper mapper = new ObjectMapper(factory)
                .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
                ;
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));


        BookDto bookDto = BookDto.of("Book - 1", new Date(),
                Collections.singletonList(PageDto.of("Title-1", "Text-1", 5)));

        BookDto bookDto2 = BookDto.of("Book - 2", new Date(),
                Arrays.asList(
                        PageDto.of("Title-1", "Text-1", 15),
                        PageDto.of("Title-2", "Text-2", 3)
                )
        );

        final List<BookDto> books = Arrays.asList(bookDto, bookDto2);

        log.info("to yaml start");
        String actual = mapper.writeValueAsString(books);
        log.info("to yaml:[\n{}]", actual);
    }

    public static class PageListFilter {
        private static final List<PageDto> DEFAULT_PAGES = Arrays.asList(
                PageDto.of("Title-1", "Text-1", 15),
                PageDto.of("Title-2", "Text-2", 3)
        );

        @Override
        public boolean equals(Object other) {
            return DEFAULT_PAGES.equals(other);
        }
    }
}
