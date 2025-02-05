package ua.in.sz.conversion.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

@Slf4j
@Configuration
public class ConversionServiceMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.conversion.service");
        context.refresh();

        ConfigurableConversionService conversionService = context.getEnvironment().getConversionService();
        conversionService.addConverter(new StringToMyDtoConverter());

        MyDto value = conversionService.convert("123:321", MyDto.class);
        log.info("{}", value);

        context.close();
    }

    public static class StringToMyDtoConverter implements Converter<String, MyDto> {
        @Override
        public MyDto convert(String source) {
            return MyDto.builder()
                    .value(Integer.parseInt(source.split(":")[0]))
                    .precision(Integer.parseInt(source.split(":")[1]))
                    .origen(source).build();
        }
    }

    @ToString
    @Getter
    @Builder
    public static class MyDto {
        private Integer value;
        private Integer precision;
        private String origen;
    }
}