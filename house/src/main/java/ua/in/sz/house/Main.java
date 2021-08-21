package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.MaterialCostCalculator;
import ua.in.sz.house.cost.SupportCostCalculator;
import ua.in.sz.house.material.*;
import ua.in.sz.house.transport.Cars;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        double blockCount = 24640;
        Packages.BrickPackage pack = Packages.brickPackage();
        double requiredPackageCount = Math.ceil(blockCount / pack.getCount());

        Cars.DafCf65 car = Cars.dafCf65();

        double maxPackage = maxPackage(car, pack);
        log.info("Max package count {} weight {}", maxPackage, maxPackage * pack.getWeight() / 1000.0);

        double travelCount = Math.ceil(requiredPackageCount / maxPackage);
        log.info("Package count {} travel count {} sum block count {}",
                requiredPackageCount, travelCount, requiredPackageCount * pack.getCount());
    }

    private static double maxPackage(Cars.DafCf65 car, Packages.BrickPackage pack) {
        double maxPackagePerWeight = Math.floor(car.getMaxWeight() / pack.getWeight());
        log.info("Max package count per weight {} package weight {}", maxPackagePerWeight, pack.getWeight() / 1000.0);

        double packagePerWidth = Math.floor(car.getWidth() / pack.getLength());
        double packagePerLength = Math.floor(car.getLength() / pack.getWidth());
        double maxPackagePerLength = packagePerLength * packagePerWidth;
        log.info("Max package count per length {} package width {} car length {} weight {}",
                maxPackagePerLength, pack.getWidth(), car.getLength(), maxPackagePerLength * pack.getWeight() / 1000.0);

        return Math.min(maxPackagePerWeight, maxPackagePerLength);
    }

    public static void main1(String[] args) {
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

        double blockCount = materialCalculator.blockCount();
        double cementMortar = materialCalculator.cementMortar();
        double cement = materialCalculator.cementKg();
        double sang = materialCalculator.sangKg();

        String materialInfo = "\nHouse materials:" +
                String.format("\n\tblock count %.0f cost %.0f UAH", blockCount, materialCostCalculator.blockCost()) +
                String.format("\n\tcement mortar %.2f M3", cementMortar) +
                String.format("\n\tcement %.3f T cost %.2f UAH", cement / 1000.0, materialCostCalculator.cementCost())  +
                String.format("\n\tsang %.3f T cost %.2f UAH", sang / 1000.0, materialCostCalculator.sangCost());

        String supportCostInfo = "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0);

        log.info(houseInfo + materialInfo + supportCostInfo);
    }
}
