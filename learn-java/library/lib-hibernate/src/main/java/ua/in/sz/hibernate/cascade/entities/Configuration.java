package ua.in.sz.hibernate.cascade.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.SortedSet;
import java.util.TreeSet;

@ToString
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "CONFIG")
public class Configuration {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "config", cascade = CascadeType.ALL)
    private SortedSet<Parameter> parameters = new TreeSet<>();

    public Configuration(Long id, String name, SortedSet<Parameter> parameters) {
        this.id = id;
        this.name = name;
        this.parameters = parameters;

        this.parameters.forEach(p -> p.setConfig(this));
    }


    public static class ConfigurationBuilder {
        public ConfigurationBuilder parameter(Parameter parameter) {
            if (this.parameters$value == null) {
                this.parameters$value = new TreeSet<>();
            }
            this.parameters$value.add(parameter);
            this.parameters$set = true;
            return this;
        }
    }
}
