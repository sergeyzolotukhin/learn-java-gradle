package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;

@Slf4j
public class MainTest {
    @Rule
    public TestMethodNameLogger collector= new TestMethodNameLogger();

    @Test
    public void main() {
      log.info("main");
    }
}