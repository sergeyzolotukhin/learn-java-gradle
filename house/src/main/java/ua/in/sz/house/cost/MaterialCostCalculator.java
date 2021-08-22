package ua.in.sz.house.cost;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.Material;

import java.util.List;
import java.util.Map;

@Slf4j
public class MaterialCostCalculator {
    private static final Map<Material.Name, Double> costs = ImmutableMap.<Material.Name, Double>builder()
            .put(Material.Name.BRICK, 3.3)
            .put(Material.Name.CEMENT, 1800.0 / 1000.0)
            .put(Material.Name.CEMENT_MORTAR, 0.0)
            .put(Material.Name.SANG, 180.0 / 1000.0)
            .build();

    public static void calculate(List<Material> materials) {
        materials.forEach(m -> m.setCost(cost(m)));
    }

    public static double cost(Material material) {
        Double cost = costs.get(material.getName());
        if (cost == null) {
            throw new IllegalStateException("The cost of material [" + material.getName() + "] not found");
        }

        return material.getQuantity() * cost;
    }
}
