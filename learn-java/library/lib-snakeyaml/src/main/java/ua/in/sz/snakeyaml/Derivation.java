package ua.in.sz.snakeyaml;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Derivation {
    private String name;
    private String description;
    private List<Attribute> attributes;
}
