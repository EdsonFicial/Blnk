package pavulla.firstapi.blnk.dto;

public class WithdrawDTO {
    private String account;
    private Double amount;
    

    public WithdrawDTO() {
        // Default constructor
    }

    public WithdrawDTO(String userId, Double amount) {
        this.account = userId; 
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String userId) {
        this.account = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
