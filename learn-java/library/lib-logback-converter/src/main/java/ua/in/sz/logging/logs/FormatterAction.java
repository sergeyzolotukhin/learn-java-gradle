package ua.in.sz.logging.logs;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.SaxEventInterpretationContext;
import org.xml.sax.Attributes;

public class FormatterAction extends Action {

    public static final String FORMATTER_CLASS_ATTRIBUTE = "formatterClass";

    @Override
    public void begin(SaxEventInterpretationContext context, String name, Attributes attributes) throws ActionException {
        String formatterClass = attributes.getValue(FORMATTER_CLASS_ATTRIBUTE);
        ExtMessageConverter.formatters.add(formatterClass);

        context.pushModel(new FormatterModel());
    }

    @Override
    public void end(SaxEventInterpretationContext context, String name) throws ActionException {
        context.popModel();
    }
}
