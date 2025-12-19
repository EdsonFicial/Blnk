package pavulla.firstapi.blnk.models;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity(name = "transactions")
public class TransactionEntity {
    @Id
    @jakarta.persistence.Column(nullable = false, unique = true)
    private String id;

    @jakarta.persistence.Column(nullable = false)
    private String tipo;

    @jakarta.persistence.Column(nullable = false)
    private double valor;

    @jakarta.persistence.Column(nullable = false)
    private LocalDateTime data;

    @jakarta.persistence.Column(nullable = false)
    private String userId;

    @jakarta.persistence.Column(nullable = false)
    private String userName;


     // Gera o ID automaticamente antes de persistir
    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = "tx-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    
    public TransactionEntity(String id, String tipo, double valor, LocalDateTime data, String userId, String userName) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.userId = userId;
        this.userName = userName;
    }
    public TransactionEntity() {
        // Default constructor
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
