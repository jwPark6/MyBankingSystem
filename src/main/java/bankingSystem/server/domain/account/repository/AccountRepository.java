package bankingSystem.server.domain.account.repository;

import bankingSystem.server.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
