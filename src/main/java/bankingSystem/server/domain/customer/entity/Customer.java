package bankingSystem.server.domain.customer.entity;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String sex;

    @NotNull
    private String userId;

    @NotNull
    private String passwd;
}
