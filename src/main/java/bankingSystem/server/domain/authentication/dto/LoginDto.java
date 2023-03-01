package bankingSystem.server.domain.authentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {

    private String userId;

    private String passwd;

    public LoginDto(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }
}
