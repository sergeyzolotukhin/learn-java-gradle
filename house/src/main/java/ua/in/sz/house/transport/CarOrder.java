package ua.in.sz.house.transport;

import ua.in.sz.house.shop.order.MaterialOrder;

import java.util.List;

public record CarOrder(List<Item> items) {
    public static record Item(MaterialOrder.Item materialOrder, CargoCar car, Distance distance) {
    }
}
