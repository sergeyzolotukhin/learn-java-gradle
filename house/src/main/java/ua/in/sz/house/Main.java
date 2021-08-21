package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.SupportCostCalculator;
import ua.in.sz.house.materials.BrickCalculator;
import ua.in.sz.house.materials.CementMortarCalculator;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        SupportCostCalculator costCalculator = SupportCostCalculator.of(house);

        String houseInfo = "\nHouse info:" +
                String.format("\n\tsize: %.0f x %.0f m", house.getWidth(), house.getLength()) +
                String.format("\n\twall width %.0f mm", house.getWallWidth() * 1000.0) +
                String.format("\n\twall heat less %.2f kWt", house.getHeatLoss(TARGET_TEMPERATURE, -20.0) / 1000.0);

        BrickCalculator brickCalculator = BrickCalculator.of(house);
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.cementMortar();
        double cement = cementKg(cementMortar);
        double sang = sandKg(cementMortar);

        String materialInfo = "\nHouse materials:" +
                String.format("\n\tblock count %.0f cost %.0f UAH", brickCalculator.blockCount(), brickCalculator.blockCount() * 3.3) +
                String.format("\n\tcement mortar %.2f M3", cementMortar) +
                String.format("\n\tcement %.3f T cost %.2f UAH", cement / 1000.0, cement / 1000.0 * 1800.0)  +
                String.format("\n\tsang %.3f T cost %.2f UAH", sang / 1000.0, sang / 1000.0 * 180.0);

        String supportCostInfo = "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0);

        log.info(houseInfo + materialInfo + supportCostInfo);
    }

    private static double cementKg(double cementMortar) {
        double volume = cementMortar / 4.0;
        return volume * 1625; // кг в м3 песка
    }

    private static double sandKg(double cementMortar) {
        double volume = cementMortar * 3.0 / 4.0;
        return volume * 1500; // кг в м3 цемента
    }
}
