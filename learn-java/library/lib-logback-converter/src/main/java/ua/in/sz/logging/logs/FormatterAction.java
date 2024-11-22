package ua.in.sz.logging.logs;

import ch.qos.logback.core.joran.action.BaseModelAction;
import ch.qos.logback.core.joran.spi.SaxEventInterpretationContext;
import ch.qos.logback.core.model.Model;
import org.xml.sax.Attributes;

public class FormatterAction extends BaseModelAction {
    public static final String FORMATTER_CLASS_ATTRIBUTE = "class";

    @Override
    protected Model buildCurrentModel(SaxEventInterpretationContext interpretationContext, String name, Attributes attributes) {
        String formatterClass = attributes.getValue(FORMATTER_CLASS_ATTRIBUTE);

        FormatterModel model = new FormatterModel();
        model.setClassName(formatterClass);
        return model;
    }
}
