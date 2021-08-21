package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.MaterialCostCalculator;
import ua.in.sz.house.cost.SupportCostCalculator;
import ua.in.sz.house.cost.TransportCostCalculator;
import ua.in.sz.house.material.*;
import ua.in.sz.house.transport.Cars;

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

        MaterialCalculator materialCalculator = MaterialCalculator.of(house);
        MaterialCostCalculator materialCostCalculator = MaterialCostCalculator.of(materialCalculator);
        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95());

        double blockCount = materialCalculator.blockCount();
        double cementMortar = materialCalculator.cementMortar();
        double cement = materialCalculator.cementKg();
        double sang = materialCalculator.sangKg();

        Packages.BrickPackage pack = Packages.brickPackage();
        double requiredPackageCount = Math.ceil(blockCount / pack.getCount());
        double brickTravelCost = transportCostCalculator.cost(pack, requiredPackageCount);

        String materialInfo = "\nHouse materials:" +
                String.format("\n\tblock count %.0f cost %.0f UAH transport cost %.0f UAH",
                        blockCount, materialCostCalculator.blockCost(), brickTravelCost) +
                String.format("\n\tcement mortar %.2f M3", cementMortar) +
                String.format("\n\tcement %.3f T cost %.2f UAH", cement / 1000.0, materialCostCalculator.cementCost()) +
                String.format("\n\tsang %.3f T cost %.2f UAH", sang / 1000.0, materialCostCalculator.sangCost());

        String supportCostInfo = "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0);

        log.info(houseInfo + materialInfo + supportCostInfo);
    }
}
