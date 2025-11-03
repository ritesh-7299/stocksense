package com.stocksense.stocksense.product_variant;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name = "product_variants",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_variant_company_sku", columnNames = {"company_id", "sku"})
        },
        indexes = {
                @Index(name = "idx_product_variant_parent", columnList = "parent_id"),
                @Index(name = "idx_product_variant_sku", columnList = "sku")
        }
)
public class ProductVariant extends BaseEntity {
    @ElementCollection
    private Map<String, String> attributes;

    private String sku;

    @Column(name = "company_id", updatable = false, nullable = false)
    private UUID companyId;

    @ManyToOne
    @JoinColumn(
            name = "parent_id"
    )
    private Product parent;

    @PrePersist
    @PreUpdate
    private void syncCompanyId() {
        if (parent != null && parent.getCompany() != null) {
            this.companyId = parent.getCompany().getId();
        }
    }
}


