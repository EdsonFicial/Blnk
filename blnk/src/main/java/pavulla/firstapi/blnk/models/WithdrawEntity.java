package pavulla.firstapi.blnk.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



@Entity
@Table(name = "withdraws")
public class WithdrawEntity {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    
     // Gera o ID automaticamente antes de persistir
    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = "with-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public WithdrawEntity() {
        // No-arg constructor required by JPA
    }

    public WithdrawEntity(String userId, Double amount, LocalDateTime timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}