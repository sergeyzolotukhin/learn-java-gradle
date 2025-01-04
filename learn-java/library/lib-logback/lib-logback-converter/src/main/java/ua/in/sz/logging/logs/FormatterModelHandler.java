package ua.in.sz.logging.logs;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.model.processor.ModelHandlerBase;
import ch.qos.logback.core.model.processor.ModelHandlerException;
import ch.qos.logback.core.model.processor.ModelInterpretationContext;

import java.util.ArrayList;
import java.util.List;

public class FormatterModelHandler extends ModelHandlerBase {
    public static final String FORMATTER_REGISTRY = "FORMATTER_REGISTRY";

    public FormatterModelHandler(Context context) {
        super(context);
    }

    static public FormatterModelHandler makeInstance(Context context, ModelInterpretationContext mic) {
        return new FormatterModelHandler(context);
    }

    @Override
    public void handle(ModelInterpretationContext mic, Model model) throws ModelHandlerException {
        FormatterModel formatterModel = (FormatterModel) model;

        List<String> registry = (List<String>) context.getObject(FORMATTER_REGISTRY);
        if (registry == null) {
            registry = new ArrayList<>();
            context.putObject(FORMATTER_REGISTRY, registry);
        }

        registry.add(formatterModel.getClassName());
    }
}
