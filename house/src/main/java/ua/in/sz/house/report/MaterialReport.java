package ua.in.sz.house.report;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.cost.MaterialCostCalculator;
import ua.in.sz.house.material.Material;

import java.util.List;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialReport {
    private final List<Material> materials;

    public String report() {
        MaterialCostCalculator materialCostCalculator = MaterialCostCalculator.of();

        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse materials:");

        for (Material material : materials) {
            double cost = materialCostCalculator.cost(material);
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH transport cost %.0f UAH",
                    material.getName(), material.getQuantity(), cost, 0.0));
        }

//        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95(), Distances.brickStockToTarasovo());
//
//        Packages.BrickPackage pack = Packages.brickPackage();
//        double requiredPackageCount = Math.ceil(blockCount / pack.getCount());
//        double brickTravelCost = transportCostCalculator.cost(pack, requiredPackageCount);

        return sb.toString();
    }
}
