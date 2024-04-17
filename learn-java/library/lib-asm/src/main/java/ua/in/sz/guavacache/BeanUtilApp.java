package ua.in.sz.guavacache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;

@Slf4j
public class BeanUtilApp {
    public static void main(String[] args) {
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(Scannee.class);
        for (PropertyDescriptor pd : pds) {
            log.info("property: {}", pd.getName());
        }
    }
}
