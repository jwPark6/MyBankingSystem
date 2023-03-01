package bankingSystem.server.domain.friend.entity;

import bankingSystem.server.domain.customer.entity.Customer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    private Customer customer;

    @NotNull
    private String friendUserId;

    public Friend(Customer customer, String friendUserId) {
        this.customer = customer;
        this.friendUserId = friendUserId;
        customer.getFriends().add(this);
    }
}
