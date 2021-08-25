package ua.in.sz.house.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillOfMaterial<T extends Material> extends ArrayList<T> {
    private BillOfMaterial(Collection<T> c) {
        super(c);
    }

    public T get(MaterialType materialType) {
        return this.stream()
                .filter(m -> materialType.equals(m.getMaterialType()))
                .findFirst()
                .orElse(null);
    }

    public static <E extends Material> BillOfMaterial<E> of(List<E> materials) {
        return new BillOfMaterial<E>(materials);
    }

}
