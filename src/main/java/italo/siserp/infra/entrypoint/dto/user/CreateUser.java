package italo.siserp.infra.entrypoint.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUser {
    
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private boolean enabled;

    private List<UserCredential> credentials;

}
