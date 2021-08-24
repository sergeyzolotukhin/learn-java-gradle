package ua.in.sz.house.material;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://specnavigator.ru/materialy/kirpich/raschet-kolichestva-v-poddone.html
 */
public class MaterialPackages {

    private static final Map<MaterialCode, MaterialPackage> materialPackages = ImmutableMap.<MaterialCode, MaterialPackage>builder()
            .put(MaterialCode.BRICK, brickPackage())
            .put(MaterialCode.CEMENT, cementPackage())
            .put(MaterialCode.SANG, sangPackage())
            .build();

    public static BillOfMaterials<MaterialOrder> packages(BillOfMaterials<Material> billOfMaterials) {
        List<MaterialOrder> packages = billOfMaterials.stream()
                .map(MaterialPackages::createOrder)
                .collect(Collectors.toList());

        return BillOfMaterials.of(packages);
    }

    private static MaterialOrder createOrder(Material material) {
        MaterialPackage pack = materialPackages.get(material.getMaterialCode());
        if (pack == null) {
            throw new IllegalStateException("The packed of material [" + material.code() + "] not found");
        }

        double packCount = Math.ceil(material.getQuantity() / pack.getCount());
        return MaterialOrder.of(pack, packCount);
    }

    /**
     * количества Полнотелый, стандартный кирпича в поддоне по евростандарту габаритами 120*80 см.
     */
    public static MaterialPackage brickPackage() {
        return new MaterialPackage(MaterialCode.BRICK, 1.2, 0.8, 1.0, 420, 3.5 * 420);
    }

    /**
     * Размер стандартного мешка с цементом весом 50 кг равен 60*49,5*9 см.
     */
    public static MaterialPackage cementPackage() {
        return new MaterialPackage(MaterialCode.CEMENT, 0.44, 0.38, 0.09, 50, 50);
    }

    public static MaterialPackage sangPackage() {
        return new MaterialPackage(MaterialCode.SANG, 0.95, 0.55, 0.55, 50, 50);
    }
}
