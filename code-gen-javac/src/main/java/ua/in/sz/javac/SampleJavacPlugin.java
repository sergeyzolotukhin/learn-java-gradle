package ua.in.sz.javac;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TreeScanner;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleJavacPlugin implements Plugin, TaskListener {
    private PrintWriter out;

    @Override
    public String getName() {
        return "MyPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        out = new PrintWriter(System.out);
        task.addTaskListener(this);
    }

    @Override
    public void finished(TaskEvent e) {
        if (e.getKind() == TaskEvent.Kind.PARSE) {
            out.println(getName() + " => " + e.getKind() + " " + e);

            e.getCompilationUnit().accept(new TreeScanner<Void, Void>(){
                private static Set<String> TARGET_TYPES = Stream.of(
                                byte.class, short.class, char.class,
                                int.class, long.class, float.class, double.class)
                        .map(Class::getName)
                        .collect(Collectors.toSet());

                @Override
                public Void visitClass(ClassTree node, Void aVoid) {
                    return super.visitClass(node, aVoid);
                }

                @Override
                public Void visitMethod(MethodTree method, Void aVoid) {
                    List<VariableTree> parametersToInstrument
                            = method.getParameters().stream()
                            .filter(this::shouldInstrument)
                            .collect(Collectors.toList());

                    if (!parametersToInstrument.isEmpty()) {
                        Collections.reverse(parametersToInstrument);
                        parametersToInstrument.forEach(p -> addCheck(method, p));
                    }

                    return super.visitMethod(method, aVoid);
                }

                private void addCheck(MethodTree method, VariableTree parameter) {
                    out.println("Add parameter [" + parameter + "] check to the method " + method);
                }

                private boolean shouldInstrument(VariableTree parameter) {
                    return TARGET_TYPES.contains(parameter.getType().toString())
                            && parameter.getModifiers().getAnnotations().stream()
                            .anyMatch(a -> Positive.class.getSimpleName()
                                    .equals(a.getAnnotationType().toString()));
                }
            }, null);

            out.flush();
        }
    }
}
