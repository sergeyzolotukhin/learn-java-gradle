package ua.in.sz.house.support;

import lombok.AllArgsConstructor;
import ua.in.sz.house.building.House;
import ua.in.sz.house.support.SupportCostCalculator;

@AllArgsConstructor(staticName = "of")
public class SupportReport {
    private final House house;

    public String report() {
        SupportCostCalculator supportCostCalculator = SupportCostCalculator.of(house);

        return "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", supportCostCalculator.costPerYear() / 12.0);
    }
}
