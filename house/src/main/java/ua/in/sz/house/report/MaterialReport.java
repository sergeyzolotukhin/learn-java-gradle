package ua.in.sz.house.report;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.MaterialCostCalculator;
import ua.in.sz.house.cost.TransportCostCalculator;
import ua.in.sz.house.material.AllMaterialCalculator;
import ua.in.sz.house.transport.Packages;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.Distances;

import java.util.List;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialReport {
    private final House house;

    public String report() {
        AllMaterialCalculator materialCalculator = AllMaterialCalculator.of(house);
        MaterialCostCalculator materialCostCalculator = MaterialCostCalculator.of(materialCalculator);

        List<AllMaterialCalculator.Material> materials = materialCalculator.calculate();
        log.info("Materials: {}", materials);

        double blockCount = materialCalculator.blockCount();
        double cementMortar = materialCalculator.cementMortar();
        double cement = materialCalculator.cementKg();
        double sang = materialCalculator.sangKg();

        double blockCost = materialCostCalculator.blockCost();
        double cementCost = materialCostCalculator.cementCost();
        double sangCost = materialCostCalculator.sangCost();

        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95(), Distances.brickStockToTarasovo());

        Packages.BrickPackage pack = Packages.brickPackage();
        double requiredPackageCount = Math.ceil(blockCount / pack.getCount());
        double brickTravelCost = transportCostCalculator.cost(pack, requiredPackageCount);

        return "\nHouse materials:" +
                String.format("\n\tblock count %.0f cost %.0f UAH transport cost %.0f UAH", blockCount, blockCost, brickTravelCost) +
                String.format("\n\tcement mortar %.2f M3", cementMortar) +
                String.format("\n\tcement %.3f T cost %.2f UAH", cement / 1000.0, cementCost) +
                String.format("\n\tsang %.3f T cost %.2f UAH", sang / 1000.0, sangCost);
    }
}
