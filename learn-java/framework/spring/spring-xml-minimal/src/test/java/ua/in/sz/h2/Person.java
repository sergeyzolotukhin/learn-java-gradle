package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class Person {
    private String name;
    private int age;
    private List<String> descriptions = new ArrayList<>();

    public String getGender() {
        return "gender";
    }

    public void setGender(String gender) {

    }

    @SuppressWarnings("all")
    public List<String> getDescriptions() {
        log.info("getDescriptions()");
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class[] { List.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("add")) {
                            return descriptions.add((String) args[0]);
                        }

                        if (method.getName().equals("toString")) {
                            return descriptions.toString();
                        }

                        throw new UnsupportedOperationException(method.getName());
//                        if (method.getName().equals("add")) {
//
//                        }
//                        return descriptions;

//                        log.info("invoke method {}", args);
                    }
                });

        return proxyInstance;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
