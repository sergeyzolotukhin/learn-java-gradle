package ua.in.sz.house.transport;

import ua.in.sz.house.shop.order.MaterialOrder;

import java.util.List;

public record TruckOrder(List<Item> items) {
    public static record Item(CargoTruck car, Distance distance, MaterialOrder.Item materialOrder) {
    }
}
