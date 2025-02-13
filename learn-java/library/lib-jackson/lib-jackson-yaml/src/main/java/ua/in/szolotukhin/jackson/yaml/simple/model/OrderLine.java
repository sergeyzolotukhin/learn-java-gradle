package ua.in.szolotukhin.jackson.yaml.simple.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderLine {
    private String item;
    private int quantity;
    private BigDecimal unitPrice;
}
