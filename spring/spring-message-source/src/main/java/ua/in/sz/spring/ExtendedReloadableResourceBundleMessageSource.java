package ua.in.sz.spring;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.text.ExtendedMessageFormat;
import org.apache.commons.text.FormatFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.*;

public class ExtendedReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
    private static final Map<String, FormatFactory> FORMAT_FACTORY = ImmutableMap.<String, FormatFactory>builder()
            .put("sz", new FormatFactory() {
                @Override
                public Format getFormat(String name, String arguments, Locale locale) {
                    return new DefaultFormat();
                }
            })
            .build();

    @Override
    protected MessageFormat createMessageFormat(String msg, Locale locale) {
        return new ExtendedMessageFormat(msg, locale, FORMAT_FACTORY);
    }

    private static class DefaultFormat extends Format {
        @Override
        public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append("-->").append(o).append("<!--");
        }

        @Override
        public Object parseObject(String s, ParsePosition parsePosition) {
            return null;
        }
    }
}
