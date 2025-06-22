package zti.gymappspringbackend.dtos.user;

import lombok.Data;

/**
 * DTO służące do tworzenia rejestracji użytkownika.
 */
@Data
public class RegisterDto {
    private String email;
    private String password;
}
