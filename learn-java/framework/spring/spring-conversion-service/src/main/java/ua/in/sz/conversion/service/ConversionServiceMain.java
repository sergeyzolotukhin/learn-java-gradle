package ua.in.sz.conversion.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Slf4j
@Configuration
public class ConversionServiceMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.conversion.service");
        context.refresh();

        ConversionService conversionService = context.getBean(ConversionService.class);

        MyDto value = conversionService.convert("123:321", MyDto.class);

        context.close();

        log.info("{}", value);
    }

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(String.class, MyDto.class,
                source -> MyDto.builder()
                        .value(Integer.parseInt(source.split(":")[0]))
                        .precision(Integer.parseInt(source.split(":")[1]))
                        .origen(source).build());
        return service;
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