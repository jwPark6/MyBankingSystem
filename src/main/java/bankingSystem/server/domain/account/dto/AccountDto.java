package bankingSystem.server.domain.account.dto;

import bankingSystem.server.domain.account.entity.Account;
import bankingSystem.server.domain.transaction.entity.Transaction;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountDto {

    private String customerUserId;
    private String customerName;
    private String customerEmail;

    private List<Transaction> transactions;

    private int balance;

    public AccountDto(String customerUserId, String customerName, String customerEmail, List<Transaction> transactions, int balance) {
        this.customerUserId = customerUserId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.transactions = transactions;
        this.balance = balance;
    }

    public AccountDto(Account account) {
        this.customerUserId = account.getCustomer().getUserId();
        this.customerName = account.getCustomer().getName();
        this.customerEmail = account.getCustomer().getEmail();

        this.transactions = account.getTransactions();
        this.balance = account.getBalance();
    }
}
