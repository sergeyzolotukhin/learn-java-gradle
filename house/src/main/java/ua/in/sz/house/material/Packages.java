package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Packages {

    /**
     * количества Полнотелый, стандартный кирпича в поддоне по евростандарту габаритами 120*80 см.
     * https://specnavigator.ru/materialy/kirpich/raschet-kolichestva-v-poddone.html
     */
    public static BrickPackage brickPackage() {
        return new BrickPackage(1.2, 0.8, 420, 3.5 * 420);
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class BrickPackage {
        private final double length;
        private final double width;
        private final double count;
        private final double weight;
    }
}
