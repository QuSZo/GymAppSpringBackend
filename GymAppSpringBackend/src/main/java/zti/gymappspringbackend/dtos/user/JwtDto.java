package zti.gymappspringbackend.dtos.user;

import lombok.Data;

/**
 * DTO reprezentujące token JWT przekazywany klientowi po uwierzytelnieniu.
 */
@Data
public class JwtDto {
    private String accessToken;
}
