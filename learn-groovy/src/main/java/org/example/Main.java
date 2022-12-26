package org.example;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Binding binding = new Binding();
        binding.setVariable("foo", Integer.valueOf("2"));
        GroovyShell shell = new GroovyShell(binding);

        Object value = shell.evaluate("\"Hi $foo\"");
        log.info("Run: [{}]", value);
    }
}