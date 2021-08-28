package ua.in.sz.house.shop.order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialOrderReport {
    public static String report(MaterialOrder materialOrder) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse order:");

        for (MaterialOrder.Item material : materialOrder.getItems()) {
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH",
                    material.materialType(), material.quantity(), material.cost()));
        }

        return sb.toString();
    }
}
