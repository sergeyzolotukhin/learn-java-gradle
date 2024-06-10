package ua.in.sz.h2.p2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ChildClassTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @SneakyThrows
    void test_1(){
        DynamicType.Unloaded unloadedType = new ByteBuddy()
                .subclass(Object.class)
//                .name("SZ")
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello World ByteBuddy! 222"))
                .make();

//        unloadedType.saveIn(new File("./build"));

        Class<?> dynamicType = unloadedType.load(getClass()
                        .getClassLoader())
                .getLoaded();
        log.info("{}", dynamicType.newInstance().toString());
    }
}