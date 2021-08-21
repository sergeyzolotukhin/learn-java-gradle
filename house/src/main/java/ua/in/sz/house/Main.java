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

        // Склад Белогородка -> Ворзель
        double comeInDistance = 26.8;
        double comeOutDistance = 33.6;
        double travelDistance = 26.8;

        double averageVelocity = 60.0; // Km/h
        double loadTime = 1; // hours
        double unloadTime = 1; // hours


        double totalTime = 0;
        double totalDistance = 0;
        double comeInCount = 0;

        double runTime = travelDistance / averageVelocity;
        double comeInTime = comeInDistance / averageVelocity;
        double comeOutTime = comeOutDistance / averageVelocity;
        double travelCargoTime = loadTime + runTime + unloadTime;

        double leftTravel = travelCount;
        while (leftTravel > 0) {
            totalTime += comeInTime;
            totalDistance += comeInDistance;
            comeInCount++;
            log.info("come in time {} min", String.format("%.0f", comeInTime * 60));

            double leftWorkTime = 8.0;
            leftWorkTime -= comeInTime;
            while (leftWorkTime > comeOutTime) {
                if (leftWorkTime < travelCargoTime) {
                    break;
                }

                // do travel cargo from stock to house
                leftWorkTime = leftWorkTime - travelCargoTime;
                totalTime = totalTime + travelCargoTime;
                totalDistance += travelDistance;
                leftTravel--;
                log.info("travel cargo time {} hours {} min",
                        String.format("%.0f", travelCargoTime),
                        String.format("%.0f", (travelCargoTime - Math.floor(travelCargoTime)) * 60));

                if (leftWorkTime < runTime + loadTime + runTime + unloadTime) {
                    break;
                }

                totalDistance += travelDistance;
                totalTime += runTime;
                log.info("travel to load time {} min",
                        String.format("%.0f", runTime * 60));
            }

            totalTime += comeOutTime;
            totalDistance += comeOutDistance;
            log.info("come out time {} min", String.format("%.0f", comeOutTime * 60));
        }

        log.info("Total time to work {} hours, total distance {} km, come in count {}", Math.ceil(totalTime), totalDistance, comeInCount);
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
                String.format("\n\tcement %.3f T cost %.2f UAH", cement / 1000.0, materialCostCalculator.cementCost()) +
                String.format("\n\tsang %.3f T cost %.2f UAH", sang / 1000.0, materialCostCalculator.sangCost());

        String supportCostInfo = "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0);

        log.info(houseInfo + materialInfo + supportCostInfo);
    }
}
