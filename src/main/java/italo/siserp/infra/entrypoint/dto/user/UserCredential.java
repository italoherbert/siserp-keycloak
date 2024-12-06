package italo.siserp.infra.entrypoint.dto.user;

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
public class UserCredential {
    
    private String type;
    private String value;
    private boolean temporary;

}
