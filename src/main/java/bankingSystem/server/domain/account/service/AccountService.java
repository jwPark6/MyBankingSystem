package bankingSystem.server.domain.account.service;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import bankingSystem.server.domain.friend.repository.FriendRepository;
import bankingSystem.server.domain.transaction.entity.Transaction;
import bankingSystem.server.domain.transaction.repository.TransactionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final FriendRepository friendRepository;

    private final TransactionRepository transactionRepository;

    public void register(Customer customer) {
        Account account = new Account(customer, 0);
        accountRepository.save(account);
    }

    @Transactional
    public int deposit(String userId, int money) {
        Customer customer = customerRepository.findById(userId).get();
        Account account = accountRepository.findByCustomerUserId(userId);
        return account.updateBalance(money);
    }

    @Transactional
    public int withdraw(String userId, int money) {
        Customer customer = customerRepository.findById(userId).get();
        Account account = accountRepository.findByCustomerUserId(userId);
        return account.updateBalance(money * -1);
    }

    @Transactional
    public int transfer(String userId, int money, String friendUserId) {
        Account account = accountRepository.findByCustomerUserId(userId);

        if (friendRepository.existsByCustomerUserIdAndFriendUserId(userId, friendUserId)) {
            withdraw(userId, money);
            deposit(friendUserId, money);

            Account fAccount = accountRepository.findByCustomerUserId(friendUserId);
            Transaction transaction = new Transaction(account, fAccount.getId(), LocalDateTime.now(), money);
            transactionRepository.save(transaction);
        }

        return account.getBalance();
    }

    @Transactional
    public Account findAllWithTransaction(String customerUserId) {
        return accountRepository.findByCustomerUserIdWithTx(customerUserId);
    }
}
