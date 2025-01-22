package ua.in.sz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class StringSubstitutorApp {
    public static void main(String[] args) {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "QUICK BROWN FOX");
        valuesMap.put("target", "LAZY DOG");

        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        String result = sub.replace("The ${animal} jumped over the ${target}.");

        log.info(result);
    }
}
