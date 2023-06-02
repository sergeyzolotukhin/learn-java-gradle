package ua.in.sz.unit.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleAssertsTest {

    @Test
    void multipleSeparateAsserts() {
        int a = 1;
        int b = 2;
        int e = 3;

        assertEquals(e, a);
        assertEquals(e, b);
    }

    @Test
    void multipleAllAsserts() {
        int a = 1;
        int b = 2;
        int c = 3;
        int e = 3;

        assertAll(
                "Grouped Assertions of User",
                () -> assertEquals(e, a, "Username should be admin"),
                () -> assertEquals(e, b, "Email should be admin@baeldung.com"),
                () -> assertEquals(e, c, "User should be activated")
        );
    }
}