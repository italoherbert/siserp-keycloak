package italo.siserp.infra.mapper;

import java.util.Collections;

import org.springframework.stereotype.Component;

import italo.siserp.core.domain.User;
import italo.siserp.infra.entrypoint.dto.user.CreateUser;
import italo.siserp.infra.entrypoint.dto.user.CreateUserRequest;
import italo.siserp.infra.entrypoint.dto.user.UserCredential;

@Component
public class UserMapper {

    public User map( CreateUserRequest request ) {
        return User.builder()
            .firstName( request.getFirstName() )
            .lastName( request.getLastName() )
            .email( request.getEmail() )
            .username( request.getUsername() )
            .password( request.getPassword() )
            .build();
    }
    
    public User map( CreateUser user ) {
        return User.builder()
            .firstName( user.getFirstName() )
            .lastName( user.getLastName() )
            .email( user.getEmail() )
            .username( user.getUsername() )
            .password( user.getCredentials().get( 0 ).getValue() )
            .build();
    }

    public CreateUser map( User user ) {
        return CreateUser.builder()
            .firstName( user.getFirstName() )
            .lastName( user.getLastName() )
            .email( user.getEmail() )
            .username( user.getUsername() )
            .credentials( Collections.singletonList( 
                UserCredential.builder()
                    .type( "password" ) 
                    .value( user.getPassword() )
                    .temporary( false )
                    .build() ))
            .build();
    }

}
