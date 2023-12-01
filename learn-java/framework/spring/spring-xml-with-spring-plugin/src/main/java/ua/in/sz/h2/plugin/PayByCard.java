package ua.in.sz.h2.plugin;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.spi.PaymentMethod;
import ua.in.sz.h2.spi.PaymentPlugin;

@Slf4j
public class PayByCard implements PaymentPlugin {

    @Override
    public void pay(int paymentAmount) {
        log.info("Paid by card, amount: {}, {}", paymentAmount, this);
    }

    @Override
    public boolean supports(PaymentMethod paymentMethod) {
        return paymentMethod == (PaymentMethod.CARD);
    }
}
