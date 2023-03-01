package bankingSystem.server.domain.account.controller;

import bankingSystem.server.domain.account.dto.AccountDto;
import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account")
    public AccountDto account (@RequestParam("userId") String userId) {
        return accountService.findAllWithTransaction(userId);
    }
}
