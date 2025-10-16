package ua.in.sz.hibernate.owning.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = {"customer"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    // 1. bi-directional - owner side
//    @ManyToOne
//    @JoinColumn(name = "customer_id")

    // 2. bi-directional - inverse side
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
