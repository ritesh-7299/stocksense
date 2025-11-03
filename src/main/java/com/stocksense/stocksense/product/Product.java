package com.stocksense.stocksense.product;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.company.Company;
import com.stocksense.stocksense.product_variant.ProductVariant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_company_name", columnNames = {"company_id", "name"})
        },
        indexes = {
                @Index(name = "idx_product_company", columnList = "company_id")
        }
)
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(
            name = "company_id"
    )
    private Company company;

    @OneToMany(
            mappedBy = "parent",
            fetch = FetchType.LAZY
    )
    private List<ProductVariant> variants;
}
