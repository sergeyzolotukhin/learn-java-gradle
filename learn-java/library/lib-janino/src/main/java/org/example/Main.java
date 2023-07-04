package org.example;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.ScriptEvaluator;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        exec();
        sum();
    }

    private static void exec() throws CompileException, InvocationTargetException {
        ScriptEvaluator se = new ScriptEvaluator();
        se.cook(
                ""
                        + "static void method1() {\n"
                        + "    System.out.println(1);\n"
                        + "}\n"
                        + "\n"
                        + "method1();\n"
                        + "method2();\n"
                        + "\n"
                        + "static void method2() {\n"
                        + "    System.out.println(2);\n"
                        + "}\n"
        );

        se.evaluate();
    }

    private static void sum() throws CompileException, InvocationTargetException {
        ExpressionEvaluator ee = new ExpressionEvaluator();

        ee.setParameters(new String[]{"a", "b"}, new Class[]{int.class, int.class});
        ee.setExpressionType(int.class);
        Object[] params = new Object[]{19, 23};

        ee.cook("a + b");
        int result = (Integer) ee.evaluate(params);
        log.info("result: {}", result);
    }
}