package bankingSystem.server.domain.friend.controller;

import bankingSystem.server.domain.customer.service.CustomerService;
import bankingSystem.server.domain.friend.entity.Friend;
import bankingSystem.server.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final CustomerService customerService;

    @GetMapping("/friends")
    public List<Friend> findAllByCustomerUserId (@RequestParam("userId") String customerUserId) {
        return friendService.findAllByCustomerUserId(customerUserId);
    }

    @PostMapping("/friend")
    public void addFriend(@RequestParam("userId") String customerUserId,
                          @RequestParam("friendUserId") String friendUserId) {
        friendService.addFriend(customerUserId, friendUserId);
    }

    @DeleteMapping("/friend")
    public void removeFriend (@RequestParam("userId") String customerUserId,
                              @RequestParam("friendUserId") String friendUserId) {
        friendService.removeFriend(customerUserId, friendUserId);
    }

    @DeleteMapping("/friends")
    public void removeAll(@RequestParam("userId") String customerUserId) {
        friendService.removeAll(customerUserId);
    }
}
