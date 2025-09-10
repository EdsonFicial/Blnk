package pavulla.firstapi.blnk.models;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String user_id;

    @jakarta.persistence.Column(nullable = false)
    private String userName;

    
    public TransactionEntity(String id, String tipo, double valor, LocalDateTime data, String user_id, String userName) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.user_id = user_id;
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
        return user_id;
    }
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
