package ua.in.sz.jpa.cache.impl;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Synchronize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SCHEDULE")
@Synchronize("f")
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    @EqualsAndHashCode.Include
    private String name;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "MY_FANCY_FK_NAME"))
    private Workspace workspace;

    public void setWorkspace(Workspace workspace) {
        if (this.workspace != null) {
            this.workspace.schedules.remove(this);
        }

        this.workspace = workspace;

        if (this.workspace != null) {
            this.workspace.schedules.add(this);
        }
    }
}
