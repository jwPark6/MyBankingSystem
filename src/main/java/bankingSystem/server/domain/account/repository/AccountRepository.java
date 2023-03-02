package bankingSystem.server.domain.account.repository;

import bankingSystem.server.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByCustomerUserId(String customerUserId);

    @Query("select a from Account a" +
            " join fetch a.transactions t" +
            " join fetch a.customer c" +
            " where c.userId = :customerUserId")
    Account findByCustomerUserIdWithTx(@Param("customerUserId") String customerUserId);
}
