package ua.in.sz.yaml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.json.BookDto;
import ua.in.sz.json.PageDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class YamlApplication {
    public static void main(String[] args) throws IOException {
        YAMLFactory factory = YAMLFactory.builder()
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .build();

        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", filter());
        ObjectMapper mapper = new ObjectMapper(factory)
//                .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
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
        String actual = mapper.writer(filters).writeValueAsString(books);
        log.info("to yaml:[\n{}]", actual);

//		BookDto bookDto1 = mapper.readValue("{ name: Book - 1, date: 11-11-2019 }", BookDto.class);
//		log.info("from yaml: [{}]", bookDto1.getDate());


//		Path path = Paths.get("src/main/resources", "books.yaml");
//		List<BookDto> list = Arrays.asList(mapper.readValue(Files.newInputStream(path), BookDto[].class));
//		list.forEach(book -> log.info("{}", book.getName()));
    }

    public static PropertyFilter filter() {
        PropertyFilter theFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (include(writer)) {
                    if (!writer.getName().equals("size")) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }

                    int size = ((PageDto) pojo).getSize();
                    if (size >= 10) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                } else if (!jgen.canOmitFields()) { // since 2.3
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }

            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }

            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }
        };

        return theFilter;
    }
}
