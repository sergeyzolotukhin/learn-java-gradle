package ua.in.sz.house.shop;

import ua.in.sz.house.material.MaterialCode;

/**
 * https://specnavigator.ru/materialy/kirpich/raschet-kolichestva-v-poddone.html
 */
public class MaterialPackages {
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
}
