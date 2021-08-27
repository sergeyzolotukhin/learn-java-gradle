package ua.in.sz.house.shop.order;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderReport {
    public static String report(List<Order> order) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse order:");

        for (Order material : order) {
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH",
                    material.getMaterialType(), material.getQuantity(), material.getCost()));
        }

        return sb.toString();
    }
}
