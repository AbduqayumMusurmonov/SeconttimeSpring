package com.example.seconttimespring.webrest;

import com.example.seconttimespring.entity.Account;
import com.example.seconttimespring.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountResource {
    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("accounts")
    public ResponseEntity save(@RequestBody Account account){
        Account account1 = accountService.save(account);
        return ResponseEntity.ok(account1);
    }
}
