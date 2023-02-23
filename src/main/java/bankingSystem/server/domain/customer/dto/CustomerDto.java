package bankingSystem.server.domain.customer.dto;

import lombok.Getter;

@Getter
public class CustomerDto {

    private String name;

    private String email;

    private String userId;

    public CustomerDto(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }
}
