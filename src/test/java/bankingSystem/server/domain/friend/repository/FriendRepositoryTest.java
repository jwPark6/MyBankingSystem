package bankingSystem.server.domain.friend.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import bankingSystem.server.domain.friend.entity.Friend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class FriendRepositoryTest {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    public void 친구등록_및_조회(){
        Customer customer1 = new Customer("t1", 123, "mail111", "남", "asd123", "123123");
        Customer customer2 = new Customer("t2", 123, "mail2222", "남", "fff123", "123123");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Friend friend = new Friend(customer1, customer2.getUserId());
        friendRepository.save(friend);

        List<Friend> all = friendRepository.findAll();
        for (Friend f : all) {
            log.info("friend = ", f);
        }

        Friend f1 = friendRepository.findByCustomerUserId(customer1.getUserId());
        assertThat(f1.getCustomer().getUserId()).isEqualTo(customer1.getUserId());
        assertThat(f1.getFriendUserId()).isEqualTo(customer2.getUserId());

        Friend f2 = friendRepository.findByCustomerUserIdAndFriendUserId(customer1.getUserId(), customer2.getUserId());
        assertThat(f2).isEqualTo(f1);
    }
}