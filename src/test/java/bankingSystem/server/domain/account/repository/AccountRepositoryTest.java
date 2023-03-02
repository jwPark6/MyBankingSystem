package bankingSystem.server.domain.account.repository;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void Lazy계좌조회() {
        Account account = accountRepository.findByCustomerUserIdWithTx("asd123");

        for (Transaction tx : account.getTransactions()) {
            log.info("tx = {}", tx);
        }
    }
}