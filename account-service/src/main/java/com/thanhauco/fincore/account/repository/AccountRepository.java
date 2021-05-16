package com.thanhauco.fincore.account.repository;

import com.thanhauco.fincore.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByOwnerId(String ownerId);
}
