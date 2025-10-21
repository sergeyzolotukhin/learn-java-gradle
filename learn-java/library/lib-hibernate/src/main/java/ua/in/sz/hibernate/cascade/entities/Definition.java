package ua.in.sz.hibernate.cascade.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@ToString
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "DEF")
public class Definition {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "definition", orphanRemoval = true, cascade = {CascadeType.ALL})
    private Set<Dependency> dependencies;

    public Definition(Long id, String name, Set<Dependency> dependencies) {
        this.id = id;
        this.name = name;
        this.dependencies = dependencies;

        emptyIfNull(this.dependencies).forEach(d -> d.setDefinition(this));
    }

    public static class DefinitionBuilder {
        public DefinitionBuilder dependency(Dependency dependency) {
            if (this.dependencies == null) {
                this.dependencies = new HashSet<>();
            }
            this.dependencies.add(dependency);
            return this;
        }
    }
}
