package pavulla.firstapi.blnk.dto;

public class WithdrawDTO {
    private String userId;
    private String customername;
    private Double amount;
    

    public WithdrawDTO() {
        // Default constructor
    }

    public WithdrawDTO(String userId,String customername, Double amount) {
        this.userId = userId;
        this.customername = customername;   
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCustomername() {
        return customername;
    }
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
