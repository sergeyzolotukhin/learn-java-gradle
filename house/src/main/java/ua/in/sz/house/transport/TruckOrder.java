package ua.in.sz.house.transport;

import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.transport.truck.Truck;

import java.util.List;

public record TruckOrder(List<Item> items) {
    public static record Item(Truck truck, Distance distance, MaterialOrder.Item materialOrder) {
    }
}
