package italo.siserp.infra.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import italo.siserp.core.domain.User;
import italo.siserp.infra.config.OAuth2TestConfiguration;
import italo.siserp.infra.entrypoint.dto.user.CreateUser;
import italo.siserp.infra.entrypoint.dto.user.CreateUserRequest;
import italo.siserp.mock.UserMocks;

@SpringBootTest
@Import(OAuth2TestConfiguration.class)
public class UserMapperTests {
    
    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Deve mapear um CreateUserResponse para User")
    void shouldMapCreateUserResponseToUser() {
        CreateUserRequest request = UserMocks.newCreateUserRequest();

        User user = userMapper.map( request );

        assertThat( user.getFirstName() ).isEqualTo( request.getFirstName() );
        assertThat( user.getLastName() ).isEqualTo( request.getLastName() );
        assertThat( user.getEmail() ).isEqualTo( request.getEmail() );
        assertThat( user.getUsername() ).isEqualTo( request.getUsername() );
        assertThat( user.getPassword() ).isEqualTo( request.getPassword() );
    }

    @Test
    @DisplayName("Deve mapear um User para CreateUser")
    void shouldMapUserToCreateUser() {
        User user = UserMocks.newUser();

        CreateUser createUser = userMapper.map( user );

        assertThat( user.getFirstName() ).isEqualTo( createUser.getFirstName() );
        assertThat( user.getLastName() ).isEqualTo( createUser.getLastName() );
        assertThat( user.getEmail() ).isEqualTo( createUser.getEmail() );
        assertThat( user.getUsername() ).isEqualTo( createUser.getUsername() );
        assertThat( user.getPassword() ).isEqualTo( createUser.getCredentials().get( 0 ).getValue() );
    }

}
