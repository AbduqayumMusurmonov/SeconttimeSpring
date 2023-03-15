package com.example.seconttimespring.services;

import com.example.seconttimespring.entity.Account;
import com.example.seconttimespring.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        Account result = accountRepository.save(account);
        return result;
    }
}
