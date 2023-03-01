package bankingSystem.server.domain.authentication.service;

import bankingSystem.server.domain.authentication.dto.LoginDto;
import bankingSystem.server.domain.customer.entity.Customer;
import bankingSystem.server.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository customerRepository;

    // 추후 Security 학습 이후 세션 리팩토링 예정
    public String login(LoginDto loginDto) {
        Customer customer = customerRepository.findById(loginDto.getUserId()).get();
        if(customer.getPasswd().equals(loginDto.getPasswd())) return "success";
        return "fail";
    }
}
