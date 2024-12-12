package ua.in.sz.junit5.suite;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import ua.in.sz.junit5.tests.FirstTest;
import ua.in.sz.junit5.tests.SecondTest;

@Slf4j
@Suite
@SuiteDisplayName("First Suite")
@SelectClasses({FirstSuite.class, SecondSuite.class})
public class AllSuite {
    @BeforeSuite
    static void beforeSuite() {
        log.info("Before Suite");
    }

    @AfterSuite
    static void afterSuite() {
        log.info("After Suite");
    }
}
