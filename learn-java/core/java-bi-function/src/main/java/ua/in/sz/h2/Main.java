package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

/**
 * This is example how to pass references to the instance member.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Parameter parameter = Parameter.NONE;
        Result result = doApply(parameter, Interface::create);
        log.info("result: {}", result);
    }

    private static <T> T doApply(Parameter parameter, BiFunction<Interface, Parameter, T> action) {
        Interface instance = new DefaultImplementation();
        return action.apply(instance, parameter);
    }

    // ================================================================================================================

    @Slf4j
    public static class DefaultImplementation implements Interface {
        @Override
        public Result create(Parameter parameter) {
            log.info("create");
            return null;
        }
    }

    public interface Interface {
        Result create(Parameter parameter);
    }

    public enum Parameter {
        NONE
    }

    public static class Result {

    }
}
