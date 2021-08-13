package ua.in.sz.code.gen.main;

import com.squareup.javapoet.MethodSpec;
import lombok.extern.slf4j.Slf4j;

/**
 * https://www.baeldung.com/java-poet
 */
@Slf4j
public class Main2 {
    public static void main(String[] args) {
        MethodSpec sumOfTen = MethodSpec
                .methodBuilder("sumOfTen")
                .addStatement("int sum = 0")
                .beginControlFlow("for (int i = 0; i <= 10; i++)")
                .addStatement("sum += i")
                .endControlFlow()
                .build();

        log.info("\n{}", sumOfTen);
    }
}
