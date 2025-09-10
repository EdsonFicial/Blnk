package pavulla.firstapi.blnk.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "deposits")
public class DepositEntity {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime depositedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

     // Gera o ID automaticamente antes de persistir
    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = "dpt-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public DepositEntity() {
        // No-arg constructor
    }

    public DepositEntity(Double amount, LocalDateTime depositedAt, UserEntity user) {
        this.amount = amount;
        this.depositedAt = depositedAt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDepositedAt() {
        return depositedAt;
    }

    public void setDepositedAt(LocalDateTime depositedAt) {
        this.depositedAt = depositedAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}