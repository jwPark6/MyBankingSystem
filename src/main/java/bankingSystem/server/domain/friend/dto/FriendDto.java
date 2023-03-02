package bankingSystem.server.domain.friend.dto;

import lombok.Getter;

@Getter
public class FriendDto {

    private String friendUserId;

    public FriendDto(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}
