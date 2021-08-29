package ua.in.sz.house.transport.truck;

public record DumpTruck(
        String name,
        double maxWeight,
        double comeInCost, double hourCost, double kmCost,
        double velocity
) implements Truck {
}
