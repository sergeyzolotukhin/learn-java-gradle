package ua.in.sz.object.mother;


import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class FactorVO {
    private String from;
    private String to;
    private String type;
    private Integer value;
}
