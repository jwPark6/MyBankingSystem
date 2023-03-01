package bankingSystem.server.domain.authentication.controller;

import bankingSystem.server.domain.authentication.dto.LoginDto;
import bankingSystem.server.domain.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // 추후 시큐리티 학습 후 토큰 및 세션 리팩토링 필요
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }
}
