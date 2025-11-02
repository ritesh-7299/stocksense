package com.stocksense.stocksense.company;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.product.Product;
import com.stocksense.stocksense.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String logo;

    @OneToOne(
            mappedBy = "company"
    )
    private User owner;

    @OneToMany(
            mappedBy = "company",
            fetch = FetchType.LAZY
    )
    private List<Product> products;
}
