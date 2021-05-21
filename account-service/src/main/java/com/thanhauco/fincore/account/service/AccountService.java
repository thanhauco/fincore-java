package com.thanhauco.fincore.account.service;

import com.thanhauco.fincore.account.domain.Account;
import com.thanhauco.fincore.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(String ownerId, String currency, String type) {
        Account account = Account.builder()
                .ownerId(ownerId)
                .currency(currency)
                .type(type)
                .balance(BigDecimal.ZERO)
                .active(true)
                .accountNumber(UUID.randomUUID().toString())
                .build();
        return accountRepository.save(account);
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    
    public List<Account> getUserAccounts(String userId) {
        return accountRepository.findByOwnerId(userId);
    }
}
