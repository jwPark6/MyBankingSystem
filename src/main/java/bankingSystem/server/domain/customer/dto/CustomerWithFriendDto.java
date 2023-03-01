package bankingSystem.server.domain.customer.dto;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.friend.dto.FriendDto;
import bankingSystem.server.domain.friend.entity.Friend;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomerWithFriendDto {

    private String name;
    private String email;
    private String userId;

    private List<FriendDto> friends;

    public CustomerWithFriendDto(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.userId = customer.getUserId();
        List<Friend> fs = customer.getFriends();
        this.friends = fs.stream()
                .map(o -> new FriendDto(o.getFriendUserId()))
                .collect(Collectors.toList());
    }
}
