package bankingSystem.server.domain.customer.entity;

import bankingSystem.server.domain.friend.entity.Friend;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "customer")
    private List<Friend> friends = new ArrayList<>();

    public Customer(String name, int phoneNumber, String email, String sex, String userId, String passwd) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
        this.userId = userId;
        this.passwd = passwd;
    }
}
