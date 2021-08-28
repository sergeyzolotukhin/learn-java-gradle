package ua.in.sz.house.material;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@ToString
public class Material {
    private final MaterialType materialType;
    private final double quantity;
    private final List<Material> components = new ArrayList<>();

    public Material(MaterialType materialType, double quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
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

    public static Material of(MaterialType materialType, double quantity) {
        return new Material(materialType, quantity);
    }
}
