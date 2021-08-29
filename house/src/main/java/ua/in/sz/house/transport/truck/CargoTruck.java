package ua.in.sz.house.transport.truck;

public record CargoTruck(
        String name,
        double length, double width, double height,
        double maxWeight,
        double comeInCost, double hourCost, double kmCost,
        double velocity
) implements Truck {

}
