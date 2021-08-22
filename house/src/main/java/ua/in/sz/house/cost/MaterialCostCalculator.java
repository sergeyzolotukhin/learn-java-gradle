package ua.in.sz.house.cost;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCode;

import java.util.List;
import java.util.Map;

@Slf4j
public class MaterialCostCalculator {
    private static final Map<String, Double> costs = ImmutableMap.<String, Double>builder()
            .put(MaterialCode.BRICK.code(), 3.3)
            .put(MaterialCode.CEMENT.code(), 1800.0 / 1000.0)
            .put(MaterialCode.CEMENT_MORTAR.code(), 0.0)
            .put(MaterialCode.SANG.code(), 180.0 / 1000.0)
            .build();

    public static void calculate(List<Material> materials) {
        materials.forEach(m -> m.setCost(cost(m)));
    }

    public static double cost(Material material) {
        Double cost = costs.get(material.code());
        if (cost == null) {
            throw new IllegalStateException("The cost of material [" + material.code() + "] not found");
        }

        return material.getQuantity() * cost;
    }
}
