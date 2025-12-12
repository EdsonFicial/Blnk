package pavulla.firstapi.blnk.dto;

public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String account;
    private double balance;


    public UserResponseDTO() {
    }

    public UserResponseDTO(String id, String name, String email, String account, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.account = account;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserResponseDTO [id=" + id + ", name=" + name + ", email=" + email + "]";
    }

    
    
}
