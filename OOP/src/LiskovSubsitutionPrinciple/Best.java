package LiskovSubsitutionPrinciple;

import java.math.BigDecimal;

class Account {
    protected BigDecimal balance;

    Account(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) < 0) {
            balance = balance.subtract(amount);
        } else {
            // 잔고가 부족할 경우 처리
        }
    }
}

class SavingsAccount extends Account {
    private final BigDecimal interestRate;

    SavingsAccount(BigDecimal balance, BigDecimal interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        balance = balance.multiply(interestRate);
    }
}