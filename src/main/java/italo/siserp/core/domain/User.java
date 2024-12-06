package italo.siserp.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import italo.siserp.core.validation.ValidationBuilder;
import italo.siserp.core.validation.Validator;
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
public class User implements Domain {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Override
    public void validate() {
        List<Validator> validators = new ArrayList<>();

        validators.addAll( 
            ValidationBuilder.of( "username", username )
                .required()
                .build() 
        );

        validators.addAll( 
            ValidationBuilder.of( "password", password )
                .required()
                .build() 
        );

        validators.addAll( 
            ValidationBuilder.of( "email", email )
                .required()
                .email()
                .build() 
        );

        validators.forEach( v -> v.validate() );
    }

}
