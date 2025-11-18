package ua.in.sz.junit5.parametrized;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Slf4j
public class JunitParameterizedFieldSourceTest {

    private final static List<Accessor> myParameterizedTest = Arrays.asList(
            Accessor.builder()
                    .extractor(JunitParameterizedFieldSourceTest::getName)
                    .injector(JunitParameterizedFieldSourceTest::setName)
                    .value("5555 --- 6666")
                    .build()
    );

    @ParameterizedTest
    @FieldSource
    void myParameterizedTest(Accessor accessor) {
        DataDto data = DataDto.builder()
                .id(1)
                .name("name 1")
                .description("description 1")
                .build();

        accessor.injector.accept(data, accessor.value);

        DataDto actual = doSomething(data);

        Object actualValue = accessor.extractor.apply(actual);

        log.info("Actual value: {}", actualValue);
    }

    private DataDto doSomething(DataDto source) {
        return DataDto.builder()
                .id(source.getId())
                .name(source.getName() + " - 2")
                .description(source.getDescription())
                .build();
    }

    // ================================================================================================================

    public static void setName(DataDto data, String name) {
        data.setName(name);
    }

    public static Object getName(DataDto data) {
        return data.getName();
    }

    @Builder
    public static class Accessor {
        Function<DataDto, Object> extractor;
        BiConsumer<DataDto, String> injector;
        String value;
    }

    @Builder
    @Setter
    @Getter
    public static class DataDto {
        int id;
        String name;
        String description;
    }

}
