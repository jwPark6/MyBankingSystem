package bankingSystem.server.domain.friend.entity;

import bankingSystem.server.domain.customer.entity.Customer;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    private Long friendId;
}
