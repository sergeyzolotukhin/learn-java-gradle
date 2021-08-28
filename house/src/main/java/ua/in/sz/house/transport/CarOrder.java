package ua.in.sz.house.transport;

import ua.in.sz.house.shop.order.MaterialOrder;

import java.util.List;

public class CarOrder {
    private final List<Item> items;

    public CarOrder(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public static record Item(MaterialOrder.Item materialOrder, CargoCar car, Distance distance) {
    }
}
