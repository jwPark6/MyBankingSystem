package bankingSystem.server.domain.account.service;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.account.repository.AccountRepository;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public void register(Customer customer) {
        Account account = new Account(customer, 0);
        accountRepository.save(account);
    }

    @Transactional
    public int deposit(String userId, int money) {
        Customer customer = customerRepository.findByUserId(userId);
        Account account = accountRepository.findByCustomerId(customer.getId());
        return account.updateBalance(money);
    }

    @Transactional
    public int withdraw(String userId, int money) {
        Customer customer = customerRepository.findByUserId(userId);
        Account account = accountRepository.findByCustomerId(customer.getId());
        return account.updateBalance(money * -1);
    }
}
