package ua.in.sz.tabular;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * -verbose:gc -Xlog:gc -XX:+PrintGCDetails
 * -Xlog:gc=debug
 *
 * heap* gc+heap* cds+heap gc+heap+verify gc+heap+region
 *
 * MaxTenuringThreshold
 * -Xlog:cpu*=debug
 *
 * https://blog.codefx.org/java/unified-logging-with-the-xlog-option/
 * https://docs.oracle.com/javase/9/gctuning/garbage-first-garbage-collector.htm#JSGCT-GUID-082C967F-2DAC-4B59-8A81-0CEC6EEB9016
 */
public class G1Main {
    @SneakyThrows
    public static void main(String[] args) {

        for (int i = 0; i < 6 * 4; i++) {
            List<Dto> list = new ArrayList<>(10000);
            for (int j = 0; j < 1_000_000; j++) {
                list.add(Dto.of(j));
            }
            System.out.println("Size:" + list.size());

            TimeUnit.SECONDS.sleep(10);
        }
    }

    @AllArgsConstructor(staticName = "of")
    private static final class Dto {
        private int id;
    }
}
