package patronobserver;

import java.util.Observable;

public class BankAccount extends Observable {

    private double balance;

    public void addAmount(Double amount) {
        this.balance += amount;
        setChanged();
        notifyObservers(balance);
    }
    
    public void withdrawAmount(Double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            setChanged();
            notifyObservers(balance);
        } else {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
    }

    public Double getBalance() {
        return balance;
    }
}
