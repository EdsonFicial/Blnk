package pavulla.firstapi.blnk.dto;


import pavulla.firstapi.blnk.models.UserEntity;
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private double balance;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
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
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserResponseDTO [id=" + id + ", name=" + name + ", email=" + email + ", balance=" + balance + "]";
    }

    
    
}
