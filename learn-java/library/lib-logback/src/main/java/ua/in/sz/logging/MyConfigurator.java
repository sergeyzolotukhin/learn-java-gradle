package ua.in.sz.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.Loader;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class MyConfigurator extends ContextAwareBase implements Configurator {
    private final static String MY_AUTOCONFIG_FILE_01 = "logback-01.xml";

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public ExecutionStatus configure(LoggerContext context) {
        ClassLoader myClassLoader = Loader.getClassLoaderOfObject(this);
        URL url = getResource(MY_AUTOCONFIG_FILE_01, myClassLoader, true);
        if (url != null) {
            try {
                configureByResource(url);
            } catch (JoranException e) {
                e.printStackTrace();
            }
            // You tried and that counts Mary.
            return ExecutionStatus.DO_NOT_INVOKE_NEXT_IF_ANY;
        } else {
            return ExecutionStatus.INVOKE_NEXT_IF_ANY;
        }
    }

    public void configureByResource(URL url) throws JoranException {
        if (url == null) {
            throw new IllegalArgumentException("URL argument cannot be null");
        }
        final String urlString = url.toString();
        if (urlString.endsWith("xml")) {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            configurator.doConfigure(url);
        } else {
            throw new LogbackException(
                    "Unexpected filename extension of file [" + url.toString() + "]. Should be .xml");
        }
    }

    @SuppressWarnings("SameParameterValue")
    private URL getResource(String filename, ClassLoader myClassLoader, boolean updateStatus) {
        URL url = Loader.getResource(filename, myClassLoader);
        if (updateStatus) {
            statusOnResourceSearch(filename, myClassLoader, url);
        }
        return url;
    }

    private void statusOnResourceSearch(String resourceName, ClassLoader classLoader, URL url) {
        StatusManager sm = context.getStatusManager();
        if (url == null) {
            sm.add(new InfoStatus("Could NOT find resource [" + resourceName + "]", context));
        } else {
            sm.add(new InfoStatus("Found resource [" + resourceName + "] at [" + url.toString() + "]", context));
            multiplicityWarning(resourceName, classLoader);
        }
    }

    @SuppressWarnings("UrlHashCode")
    private void multiplicityWarning(String resourceName, ClassLoader classLoader) {
        Set<URL> urlSet = null;
        try {
            urlSet = Loader.getResources(resourceName, classLoader);
        } catch (IOException e) {
            addError("Failed to get url list for resource [" + resourceName + "]", e);
        }

        if (urlSet != null && urlSet.size() > 1) {
            addWarn("Resource [" + resourceName + "] occurs multiple times on the classpath.");
            for (URL url : urlSet) {
                addWarn("Resource [" + resourceName + "] occurs at [" + url.toString() + "]");
            }
        }
    }
}
