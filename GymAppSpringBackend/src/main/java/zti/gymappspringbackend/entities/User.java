package zti.gymappspringbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "_user")
public class User {

    @Id @GeneratedValue
    private UUID id;
    @Getter
    private String email;
    @Getter
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
