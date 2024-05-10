package ua.in.szolotukhin.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    private String name;
    private int value;
}
