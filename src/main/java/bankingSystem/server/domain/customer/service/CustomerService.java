package bankingSystem.server.domain.customer.service;

import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    // Customer DTO 필요 -> 해당 정보만 전달하여 Customer 객체 생성 요구
    public void register(Customer customer) {
        if(!customerRepository.existsByEmail(customer.getEmail())
                || !customerRepository.existsByUserId(customer.getUserId())){
            // Customer customer = new Customer();
            customerRepository.save(customer);
        } else {
            log.info("중복 값이 존재");
        }
    }
}
