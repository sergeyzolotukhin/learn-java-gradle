package ua.in.sz.code.gen.main;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.code.gen.tools.PicoWriter;

/**
 * https://github.com/ainslec/picocog
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        PicoWriter topWriter = new PicoWriter();

        topWriter.w("package com.samplepackage;");
        topWriter.w("");
        topWriter.w("public class MyClass {");

        PicoWriter memvarWriter = topWriter.createDeferredWriter();
        topWriter.w("{");
        PicoWriter indentedSection = topWriter.createDeferredWriter();
        topWriter.w("}");
        topWriter.w("");

        PicoWriter methodSection = topWriter.createDeferredWriter();

        memvarWriter.w("String myString = null;");
        memvarWriter.w("String myString2 = null;");

        indentedSection.w("// Contents of the indented section (1)");
        indentedSection.w("// Contents of the indented section (2)");

        PicoWriter mainMethod = methodSection.createDeferredWriter();

        main(mainMethod);

        topWriter.w("}");

        log.info("\n{}", topWriter);
    }

    private static void main(PicoWriter w) {
        w.w("public static void main(String[] args) {");
        w.w("   if (args.length == 0) {");
        w.w("       System.out.println(\"Require more than one argument\");");
        w.w("   } else if (args.length == 1) {");
        w.w("       doSomething();");
        w.w("   } else {");
        w.w("       System.out.println(\"Too many arguments\");");
        w.w("   }");
        w.w("}");
        w.w("");
    }
}
