package bankingSystem.server.domain.account.service;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import bankingSystem.server.domain.customer.service.CustomerService;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Test
    public void 입출금테스트() {
        Customer customer1 = new Customer("t1", 123, "ttmail", "남", "ttasd123", "123123");
        customerService.register(customer1);
        accountService.register(customer1);

        int b1 = accountService.deposit(customer1.getUserId(), 4000);
        Account account = accountRepository.findByCustomerUserId(customer1.getUserId());
        assertThat(account.getBalance()).isEqualTo(4000);

        int b2 = accountService.withdraw(customer1.getUserId(), 3000);
        account = accountRepository.findByCustomerUserId(customer1.getUserId());
        assertThat(account.getBalance()).isEqualTo(1000);
    }

    @Test
    public void 계좌조회() {
        Account account = accountService.findAllWithTransaction("asd123");
        List<Transaction> txs = account.getTransactions();

        log.info("account = {}", account);

        for (Transaction tx : txs) {
            log.info("account tx = {}", tx.getAmtWithdrawal());
        }

    }
}