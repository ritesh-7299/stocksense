package com.stocksense.stocksense.product_variant;

import com.stocksense.stocksense.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
public class ProductVariant extends BaseEntity {
}
