package bankingSystem.server.domain.account.controller;

import bankingSystem.server.domain.account.dto.AccountDto;
import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/account/deposit")
    public int deposit(@RequestParam("money") int money,
                       @RequestParam("userId") String userId) {
        return accountService.deposit(userId, money);
    }

    @PostMapping("/account/lockDeposit")
    public int lockDeposit(@RequestParam("money") int money,
                       @RequestParam("userId") String userId) throws InterruptedException {
        return accountService.lockDeposit(userId, money);
    }

    @PostMapping("/account/withdraw")
    public int withdraw(@RequestParam("money") int money,
                        @RequestParam("userId") String userId) {
        return accountService.withdraw(userId, money);
    }

    @PostMapping("/account/transfer")
    public int transfer(@RequestParam("money") int money,
                        @RequestParam("userId") String userId,
                        @RequestParam("fUserId") String fUserId) {
        return accountService.transfer(userId, money, fUserId);
    }
}
