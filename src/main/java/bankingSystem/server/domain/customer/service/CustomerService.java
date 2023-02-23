package bankingSystem.server.domain.customer.service;

import bankingSystem.server.domain.customer.dto.CustomerDto;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public void register(Customer customer) {
        if (!customerRepository.existsByEmail(customer.getEmail())
                || !customerRepository.existsByUserId(customer.getUserId())) {
                        customerRepository.save(customer);
        } else {
            log.info("중복 값이 존재");
        }
    }

    public List<CustomerDto> findCustomers() {
        List<Customer> all = customerRepository.findAll();

        return all.stream()
                .map(o -> new CustomerDto(o.getName(), o.getEmail(), o.getUserId()))
                .collect(Collectors.toList());
    }

    public CustomerDto findOne(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return new CustomerDto(customer.getName(), customer.getEmail(), customer.getUserId());
    }
}
