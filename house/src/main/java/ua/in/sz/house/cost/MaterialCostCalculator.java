package ua.in.sz.house.cost;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.Material;

import java.util.Map;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialCostCalculator {
    private final Map<Material.Names, Double> costs = ImmutableMap.<Material.Names, Double>builder()
            .put(Material.Names.BRICK, 3.3)
            .put(Material.Names.CEMENT, 1800.0 / 1000.0)
            .put(Material.Names.CEMENT_MORTAR, 0.0)
            .put(Material.Names.SANG, 180.0 / 1000.0)
            .build();

    public double cost(Material material) {
        Double cost = costs.get(material.getName());
        if (cost == null) {
            throw new IllegalStateException("The cost of material [" + material.getName() + "] not found");
        }

        return material.getQuantity() * cost;
    }
}
