package ua.in.sz.spring;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class EntsoeReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
    @Override
    protected MessageFormat createMessageFormat(String msg, Locale locale) {
        MessageFormat format = super.createMessageFormat(msg, locale);

        Format[] formats = format.getFormats();
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] == null) {
                formats[i] = new DefaultFormat();
            }
        }
        format.setFormats(formats);

        return format;
    }

    private static class DefaultFormat extends Format {
        @Override
        public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append("...").append(o).append("...");
        }

        @Override
        public Object parseObject(String s, ParsePosition parsePosition) {
            return null;
        }
    }
}
