package ua.in.sz.object.mother;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class EntityVO {
    private String from;
    private String to;
    private String identification;
    private String primaryRole;
    private List<FactorVO> factors;
}
