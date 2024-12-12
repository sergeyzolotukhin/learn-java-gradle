package ua.in.sz.junit5.suite;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import ua.in.sz.junit5.tests.FirstTest;
import ua.in.sz.junit5.tests.SecondTest;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
//@SelectPackages("example")
//@IncludeClassNamePatterns(".*Tests")
@SelectClasses({FirstTest.class, SecondTest.class})
public class FirstSuite {
}
