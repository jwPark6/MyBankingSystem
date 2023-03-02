package bankingSystem.server.domain.transaction.repository;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void 거래내역조회() {
        Account account = accountRepository.findByCustomerUserId("asd123");

        List<Transaction> txs = transactionRepository.findByAccount(account.getId());

        for (Transaction t : txs) {
            log.info("transaction = {}", t);
        }
    }
}