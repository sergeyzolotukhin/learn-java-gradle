package ua.in.sz.house.report;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.Material;

import java.util.List;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialReport {
    private final List<Material> materials;

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nHouse materials:");

        for (Material material : materials) {
            sb.append(String.format("\n\t%s quantity %.0f cost %.0f UAH transport cost %.0f UAH",
                    material.getName(), material.getQuantity(), material.getCost(), material.getTransportCost()));
        }

        return sb.toString();
    }
}
