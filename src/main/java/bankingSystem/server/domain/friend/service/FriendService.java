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

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void addFriend(String customerUserId, String friendUserId) {
        Customer customer = customerRepository.findById(customerUserId).get();
        Friend friend = new Friend(customer, friendUserId);
        friendRepository.save(friend);
    }

    @Transactional
    public void removeFriend(String customerUserId, String friendUserId) {
        Customer customer = customerRepository.findById(customerUserId).get();
        Friend friend = friendRepository.findByCustomerUserIdAndFriendUserId(customerUserId, friendUserId);
        friendRepository.delete(friend);
        customer.removeFriend(friend);
    }

    @Transactional
    public void removeAll(String customerUserId) {
        Customer customer = customerRepository.findById(customerUserId).get();
        friendRepository.removeAllByCustomerUserId(customer);
        customer.removeAllFriend();
    }

    public List<Friend> findAllByCustomerUserId(String customerUserId) {
        return friendRepository.findByCustomerUserId(customerUserId);
    }
}
