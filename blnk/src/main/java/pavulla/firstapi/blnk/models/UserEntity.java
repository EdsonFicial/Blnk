package pavulla.firstapi.blnk.models;
import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_id")
    private String accountId;

     // Gera o ID automaticamente antes de persistir
    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = "usr-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public UserEntity() {
    }
    public UserEntity(String name, String email, String password, String accountId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountId = accountId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", accountId=" + accountId + "]";
    }
    
    
}
