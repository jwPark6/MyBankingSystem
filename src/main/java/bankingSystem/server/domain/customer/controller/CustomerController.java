package bankingSystem.server.domain.customer.controller;

import bankingSystem.server.domain.account.service.AccountService;
import bankingSystem.server.domain.customer.dto.CustomerDto;
import bankingSystem.server.domain.customer.dto.CustomerWithFriendDto;
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
    private final AccountService accountService;

    @GetMapping("/customers")
    public List<CustomerDto> customers() {
        return customerService.findCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerDto customer(@PathVariable("id") String id) {
        return customerService.findOne(id);
    }

    //DTO 변환 필요
    @GetMapping("/customer/{id}/friends")
    public CustomerWithFriendDto customerWithFriends(@PathVariable("id") String id) {
        return customerService.findOneWithFriends(id);
    }

    @PostMapping("/customer")
    public String register(@RequestBody Customer customer) {
        customerService.register(customer);
        accountService.register(customer);
        return customer.getUserId();
    }

}
