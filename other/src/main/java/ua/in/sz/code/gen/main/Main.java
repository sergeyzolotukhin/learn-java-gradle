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

        topWriter.s("package com.samplepackage;");
        topWriter.s("");
        topWriter.s("public class MyClass {");

        PicoWriter memvarWriter = topWriter.createDeferredWriter();
        topWriter.s("{");
        PicoWriter indentedSection = topWriter.createDeferredWriter();
        topWriter.s("}");
        topWriter.s("");

        PicoWriter methodSection = topWriter.createDeferredWriter();

        memvarWriter.s("String myString = null;");
        memvarWriter.s("String myString2 = null;");

        indentedSection.s("// Contents of the indented section (1)");
        indentedSection.s("// Contents of the indented section (2)");

        PicoWriter mainMethod = methodSection.createDeferredWriter();

        main(mainMethod);

        topWriter.s("}");

        log.info("\n{}", topWriter);
    }

    private static void main(PicoWriter w) {
        w.s("public static void main(String[] args) {");
        w.s("   if (args.length == 0) {");
        w.s("       System.out.println(\"Require more than one argument\");");
        w.s("   } else if (args.length == 1) {");
        w.s("       doSomething();");
        w.s("   } else {");
        w.s("       System.out.println(\"Too many arguments\");");
        w.s("   }");
        w.s("}");
        w.s("");
    }
}
