package bankingSystem.server.domain.transaction.entity;

import bankingSystem.server.domain.account.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    private String foreignUserId;

    @NotNull
    private LocalDateTime timeStamp;

    @NotNull
    private int amtWithdrawal;

    public Transaction(Account account, String foreignUserId, LocalDateTime timeStamp, int amtWithdrawal) {
        this.account = account;
        this.foreignUserId = foreignUserId;
        this.timeStamp = timeStamp;
        this.amtWithdrawal = amtWithdrawal;
        account.getTransactions().add(this);
    }
}
