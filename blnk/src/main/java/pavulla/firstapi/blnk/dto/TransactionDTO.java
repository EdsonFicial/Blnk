package pavulla.firstapi.blnk.dto;

import java.util.*;

public class TransactionDTO{
    private String id;
    private String tipo;
    private double valor;
    private Date data;
    private String user_id;

    public TransactionDTO(String id, String tipo, double valor, Date data, String user_id) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.user_id = user_id;
    }
    public TransactionDTO() {
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
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    
}