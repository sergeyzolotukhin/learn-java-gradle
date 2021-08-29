package ua.in.sz.house.transport.truck;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DumpTruck implements Truck {
    private final String name;

    private final double maxWeight;

    private final double comeInCost;
    private final double hourCost;
    private final double kmCost;

    private final double velocity;
}
