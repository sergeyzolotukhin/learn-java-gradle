package ua.in.sz.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import org.slf4j.event.KeyValuePair;

import java.util.List;

/**
 * @see ch.qos.logback.classic.pattern.KeyValuePairConverter
 * @see org.springframework.boot.logging.logback.ColorConverter
 */
public class KVPConverter extends ClassicConverter {

    private String key;

    public void start() {
        key = getFirstOption();
        super.start();
    }

    @Override
    public String convert(ILoggingEvent event) {
        List<KeyValuePair> kvpList = event.getKeyValuePairs();
        if (kvpList == null || kvpList.isEmpty()) {
            return CoreConstants.EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < kvpList.size(); i++) {
            KeyValuePair kvp = kvpList.get(i);

            if (!this.key.equals(kvp.key)) {
                continue;
            }

            if (i != 0) {
                sb.append(',');
            }

            sb.append(kvp.value);
        }

        return sb.toString();
    }
}
