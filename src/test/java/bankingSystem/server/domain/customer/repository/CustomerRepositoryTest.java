package bankingSystem.server.domain.customer.repository;

import bankingSystem.server.domain.customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void 중복처리() {
        Customer customer1 = new Customer("t1", 123, "mail", "남", "asd123", "123123");
        Customer customer2 = new Customer("t1", 123, "mail", "남", "asd123", "123123");

        customerRepository.save(customer1);
        assertThat(customerRepository.existsByEmail(customer2.getEmail())).isTrue();
        assertThat(customerRepository.existsByUserId(customer2.getUserId())).isTrue();
    }

}