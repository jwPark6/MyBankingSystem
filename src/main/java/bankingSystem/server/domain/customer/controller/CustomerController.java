package bankingSystem.server.domain.customer.controller;

import bankingSystem.server.domain.customer.dto.CustomerDto;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> customers() {
        return customerService.findCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerDto customer(@PathVariable("id") Long id) {
        return customerService.findOne(id);
    }

    @PostMapping("/customer")
    public Long register(@RequestBody Customer customer) {
        customerService.register(customer);
        return customer.getId();
    }

}
