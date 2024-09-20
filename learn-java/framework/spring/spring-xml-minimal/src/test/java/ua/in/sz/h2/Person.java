package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@SuppressWarnings("all")
public class Person {
    private String name;
    private int age;
    private List<String> descriptions = new ArrayList<>();

    public String getGender() {
        return "gender";
    }

    public void setGender(String gender) {

    }

    public List<String> getDescriptions() {
//        List<String> proxy = createDescriptionsJavaProxy();
        List<String> proxy = createDescriptiosSpringProxy();
        return proxy;
    }

    private List<String> createDescriptiosSpringProxy() {
        ProxyFactory factory = new ProxyFactory(descriptions);
        factory.addInterface(List.class);
        factory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                log.info("before method invoke [{}] args [{}]", method.getName(), args);
            }
        });

        List<String> proxy = (List<String>) factory.getProxy();
        return proxy;
    }

    private List<String> createDescriptionsJavaProxy() {
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class[] { List.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("add")) {
                            log.info("Add description [{}]", args[0]);
                            return descriptions.add((String) args[0]);
                        }

                        if (method.getName().equals("toString")) {
                            return descriptions.toString();
                        }

                        throw new UnsupportedOperationException(method.getName());
                    }
                });
        return proxyInstance;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
