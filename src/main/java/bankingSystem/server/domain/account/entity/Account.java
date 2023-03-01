package bankingSystem.server.domain.account.entity;

import bankingSystem.server.domain.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Customer customer;

    private int balance;

    public Account(Customer customer, int balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public int updateBalance(int money) {
        this.balance += money;
        return balance;
    }
}
