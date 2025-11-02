package com.stocksense.stocksense.user;

import com.stocksense.stocksense.common.model.BaseEntity;
import com.stocksense.stocksense.company.Company;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_company_id", columnList = "company_id", unique = true),
                @Index(name = "idx_email", columnList = "email", unique = true)
        }
)
public class User extends BaseEntity {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(
            name = "company_id",
            nullable = false
    )
    private Company company;
}

