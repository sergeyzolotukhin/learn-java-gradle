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
        clazz.nl();

        clazz.w("public class MyClass {");
        clazz.nl();

        CodeWriter field = clazz.writer();
        field.nl();

        CodeWriter statics = clazz.writer();
        statics.nl();

        CodeWriter main = clazz.writer();
        main.nl();

        clazz.w("}");
        clazz.nl();

        field.w("String myString = null;");
        field.w("String myString2 = null;");

        statics.w(" {");
        statics.w("     // Contents of the indented section (1)");
        statics.w("     // Contents of the indented section (2)");
        statics.w(" }");

        main(main);



        log.info("\n{}", clazz);
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
        w.nl();
    }
}
