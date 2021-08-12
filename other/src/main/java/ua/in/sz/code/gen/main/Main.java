package ua.in.sz.code.gen.main;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.code.gen.tools.CodeWriter;

/**
 * https://github.com/ainslec/picocog
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        CodeWriter clazz = new CodeWriter();

        clazz.w("package com.samplepackage;");
        clazz.w();

        clazz.w("public class MyClass {");
        clazz.w();

        CodeWriter field = clazz.writer();
        field.w(Main::field);
        field.w();

        CodeWriter statics = clazz.writer();
        statics.w(Main::statics);
        statics.w();

        CodeWriter main = clazz.writer();
        main.w(Main::main);
        main.w();

        clazz.w("}");
        clazz.w();

        log.info("\n{}", clazz);
    }

    private static void field(CodeWriter w) {
        w.w("String myString = null;");
        w.w("String myString2 = null;");
    }

    private static void statics(CodeWriter w) {
        w.w(" {");
        w.w("     // Contents of the indented section (1)");
        w.w("     // Contents of the indented section (2)");
        w.w(" }");
    }

    private static void main(CodeWriter w) {
        w.w("public static void main(String[] args) {");
        w.w("   if (args.length == 0) {");
        w.w("       System.out.println(\"Require more than one argument\");");
        w.w("   } else if (args.length == 1) {");
        w.w("       doSomething();");
        w.w("   } else {");
        w.w("       System.out.println(\"Too many arguments\");");
        w.w("   }");
        w.w("}");
        w.w();
    }
}
