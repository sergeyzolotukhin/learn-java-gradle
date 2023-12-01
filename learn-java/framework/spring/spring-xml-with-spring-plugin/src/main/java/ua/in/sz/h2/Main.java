package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.sz.h2.spi.PaymentMethod;
import ua.in.sz.h2.spi.PaymentPlugin;

import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

        PaymentService paymentService = context.getBean(PaymentService.class);
        Optional<PaymentPlugin> paymentPlugin = paymentService.choosePaymentMethod(PaymentMethod.CARD);
        paymentPlugin.ifPresent(p -> p.pay(10));

        Optional<PaymentPlugin> paymentPlugin2 = paymentService.choosePaymentMethod(PaymentMethod.PAYPAL);
        paymentPlugin2.ifPresent(p -> p.pay(10));

        context.close();
    }
}