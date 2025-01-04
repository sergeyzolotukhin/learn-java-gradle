package ua.in.sz.logging.logs;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.joran.ModelClassToModelHandlerLinker;
import ch.qos.logback.classic.model.processor.ConfigurationModelHandlerFull;
import ch.qos.logback.classic.util.DefaultJoranConfigurator;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.spi.RuleStore;
import ch.qos.logback.core.model.processor.DefaultProcessor;

import java.net.URL;

public class FormatterDefaultJoranConfigurator extends DefaultJoranConfigurator {
    @Override
    public void configureByResource(URL url) throws JoranException {
        if (url == null) {
            throw new IllegalArgumentException("URL argument cannot be null");
        }
        final String urlString = url.toString();
        if (urlString.endsWith("xml")) {
            JoranConfigurator configurator = new FormatterJoranConfigurator();
            configurator.setContext(context);
            configurator.doConfigure(url);
        } else {
            throw new LogbackException("Unexpected filename extension of file [" + url.toString() + "]. Should be .xml");
        }
    }

    private static class FormatterJoranConfigurator extends JoranConfigurator {
        @Override
        public void addElementSelectorAndActionAssociations(RuleStore rs) {
            super.addElementSelectorAndActionAssociations(rs);
            rs.addRule(new ElementSelector("configuration/formatter"), FormatterAction::new);
        }

        @Override
        protected void addModelHandlerAssociations(DefaultProcessor defaultProcessor) {
            ModelClassToModelHandlerLinker m = new ModelClassToModelHandlerLinker(context);
            defaultProcessor.addHandler(FormatterModel.class, FormatterModelHandler::makeInstance);
            m.setConfigurationModelHandlerFactoryMethod(ConfigurationModelHandlerFull::makeInstance2);
            m.link(defaultProcessor);
        }
    }
}
