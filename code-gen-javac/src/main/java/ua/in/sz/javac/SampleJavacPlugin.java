package ua.in.sz.javac;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;

import java.io.PrintWriter;

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
        if (e.getKind() == TaskEvent.Kind.ANALYZE) {
            out.println(getName() + " => " + e.getKind() + " " + e);
            out.flush();
        }
    }
}
