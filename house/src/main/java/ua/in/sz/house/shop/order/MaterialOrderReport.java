package ua.in.sz.house.shop.order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialOrderReport {
    public static String report(MaterialOrder materialOrder) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nMaterial order:");

        for (MaterialOrder.Item material : materialOrder.items()) {
            sb.append(String.format("\n\t%-10.10s quantity %7.0f cost %10.0f UAH",
                    material.materialType().getName(), material.quantity(), material.cost()));
        }

        return sb.toString();
    }
}
