package com.stocksense.stocksense.product;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.company.Company;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_company_sku", columnNames = {"company_id", "sku"})
        },
        indexes = {
                @Index(name = "idx_product_sku", columnList = "sku"),
                @Index(name = "idx_product_company", columnList = "company_id")
        }
)
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private int quantity = 0;

    private int price = 0;

    @ManyToOne()
    @JoinColumn(
            name = "company_id"
    )
    private Company company;
}
