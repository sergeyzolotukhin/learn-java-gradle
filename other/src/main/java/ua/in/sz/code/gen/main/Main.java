package ua.in.sz.code.gen.main;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.code.gen.tools.PicoWriter;

/**
 * https://github.com/ainslec/picocog
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        PicoWriter topWriter = new PicoWriter();

        String myPackageName = "com.samplepackage";
        String myClassName   = "MyClass";

        topWriter.writeln ("package " + myPackageName + ";");
        topWriter.writeln ("");
        topWriter.writeln_r ("public class "+myClassName+" {");

        PicoWriter memvarWriter    = topWriter.createDeferredWriter();
        topWriter.writeln_r ("{");
        PicoWriter indentedSection = topWriter.createDeferredWriter();
        topWriter.writeln_l ("}");
        topWriter.writeln("");

        // Reserve a place at the current row
        PicoWriter methodSection = topWriter.createDeferredWriter();

        memvarWriter.writeln("String myString = null;" );
        indentedSection.writeln("// Contents of the indented section (1)");
        memvarWriter.writeln("String myString2 = null;" );
        indentedSection.writeln("// Contents of the indented section (2)");

        // Reserve a place at the current row
        PicoWriter mainMethod = methodSection.createDeferredWriter();

        genMain(mainMethod);

        topWriter.writeln_l ("}");

        log.info("{}", topWriter);
    }

    private static void genMain(PicoWriter w) {
        w.w("public static void main(String[] args) {");
        w.r("   if (args.length == 0) {");
        w.r("       System.out.println(\"Require more than one argument\");");
        w.l("   } else if (args.length == 1) {");
        w.r("       doSomething();");
        w.l("   } else {");
        w.r("       System.out.println(\"Too many arguments\");");
        w.l("   }");
        w.l("}");
        w.w("");
    }
}
