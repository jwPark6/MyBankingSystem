package bankingSystem.server.domain.account.entity;

import bankingSystem.server.domain.customer.entity.Customer;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int balance;
}
