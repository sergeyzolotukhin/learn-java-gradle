package ua.in.sz;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Slf4j
public class ToString {
    public static void main(String[] args) {
        Main m1 = new Main("m1");
        log.info("to string apache: {}", ToStringBuilder.reflectionToString(m1));
        log.info("to string: {}", m1);
        log.info("system to string: {}", Objects.toIdentityString(m1));
    }

    @AllArgsConstructor
    private static final class Main {
        String name;

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
