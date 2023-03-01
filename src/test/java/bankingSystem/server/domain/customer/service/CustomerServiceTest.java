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
    @Rollback(value = false)
    public void register() {
        Customer customer1 = new Customer("t1", 123, "mail", "남", "asd123", "123123");
        Customer customer2 = new Customer("t1", 123, "mail", "남", "asd123", "123123");

        customerService.register(customer1);
        Optional<Customer> findMember1 = customerRepository.findById(customer1.getId());
        customerService.register(customer2);
        Optional<Customer> findMember2 = customerRepository.findById(2L);
        assertThat(findMember1.get().getId()).isEqualTo(customer1.getId());
        assertThat(findMember2).isEqualTo(Optional.empty());
    }

    @Test
    public void find() {
        Customer customer1 = new Customer("t1", 123, "mail", "남", "asd123", "123123");
        Customer customer2 = new Customer("t12", 123, "mail2", "남", "asd123", "123123");

        customerService.register(customer1);
        customerService.register(customer2);

        List<CustomerDto> customers = customerService.findCustomers();

        CustomerDto one = customerService.findOne(1L);

        assertThat(one.getUserId()).isEqualTo(customer1.getUserId());
        assertThat(customers.get(0).getUserId()).isEqualTo(customer1.getUserId());
    }
}