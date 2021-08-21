package ua.in.sz.house.report;

import lombok.AllArgsConstructor;
import ua.in.sz.house.building.House;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;

@AllArgsConstructor(staticName = "of")
public class HouseReport {
    private final House house;

    public String report() {
        return "\nHouse info:" +
                String.format("\n\tsize: %.0f x %.0f m", house.getWidth(), house.getLength()) +
                String.format("\n\twall width %.0f mm", house.getWallWidth() * 1000.0) +
                String.format("\n\twall heat less %.2f kWt", house.getHeatLoss(TARGET_TEMPERATURE, -20.0) / 1000.0);
    }
}
