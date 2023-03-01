package bankingSystem.server.global;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.friend.entity.Friend;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void init() {
            Customer customer1 = new Customer("t1", 123, "mail111", "남", "asd123", "123123");
            Customer customer2 = new Customer("t2", 123, "mail2222", "남", "fff123", "123123");
            Customer customer3 = new Customer("t1", 123, "mail3333", "남", "333123", "123123");
            Customer customer4 = new Customer("t2", 123, "mail4444", "남", "4444123", "123123");

            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);
            em.persist(customer4);

            Account account1 = new Account(customer1, 0);
            Account account2 = new Account(customer2, 5000);
            Account account3 = new Account(customer3, 3000);
            Account account4 = new Account(customer4, 2000);

            em.persist(account1);
            em.persist(account2);
            em.persist(account3);
            em.persist(account4);

            Friend friend1 = new Friend(customer1, customer2.getUserId());
            Friend friend2 = new Friend(customer1, customer3.getUserId());
            Friend friend3 = new Friend(customer1, customer4.getUserId());

            em.persist(friend1);
            em.persist(friend2);
            em.persist(friend3);

            Transaction transaction1 = new Transaction(account1, account2.getId(), LocalDateTime.now(), 5000);
            Transaction transaction2 = new Transaction(account1, account3.getId(), LocalDateTime.now(), 3000);
            Transaction transaction3 = new Transaction(account1, account4.getId(), LocalDateTime.now(), 2000);

            em.persist(transaction1);
            em.persist(transaction2);
            em.persist(transaction3);
        }
    }
}
