package ua.in.sz.swagger.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class StringToMapConverter implements Converter<String, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(String source) {
        try {
            return new ObjectMapper().readValue(source, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
