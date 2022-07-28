package ua.in.sz.object.mother;

import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
public class EntityVO {
    private String from;
    private String to;
    private String identification;
    private String primaryRole;
    private List<FactorVO> factors;

    EntityVO(String from, String to, String identification, String primaryRole, List<FactorVO> factors) {
        this.from = from;
        this.to = to;
        this.identification = identification;
        this.primaryRole = primaryRole;
        this.factors = factors;
    }

    public static EntityVOBuilder builder() {
        return new EntityVOBuilder();
    }


    public static class EntityVOBuilder {
        private List<FactorVO> factors;

        public EntityVOBuilder factor(String type, Integer value) {
            if (this.factors == null) {
                this.factors = new ArrayList<>();
            }

            FactorVO factor = FactorVO.builder()
                    .from(this.from)
                    .to(this.to)
                    .type(type)
                    .value(value)
                    .build();

            this.factors.add(factor);

            return this;
        }
    }
}
