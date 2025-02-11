package ua.in.sz.hibernate.merge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@EqualsAndHashCode
@FieldNameConstants
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "DET_GROUP")
public class Group {
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

    @JoinTable(name = "DEP_GROUP_TO_PARENT",
            foreignKey = @ForeignKey(name = "FK_PARENT_GR_01"),
            inverseForeignKey = @ForeignKey(name = "FK_PARENT_GR_2")
    )
    @Builder.Default
    private Set<Group> parents = new HashSet<>();
    @OneToMany
    @JoinTable(name = "DEP_GROUP_TO_CHILD",
            foreignKey = @ForeignKey(name = "FK_CHILD_GR_1"),
            inverseForeignKey = @ForeignKey(name = "FK_CHILD_GR_2")
    )
    @Builder.Default
    private Set<Group> children = new HashSet<>();

    @SuppressWarnings("unused")
    public static class GroupBuilder {
        public GroupBuilder withParent(Group parent) {
            if (this.parents$value == null) {
                this.parents$value = new HashSet<>();
            }
            this.parents$value.add(parent);
            this.parents$set = true;
            return this;
        }

        public GroupBuilder withChild(Group child) {
            if (this.children$value == null) {
                this.children$value = new HashSet<>();
            }
            this.children$value.add(child);
            this.children$set = true;
            return this;
        }
    }
}
