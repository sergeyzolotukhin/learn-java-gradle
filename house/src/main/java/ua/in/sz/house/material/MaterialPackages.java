package ua.in.sz.house.material;

public class MaterialPackages {

    /**
     * количества Полнотелый, стандартный кирпича в поддоне по евростандарту габаритами 120*80 см.
     * https://specnavigator.ru/materialy/kirpich/raschet-kolichestva-v-poddone.html
     */
    public static MaterialPackage brickPackage() {
        return new MaterialPackage(MaterialCode.BRICK,1.2, 0.8, 420, 3.5 * 420);
    }

}
