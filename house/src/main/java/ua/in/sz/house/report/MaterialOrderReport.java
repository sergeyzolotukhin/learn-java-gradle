package ua.in.sz.house.report;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.shop.MaterialOrder;

import java.util.List;

@Slf4j
public class MaterialOrderReport {
    public static String report(List<MaterialOrder> materials) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse materials:");

        for (MaterialOrder material : materials) {
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH transport cost %.0f UAH",
                    material.code(), material.getQuantity(), material.getCost(), 0.0));
        }

        return sb.toString();
    }
}
