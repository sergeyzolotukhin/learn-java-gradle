package org.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            OutputStream output = new FileOutputStream("demopage.html");

            JellyContext context = new JellyContext();

            context.setVariable("name", "Title");
            context.setVariable("background", "#FF0000");
            context.setVariable("url", "https://commons.apache.org/proper/commons-jelly/tutorial.html");
            Vector v = new Vector();
            for (int i = 0; i < 10; i++) {
                v.add("Serhij " + i);
            }
            context.setVariable("hobbies", v);

            XMLOutput xmlOutput = XMLOutput.createXMLOutput(output);
            URL url = Main.class.getClassLoader().getResource("main.jelly");
            context.runScript(url, xmlOutput);
            xmlOutput.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}