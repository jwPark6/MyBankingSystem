package bankingSystem.server.domain.friend.repository;

import bankingSystem.server.domain.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Friend findByCustomerUserId(String customerUserId);

    Friend findByCustomerUserIdAndFriendUserId(String customerUserId, String friendUserId);
}
