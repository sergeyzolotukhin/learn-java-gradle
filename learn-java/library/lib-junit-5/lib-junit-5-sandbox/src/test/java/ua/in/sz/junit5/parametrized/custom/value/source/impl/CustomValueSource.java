package ua.in.sz.junit5.parametrized.custom.value.source.impl;

import org.apiguardian.api.API;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.apiguardian.api.API.Status.STABLE;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//@Repeatable(ValueSources.class)
@API(status = STABLE, since = "5.7")
@ArgumentsSource(CustomValueArgumentsProvider.class)
public @interface CustomValueSource {
    String[] value();
}
