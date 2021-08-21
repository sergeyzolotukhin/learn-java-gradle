package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.MaterialCostCalculator;
import ua.in.sz.house.cost.TransportCostCalculator;
import ua.in.sz.house.material.MaterialCalculators;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.report.MaterialReport;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.Distances;
import ua.in.sz.house.transport.Packages;

import java.util.List;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        List<Material> materials = MaterialCalculators.of(house).calculate();
        MaterialCostCalculator.of().calculate(materials);
        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95(), Distances.brickStockToTarasovo());

        Packages.BrickPackage pack = Packages.brickPackage();
//        double requiredPackageCount = Math.ceil(blockCount / pack.getCount());
//        double brickTravelCost = transportCostCalculator.cost(pack, requiredPackageCount);

//        log.info(HouseReport.of(house).report());
        log.info(MaterialReport.of(materials).report());
//        log.info(SupportReport.of(house).report());
    }
}
