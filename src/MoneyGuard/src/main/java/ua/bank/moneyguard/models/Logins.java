package ua.bank.moneyguard.models;


import jakarta.persistence.*;

@Entity
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long login_id;

    @Column(nullable = false, length = 30)
private String user_password;


}
