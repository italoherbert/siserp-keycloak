package italo.siserp.core.serviceimpl.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import italo.siserp.core.domain.User;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.core.serviceimpl.UserServiceImpl;
import italo.siserp.mock.UserMocks;

public class GetUserTests {
    
    @Test
    @DisplayName("Deve retornar um usuário com sucesso pelo ID")
    void shouldReturnUserByIdWithSuccess() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( user ) );

        Optional<User> userFoundOp = userService.getById( user.getId() );
        
        assertThat( userFoundOp ).isPresent();
        assertThat( userFoundOp.get().getId() ).isEqualTo( user.getId() );
        assertThat( userFoundOp.get().getFirstName() ).isEqualTo( user.getFirstName() );
        assertThat( userFoundOp.get().getLastName() ).isEqualTo( user.getLastName() );
        assertThat( userFoundOp.get().getEmail() ).isEqualTo( user.getEmail() );
        assertThat( userFoundOp.get().getUsername() ).isEqualTo( user.getUsername() );
        assertThat( userFoundOp.get().getPassword() ).isEqualTo( user.getPassword() );
    }

}
