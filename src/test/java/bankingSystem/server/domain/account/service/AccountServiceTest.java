package bankingSystem.server.domain.account.service;

import bankingSystem.server.domain.account.dto.AccountDto;
import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import bankingSystem.server.domain.customer.service.CustomerService;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static jodd.util.ThreadUtil.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

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
        AccountDto account = accountService.findAllWithTransaction("asd123");
        List<Transaction> txs = account.getTransactions();

        log.info("account = {}", account);

        for (Transaction tx : txs) {
            log.info("account tx = {}", tx.getAmtWithdrawal());
        }

    }

    @Test
    public void 단독이체() {
        Customer customer1 = customerRepository.findById("asd123").get();
        Customer customer2 = customerRepository.findById("fff123").get();
        Account account1 = accountRepository.findByCustomerUserId(customer1.getUserId());

        int oldBalance1 = account1.getBalance();
        int curBalance = accountService.transfer(customer1.getUserId(), 3000, customer2.getUserId());

        assertThat(oldBalance1 - 3000).isEqualTo(curBalance);
    }

    @Test
    public void 동시이체테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        AtomicInteger result1 = new AtomicInteger();
        AtomicInteger result2 = new AtomicInteger();

        AtomicInteger initialBalance1 = new AtomicInteger();
        AtomicInteger initialBalance2 = new AtomicInteger();
        //initDB 하기 전에 부르는거 같음
        executorService.execute(() -> {
            //result1.set(accountService.deposit("fff123", 3000));
            initialBalance1.set(accountRepository.findByCustomerUserId("fff123").getBalance());
            result1.set(accountService.transfer("asd123", 3000, "fff123"));
            latch.countDown();
        });
        executorService.execute(() -> {
            //result2.set(accountService.deposit("fff123", 1000));
            initialBalance2.set(accountRepository.findByCustomerUserId("fff123").getBalance());
            result2.set(accountService.transfer("333123", 1000, "fff123"));
            latch.countDown();
        });
        latch.await();

        assertThat(result1.get()).isEqualTo(initialBalance1.get() + 3000);
        assertThat(result2.get()).isEqualTo(initialBalance2.get() + 1000);
    }

    @Test
    public void 다중이체() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(30);

        int balance = accountRepository.findByCustomerUserId("asd123").getBalance();
        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                //accountService.deposit("asd123", 50);
                try {
                    accountService.lockDeposit("asd123", 50);
                } catch (InterruptedException e) {

                }
                latch.countDown();
            });
        }
        latch.await();

        log.info("initial balance = {}", balance);

        Account account = accountRepository.findByCustomerUserId("asd123");
        assertThat(account.getBalance()).isEqualTo(balance + 50 * 30);
    }
}