package com.stocksense.stocksense.company;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
}
