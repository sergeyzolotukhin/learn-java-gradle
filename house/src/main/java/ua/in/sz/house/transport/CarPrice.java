package ua.in.sz.house.transport;

import java.util.List;

public record CarPrice(List<Item> items) {
    public static record Item(CargoCar car, double distance, double forward, double weight, double cost) {
    }
}
