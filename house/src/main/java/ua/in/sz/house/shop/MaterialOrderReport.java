package ua.in.sz.house.shop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.house.shop.order.MaterialOrder;

@Slf4j
public class MaterialOrderReport {
    public static String report(MaterialOrder materialOrder) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\tMaterial order");

        int size = 38;
        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(String.format("\n\t%-10.10s %7.7s %6.6s %10.10s", "name", "quantity", "unit", "cost UAH"));
        sb.append(StringUtils.rightPad("\n\t=", size, "="));

        double totalCost = 0.0;
        for (MaterialOrder.Item material : materialOrder.items()) {
            sb.append(String.format("\n\t%-10.10s %7.0f %6.6s %10.0f",
                    material.materialType().getName(),
                    Math.ceil(material.quantity()),
                    material.unit().getName(),
                    Math.ceil(material.cost())));

            totalCost += material.cost();
        }

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(String.format("\n\t%-10.10s %7.7s %6.6s %10.0f",
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Math.ceil(totalCost)));

        return sb.toString();
    }
}
