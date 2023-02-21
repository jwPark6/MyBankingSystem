package bankingSystem.server.domain.customer.service;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        customerService.register(customer2);
    }
}