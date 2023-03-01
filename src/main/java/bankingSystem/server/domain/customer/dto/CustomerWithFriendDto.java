package bankingSystem.server.domain.customer.dto;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.friend.entity.Friend;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerWithFriendDto {

    private String name;
    private String email;
    private String userId;

    private List<Friend> friends;

    public CustomerWithFriendDto(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.userId = customer.getUserId();
        this.friends = customer.getFriends();
    }
}
