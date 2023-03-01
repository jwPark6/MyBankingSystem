package bankingSystem.server.domain.customer.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

    @Query("select c from Customer c" +
            " join fetch c.friends f" +
            " where c.userId = :userId")
    Customer findByUserIdWithFriends(@Param("userId") String userId);
}
