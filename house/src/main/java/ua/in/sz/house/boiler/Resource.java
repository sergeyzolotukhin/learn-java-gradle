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
}
