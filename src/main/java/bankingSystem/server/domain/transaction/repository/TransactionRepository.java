package bankingSystem.server.domain.transaction.repository;

import bankingSystem.server.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.account.id = :accountId")
    List<Transaction> findByAccount(@Param("accountId") Long accountId);
}
