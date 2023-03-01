package bankingSystem.server.domain.friend.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByCustomerUserId(String customerUserId);

    Friend findByCustomerUserIdAndFriendUserId(String customerUserId, String friendUserId);

    @Modifying
    @Query("delete from Friend f where f.customer = :customer")
    void removeAllByCustomerUserId(@Param("customer") Customer customer);
}
