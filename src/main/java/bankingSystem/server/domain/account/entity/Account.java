package bankingSystem.server.domain.account.entity;

import bankingSystem.server.domain.customer.entity.Customer;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int balance;

    public Account(Customer customer, int balance) {
        this.customer = customer;
        this.balance = balance;
    }
}
