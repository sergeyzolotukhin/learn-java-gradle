package ua.in.sz.house.transport;

import ua.in.sz.house.transport.truck.Truck;

import java.util.List;

public record TruckPrice(List<Item> items) {
    public static record Item(Truck car, double distance, double forward, double weight, double cost) {
    }
}
