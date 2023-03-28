package ua.in.sz.logging;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.Setter;

public class NyPropertyDefiner extends PropertyDefinerBase {
    @Setter
    private String prefix = "";

    @Override
    public String getPropertyValue() {
//        prefix.equals()
        return prefix + System.getProperty("os.name");
    }
}
