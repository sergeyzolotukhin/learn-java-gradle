package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

import java.util.List;

public record MaterialOrder(List<Item> items) {
    public MaterialOrder.Item get(MaterialType materialType) {
        return items.stream()
                .filter(m -> m.materialType().equals(materialType))
                .reduce(Item::add)
                .orElse(null);
    }

    public interface Item {
        MaterialType materialType();

        double quantity();

        double cost();

        MaterialUnit unit();

        Item add(Item item);
    }
}
