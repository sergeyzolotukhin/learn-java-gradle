package ua.in.sz.house.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.in.sz.house.shop.order.MaterialOrder;

@Getter
@AllArgsConstructor
public class CarOrder {
    private final MaterialOrder materialOrder;
    private final CargoCar car;
    private final Distance distance;
}
