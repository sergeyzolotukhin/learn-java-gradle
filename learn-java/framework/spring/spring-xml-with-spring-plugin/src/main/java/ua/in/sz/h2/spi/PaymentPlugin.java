package ua.in.sz.h2.spi;

import org.springframework.plugin.core.Plugin;

public interface PaymentPlugin extends Plugin<PaymentMethod> {
    void pay(int paymentAmount);
}
