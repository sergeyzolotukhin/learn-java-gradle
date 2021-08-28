package ua.in.sz.house.material;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Material {
    private final MaterialType materialType;
    private final double quantity;
    private final MaterialUnit unit;
    private final List<Material> components = new ArrayList<>();

    public Material(MaterialType materialType, double quantity, MaterialUnit unit) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.unit = unit;
    }

    public void add(Material material) {
        components.add(material);
    }

    public Material get(MaterialType materialType) {
        if (materialType.equals(this.materialType)) {
            return this;
        }

        return components.stream()
                .map(m -> m.get(materialType))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public List<Material> allComponents() {
        List<Material> result = new ArrayList<>();
        result.add(this);
        result.addAll(components.stream()
                .flatMap(c -> c.allComponents().stream())
                .collect(Collectors.toList()));
        return result;
    }
}
