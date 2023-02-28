package bankingSystem.server.domain.friend.service;

import bankingSystem.server.domain.customer.dto.CustomerDto;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import bankingSystem.server.domain.friend.entity.Friend;
import bankingSystem.server.domain.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void addFriend(String customerId, String friendUserId) {
        Customer customer = customerRepository.findByUserId(customerId);
        Friend friend = new Friend(customer, friendUserId);
        friendRepository.save(friend);
    }

    public void removeFriend(String customerId, String friendUserId) {

    }
}
