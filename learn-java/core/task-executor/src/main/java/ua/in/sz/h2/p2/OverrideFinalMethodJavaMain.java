package ua.in.sz.h2.p2;

import javassist.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OverrideFinalMethodJavaMain {
    public static void main(String[] args) throws Exception {
        overrideGetName();

        ChildClass childClass = new ChildClass();
        childClass.setName("Base Name");

        log.info("Get name: {}", childClass.getName());
    }

    private static void overrideGetName() throws NotFoundException, CannotCompileException {
        CtClass baseClass = ClassPool.getDefault().get("ua.in.sz.h2.p2.BaseClass");
        CtMethod originalMethod = baseClass.getDeclaredMethod("getName");
        originalMethod.setModifiers(Modifier.PUBLIC);
        baseClass.toClass();

        CtClass childClass = ClassPool.getDefault().get("ua.in.sz.h2.p2.ChildClass");
        CtMethod overrideMethod = CtNewMethod.make("public String getName() { return super.getName() + \" with options \" ; }", childClass);
        childClass.addMethod(overrideMethod);
        childClass.toClass();
    }

}
