package ua.in.sz.h2;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import ua.in.sz.h2.spi.PaymentMethod;
import ua.in.sz.h2.spi.PaymentPlugin;

import java.util.Optional;

@EnablePluginRegistries(PaymentPlugin.class)
@Slf4j
@Setter
public class PaymentService {
    private PluginRegistry<PaymentPlugin, PaymentMethod> pluginRegistry;

    public Optional<PaymentPlugin> choosePaymentMethod(PaymentMethod paymentMethod) {
        return pluginRegistry.getPluginFor(paymentMethod);
    }
}
