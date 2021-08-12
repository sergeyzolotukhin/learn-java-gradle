package ua.in.sz.code.gen.main;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.code.gen.tools.PicoWriter;

/**
 * https://github.com/ainslec/picocog
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        PicoWriter c = new PicoWriter();

        c.w("package com.samplepackage;");
        c.w();
        c.w("public class MyClass {");

        PicoWriter pv = c.createDeferredWriter();

        c.w("{");
        PicoWriter si = c.createDeferredWriter();
        c.w("}");
        c.w();

        PicoWriter mainMethod = c.createDeferredWriter();

        pv.w("String myString = null;");
        pv.w("String myString2 = null;");

        si.w("// Contents of the indented section (1)");
        si.w("// Contents of the indented section (2)");

        main(mainMethod);

        c.w("}");

        log.info("\n{}", c);
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
        w.w();
    }
}
