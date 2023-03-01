package bankingSystem.server.domain.customer.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

}
