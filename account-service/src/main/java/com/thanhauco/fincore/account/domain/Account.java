package com.thanhauco.fincore.account.domain;

import com.thanhauco.fincore.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
    private String accountNumber;
    private String ownerId;
    private BigDecimal balance;
    private String currency;
    private boolean active;
    private String type; // SAVINGS, CHECKING
}
