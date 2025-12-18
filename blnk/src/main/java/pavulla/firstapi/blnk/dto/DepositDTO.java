package pavulla.firstapi.blnk.dto;

import java.time.LocalDateTime;



public class DepositDTO {
    
    private String account;
    private double amount;

    public DepositDTO() {
    }

    public DepositDTO(String account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDepositedAt() {
        return LocalDateTime.now();
    }
}
