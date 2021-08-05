package ua.in.sz.house.boiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract class Resource {
    public abstract double getQuantity();

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Electricity extends Resource {
        private double quantity;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Pellet extends Resource {
        private double quantity;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Gas extends Resource {
        private double quantity;
    }
}
