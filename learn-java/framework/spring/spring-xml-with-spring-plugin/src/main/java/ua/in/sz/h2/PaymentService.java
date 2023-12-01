package ua.in.sz.h2;

import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import ua.in.sz.h2.spi.PaymentMethod;
import ua.in.sz.h2.spi.PaymentPlugin;

import java.util.Optional;

@EnablePluginRegistries(PaymentPlugin.class)
public class PaymentService {
    private PluginRegistry<PaymentPlugin, PaymentMethod> plugins;

    public Optional<PaymentPlugin> choosePaymentMethod(PaymentMethod paymentMethod) {
        return plugins.getPluginFor(paymentMethod);
    }
}
