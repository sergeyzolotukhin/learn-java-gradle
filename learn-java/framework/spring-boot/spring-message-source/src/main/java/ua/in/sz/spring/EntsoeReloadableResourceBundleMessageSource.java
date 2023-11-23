package ua.in.sz.spring;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.Nonnull;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class EntsoeReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
    @Override
    @Nonnull
    protected MessageFormat createMessageFormat(@Nonnull String msg, @Nonnull Locale locale) {
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
        public StringBuffer format(Object value, StringBuffer buffer, @Nonnull FieldPosition fieldPosition) {
            return buffer.append("...").append(value).append("...");
        }

        @Override
        public Object parseObject(String text, @Nonnull ParsePosition parsePosition) {
            throw new UnsupportedOperationException();
        }
    }
}
