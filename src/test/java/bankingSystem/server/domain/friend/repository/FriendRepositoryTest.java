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
        Customer customer1 = customerRepository.findById("asd123").get();
        Customer customer2 = customerRepository.findById("fff123").get();

        List<Friend> all = friendRepository.findAll();
        for (Friend f : all) {
            log.info("friend = {}", f);
        }

        Friend f1 = friendRepository.findByCustomerUserId(customer1.getUserId()).get(0);
        assertThat(f1.getCustomer().getUserId()).isEqualTo(customer1.getUserId());
        assertThat(f1.getFriendUserId()).isEqualTo(customer2.getUserId());

        Friend f2 = friendRepository.findByCustomerUserIdAndFriendUserId(customer1.getUserId(), customer2.getUserId());
        assertThat(f2).isEqualTo(f1);
    }

    @Test
    @Transactional
    public void 친구삭제() {
        Customer customer1 = customerRepository.findById("asd123").get();
        Customer customer2 = customerRepository.findById("fff123").get();

        Friend friend1 = friendRepository.findByCustomerUserIdAndFriendUserId(customer1.getUserId(), customer2.getUserId());

        friendRepository.delete(friend1);

        List<Friend> fs = friendRepository.findByCustomerUserId(customer1.getUserId());

        for (Friend f : fs) {
            log.info("friendId = {}", f.getFriendUserId());
        }

        Friend removedF = friendRepository.findByCustomerUserIdAndFriendUserId(customer1.getUserId(), customer2.getUserId());
        assertThat(removedF).isEqualTo(null);

        friendRepository.removeAllByCustomerUserId(customer1);

        fs = friendRepository.findByCustomerUserId(customer1.getUserId());
        assertThat(fs.size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void cascade삭제테스트() {
        Customer customer1 = customerRepository.findById("asd123").get();

        customerRepository.delete(customer1);

        List<Friend> friends = friendRepository.findAll();

        for (Friend f : friends) {
            log.info("friend = {}", f.getFriendUserId());
        }

        assertThat(friends.size()).isEqualTo(0);
    }

    @Test
    public void 친구존재확인() {
        boolean b1 = friendRepository.existsByCustomerUserIdAndFriendUserId("asd123", "fff123");
        assertThat(b1).isTrue();

        boolean b2 = friendRepository.existsByCustomerUserIdAndFriendUserId("asd123", "????");
        assertThat(b2).isFalse();
    }
}