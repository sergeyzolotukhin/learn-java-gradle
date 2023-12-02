package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.DataBinder;

@Slf4j
public class Main {
    public static void main(String[] args) {
        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.add(TestBean.FIELDS.AN_INT, "10");

        TestBean testBean = new TestBean();
        DataBinder db = new DataBinder(testBean, "testBean");
        db.setRequiredFields(TestBean.FIELDS.AN_INT);

        db.bind(mpv);
        db.getBindingResult().getAllErrors().forEach(d -> log.info("Errors: {}", d));

        log.info("Bean: {}", testBean);
    }
}