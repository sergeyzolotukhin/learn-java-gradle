package ua.in.sz.junit.mockito.ceated;

import java.lang.reflect.Method;
import java.util.List;

import org.mockito.internal.creation.bytebuddy.MockAccess;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.DispatcherDefaultingToRealMethod;

public class MockList  implements MyList, MockAccess {
    private MockMethodInterceptor mockitoInterceptor;

    @Override
    public int indexOf(Object var1) {
        try {
            return (Integer) DispatcherDefaultingToRealMethod.interceptAbstract(
                    this,
                    this.mockitoInterceptor,
                    0,
                    cachedValue$3n4PrCK0$tm4die2,
                    new Object[]{var1});
        } catch (Throwable t) {
            return -1;
        }
    }

    public void setMockitoInterceptor(MockMethodInterceptor var1) {
        this.mockitoInterceptor = var1;
    }

    public MockMethodInterceptor getMockitoInterceptor() {
        return this.mockitoInterceptor;
    }

    public MockList() {
    }

    private static Method cachedValue$3n4PrCK0$tm4die2;

    static {
        try {
            cachedValue$3n4PrCK0$tm4die2 = List.class.getMethod("indexOf", Object.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}