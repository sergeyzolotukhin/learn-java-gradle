package ua.in.sz.pattern.gof.chain.of.responsibility.lambda;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.UnaryOperator;


@Slf4j
public class Application {
    public static void main(String[] args) {
        Function<String, String> chain = headerProcessing().andThen(spellCheckerProcessing());

        String result = chain.apply("Source text.");

        log.info("Result: {}", result);
    }

    private static UnaryOperator<String> headerProcessing() {
        return (text) -> text + " Header was added.";
    }

    private static UnaryOperator<String> spellCheckerProcessing() {
        return (text) -> text + " Spell was added.";
    }
}
