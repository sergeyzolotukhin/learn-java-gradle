package ua.in.sz.house.shop.order;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderReport {
    public static String report(List<MaterialOrder> materialOrder) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse order:");

        for (MaterialOrder material : materialOrder) {
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH",
                    material.materialType(), material.quantity(), material.cost()));
        }

        return sb.toString();
    }
}
