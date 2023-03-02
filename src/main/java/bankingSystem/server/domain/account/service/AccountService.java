package bankingSystem.server.domain.account.service;

import bankingSystem.server.domain.account.dto.AccountDto;
import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.friend.repository.FriendRepository;
import bankingSystem.server.domain.transaction.entity.Transaction;
import bankingSystem.server.domain.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final FriendRepository friendRepository;

    private final TransactionRepository transactionRepository;

    private final RedissonClient redissonClient;

    private final PlatformTransactionManager txm;

    public void register(Customer customer) {
        Account account = new Account(customer, 0);
        accountRepository.save(account);
    }

//    @DistributeLock(key = "#userId")
//    public int deposit(String userId, int money) {
//        Account account = accountRepository.findByCustomerUserId(userId);
//        return account.updateBalance(money);
//    }

    public int lockDeposit(String userId, int money) throws InterruptedException {
        RLock rLock = redissonClient.getLock(userId);

        boolean isLock = rLock.tryLock(5, 3, TimeUnit.SECONDS);
        TransactionStatus status = txm.getTransaction(new DefaultTransactionDefinition());
        Account account = accountRepository.findByCustomerUserId(userId);

        try {
            if (!isLock) {
                log.info("========time out========");
                return account.getBalance();
            }
            int val = account.updateBalance(money);
            log.info("get lock success {}" , userId);
            txm.commit(status);
            return val;

        } catch (Exception e) {
            log.info("========Error========");
            txm.rollback(status);
        } finally {
            rLock.unlock();
        }
        log.info("========이체 실패 - 현재 잔액========");
        return account.getBalance();
    }

    @Transactional
    public int withdraw(String userId, int money) {
        Account account = accountRepository.findByCustomerUserId(userId);
        return account.updateBalance(money * -1);
    }

    // 입출금 포함 전체 예외 처리 필요
    @Transactional
    public int transfer(String userId, int money, String friendUserId) {
        Account account = accountRepository.findByCustomerUserId(userId);

        if (friendRepository.existsByCustomerUserIdAndFriendUserId(userId, friendUserId)) {
            try {
                int curBalance = withdraw(userId, money);
                int temp = lockDeposit(friendUserId, money);
                Transaction transaction = new Transaction(account, friendUserId, LocalDateTime.now(), money);
                transactionRepository.save(transaction);
                return curBalance;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("======이체 실패 - 현재 잔고======");
        return account.getBalance();
    }

    @Transactional
    public AccountDto findAllWithTransaction(String customerUserId) {
        Account account = accountRepository.findByCustomerUserIdWithTx(customerUserId);
        return new AccountDto(account);
    }
}
