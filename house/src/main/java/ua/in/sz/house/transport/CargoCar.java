package ua.in.sz.house.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CargoCar {
    private final String name;

    private final double length;
    private final double width;
    private final double height;

    private final double maxWeight;

    private final double comeInCost;
    private final double hourCost;
    private final double kmCost;

    private final double velocity;
}
