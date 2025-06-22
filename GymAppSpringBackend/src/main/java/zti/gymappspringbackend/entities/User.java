package zti.gymappspringbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Encja reprezentująca użytkownika w systemie.
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "_user")
public class User {

    @Id @GeneratedValue
    private UUID id;
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
