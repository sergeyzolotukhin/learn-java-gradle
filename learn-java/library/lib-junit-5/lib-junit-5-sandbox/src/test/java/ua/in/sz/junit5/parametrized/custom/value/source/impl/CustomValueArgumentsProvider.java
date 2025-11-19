package ua.in.sz.junit5.parametrized.custom.value.source.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.support.ParameterDeclarations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class CustomValueArgumentsProvider extends AnnotationBasedArgumentsProvider<CustomValueSource> {

    @Override
    protected Stream<? extends Arguments>
    provideArguments(ParameterDeclarations parameters, ExtensionContext context, CustomValueSource valueSource)
    {
        List<String> arguments = Stream.of(valueSource.value())
                .flatMap(v -> Arrays.stream(v.split(",")))
                .toList();

        return arguments.stream().map(Arguments::of);
    }
}
