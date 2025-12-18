package pavulla.firstapi.blnk.dto;

public class CheckBalanceDTO {
    private String account;
    

    public CheckBalanceDTO(){

    }
    public CheckBalanceDTO(String account, String userName, double balance) {

    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
}
