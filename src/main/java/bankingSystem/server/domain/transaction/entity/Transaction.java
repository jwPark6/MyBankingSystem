package bankingSystem.server.domain.transaction.entity;

import bankingSystem.server.domain.account.entity.Account;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    private Long foreignAccountId;

    @NotNull
    private LocalDateTime timeStamp;

    @NotNull
    private int amtWithdrawal;
}
