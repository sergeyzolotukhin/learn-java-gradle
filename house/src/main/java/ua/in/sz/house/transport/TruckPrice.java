package ua.in.sz.house.transport;

import java.util.List;

public record TruckPrice(List<Item> items) {
    public static record Item(CargoTruck car, double distance, double forward, double weight, double cost) {
    }
}
