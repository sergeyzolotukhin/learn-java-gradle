package ua.in.sz.hibernate.merge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany
    @JoinTable(name = "DET_DETERMINANT_TO_GROUP",
            foreignKey = @ForeignKey(name = "FK_DET_DETERMINANT_TO_GROUP_1"),
            inverseForeignKey = @ForeignKey(name = "FK_DET_DETERMINANT_TO_GROUP_2")
    )
    @Builder.Default
    private Set<Group> parentGroups = new HashSet<>();

    public Determinant(Long id, String name, String description, Set<Group> parentGroups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentGroups = parentGroups;

        this.parentGroups.forEach(g -> g.getDeterminants().add(this));
    }

    public boolean addParentGroup(Group group) {
        boolean added = this.parentGroups.add(group);
        if (added) {
            group.addDeterminant(this);
        }
        return added;
    }

    @SuppressWarnings("unused")
    public static class DeterminantBuilder {
        public DeterminantBuilder withGroup(Group parent) {
            if (this.parentGroups$value == null) {
                this.parentGroups$value = new HashSet<>();
            }
            this.parentGroups$value.add(parent);
            this.parentGroups$set = true;
            return this;
        }
    }
}
