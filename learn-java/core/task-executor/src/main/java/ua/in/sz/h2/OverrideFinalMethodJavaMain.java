package ua.in.sz.h2;

import javassist.*;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.p2.SzClass;

@Slf4j
public class OverrideFinalMethodJavaMain {
    public static void main(String[] args) throws Exception {
//        CtClass bagel = ClassPool.getDefault().get("java.lang.Thread");
        CtClass bagel = ClassPool.getDefault().get("ua.in.sz.h2.p2.BaseClass");
        CtMethod originalMethod = bagel.getDeclaredMethod("getName");
        originalMethod.setModifiers(Modifier.PUBLIC);
        bagel.toClass();

//        CtClass bagelsolver = ClassPool.getDefault().get("ua.in.sz.h2.ThreadPoolExecutorMain.MyThread");
        CtClass bagelsolver = ClassPool.getDefault().get("ua.in.sz.h2.p2.SzClass");
        CtMethod overrideMethod = CtNewMethod.make("public String getName() { return super.getName() + \" with options \" ; }", bagelsolver);

        bagelsolver.addMethod(overrideMethod);

        bagelsolver.toClass();

//        Thread t = new ThreadPoolExecutorMain.MyThread(
//                Thread.currentThread().getThreadGroup(),
//                new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                    }
//                },
//                "My Name",
//                0
//        );

        SzClass t = new SzClass();
        t.setName("Base Name");

        log.info("Get name: {}", t.getName());
    }

}
