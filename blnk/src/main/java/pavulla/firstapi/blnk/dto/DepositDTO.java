package pavulla.firstapi.blnk.dto;

import java.time.LocalDateTime;

public class DepositDTO {
    private String userId;
    private double amount;

    public DepositDTO() {
    }

    public DepositDTO(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
