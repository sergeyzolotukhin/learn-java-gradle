package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.plugin.BussinessPlugin;

import java.util.List;

@Slf4j
@Getter
@Setter
public class BusinessService {
    private List<BussinessPlugin> plugins;

    void print() {
        plugins.forEach(BussinessPlugin::print);
    }
}
