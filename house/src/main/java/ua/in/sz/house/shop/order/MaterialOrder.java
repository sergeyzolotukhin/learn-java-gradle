package ua.in.sz.house.shop.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.in.sz.house.material.MaterialType;

import java.util.List;


@Getter
@RequiredArgsConstructor(staticName = "of")
public class MaterialOrder {
    private final List<Item> items;

    public MaterialOrder.Item get(MaterialType materialType) {
        return items.stream()
                .filter(m -> m.materialType().equals(materialType))
                .findFirst()
                .orElse(null);
    }

    public interface Item {
        MaterialType materialType();
        double quantity();
        double cost();
    }
}
