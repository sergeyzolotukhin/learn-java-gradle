package ua.in.sz.junit5.parametrized;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;


@Slf4j
public class JunitParameterizedArgumentConverterTest {
    @ParameterizedTest
    @ValueSource(strings = {"10", "20", "30"})
    void testWithCustomConverter(@ConvertWith(MyCustomConverter.class) Integer number) {
        Assertions.assertEquals(Integer.class, number.getClass());
        log.info("{}", number.getClass());
    }

    public static class MyCustomConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            try {
                return Integer.parseInt((String) source);
            } catch (NumberFormatException e) {
                throw new ArgumentConversionException("Failed to convert String to Integer", e);
            }
        }
    }
}
