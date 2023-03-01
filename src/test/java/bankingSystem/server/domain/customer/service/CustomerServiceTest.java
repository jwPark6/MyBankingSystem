package bankingSystem.server.domain.customer.service;

import bankingSystem.server.domain.customer.dto.CustomerDto;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class CustomerServiceTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Test
    public void register() {
        Customer customer1 = new Customer("t1", 123, "ttmail", "남", "ttasd123", "123123");
        Customer customer2 = new Customer("t1", 123, "ttmail", "남", "ttasd1234", "123123");

        customerService.register(customer1);
        Customer findMember1 = customerRepository.findById(customer1.getUserId()).get();
        customerService.register(customer2);
        Optional<Customer> findMember2 = customerRepository.findById("ttasd1234");
        assertThat(findMember1.getUserId()).isEqualTo(customer1.getUserId());
        assertThat(findMember2).isEqualTo(Optional.empty());
    }

    @Test
    public void find() {
        Customer customer1 = new Customer("t1", 123, "ttmail", "남", "ttasd123", "123123");
        Customer customer2 = new Customer("t12", 123, "ttmail2", "남", "ttasd123", "123123");

        customerService.register(customer1);
        customerService.register(customer2);

        List<CustomerDto> customers = customerService.findCustomers();

        CustomerDto one = customerService.findOne("ttasd123");

        assertThat(one.getUserId()).isEqualTo(customer1.getUserId());
        // assertThat(customers.get(0).getUserId()).isEqualTo(customer1.getUserId());
    }
}