package ua.in.sz.conversion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class StringToMyDtoConverter implements Converter<String, Application.MyDto> {
    @Override
    public Application.MyDto convert(String source) {
        return Application.MyDto.builder()
                .value(Integer.parseInt(source.split(":")[0]))
                .precision(Integer.parseInt(source.split(":")[1]))
                .origen(source).build();
    }

    @Autowired
    public void onboardConverter(ConfigurableEnvironment environment) {
        environment.getConversionService().addConverter(this);
    }
}