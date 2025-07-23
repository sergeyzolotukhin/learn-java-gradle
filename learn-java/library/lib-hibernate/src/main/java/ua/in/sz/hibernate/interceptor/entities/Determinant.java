package ua.in.sz.hibernate.interceptor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.NaturalId;

@SuppressWarnings("JpaDataSourceORMInspection")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldNameConstants
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "DET_DETERMINANT")
public class Determinant {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @NaturalId
    @Column(name = "NAME")
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;
    @Column(name = "DESCRIPTION")
    @ToString.Include
    private String description;

    public Determinant(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
