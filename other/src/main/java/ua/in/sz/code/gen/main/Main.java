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

        clazz.wl("package com.samplepackage;");
        clazz.wl();

        clazz.wl("public class MyClass {");
        clazz.wl();

        CodeWriter field = clazz.writer();
        field.wl(Main::field);
        field.wl();

        CodeWriter statics = clazz.writer();
        statics.wl(Main::statics);
        statics.wl();

        CodeWriter main = clazz.writer();
        main.wl(Main::main);
        main.wl();

        clazz.wl("}");
        clazz.wl();

        log.info("\n{}", clazz);
    }

    private static void field(CodeWriter w) {
        w.wl("String myString = null;");
        w.wl("String myString2 = null;");
    }

    private static void statics(CodeWriter w) {
        w.wl(" {");
        w.wl("     // Contents of the indented section (1)");
        w.wl("     // Contents of the indented section (2)");
        w.wl(" }");
    }

    private static void main(CodeWriter w) {
        w.wl("public static void main(String[] args) {");
        w.wl("   if (args.length == 0) {");
        w.wl("       System.out.println(\"Require more than one argument\");");
        w.wl("   } else if (args.length == 1) {");
        w.wl("       doSomething();");
        w.wl("   } else {");
        w.wl("       System.out.println(\"Too many arguments\");");
        w.wl("   }");
        w.wl("}");
        w.wl();
    }
}
