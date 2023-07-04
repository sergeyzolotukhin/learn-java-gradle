package ua.in.sz.pattern.gof.visitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.pattern.gof.visitor.impl.Component;
import ua.in.sz.pattern.gof.visitor.impl.Visitor;

@Slf4j
public class LogVisitor implements Visitor {
    public static final String PADDING = "    ";

    private int level = 0;

    @Override
    public void visit(Component component) {
        log.info("{}[{}]", StringUtils.repeat(PADDING, level), component.getName());
    }

    @Override
    public void preVisitChildren(Component component) {
        level++;
    }

    @Override
    public void postVisitChildren(Component component) {
        level--;
    }
}
