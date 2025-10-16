package ua.in.sz.hibernate.owning.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
//    @OneToMany

    // 1. bi-directional - inverse side
    // The attribute mappedBy indicates that the entity in this side is the inverse of the relationship
//    @OneToMany(mappedBy = "customer")

    // 2. bi-directional - owner side
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private List<Order> orders;
}
