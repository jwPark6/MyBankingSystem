package bankingSystem.server.domain.customer.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
}
