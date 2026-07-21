package com.store.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
    private Long customerId;

    // cart can have more than one of the same book, therefore we need to create an item for the cart containing also
    // the quantity of a certain book
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartItem> items = new ArrayList<>();

    public Cart(Long customerId) {
        this.customerId = customerId;
    }

    public Cart() {

    }
}