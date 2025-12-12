package pavulla.firstapi.blnk.models;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "userid", nullable = false)
    private String userid;
    @Column(name = "balance")
    private double balance;

    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = "acc-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public AccountEntity(){
    }

    public AccountEntity(String userid, double balance) {
        this.userid = userid;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
