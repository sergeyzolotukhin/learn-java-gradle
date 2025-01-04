package ua.in.sz.logging.logs;

import ch.qos.logback.core.model.ComponentModel;

public class FormatterModel extends ComponentModel {
    @Override
    protected FormatterModel makeNewInstance() {
        return new FormatterModel();
    }
}
