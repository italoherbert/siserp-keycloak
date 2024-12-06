package italo.siserp.core.serviceimpl.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import italo.siserp.core.domain.User;
import italo.siserp.core.exception.BusinessException;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.core.serviceimpl.UserServiceImpl;
import italo.siserp.mock.UserMocks;

public class DeleteUserTests {
    
    @Test
    @DisplayName("Deve deletar o usuário com sucesso.")
    void shouldDeleteUserWithSuccess() {
        UUID id = UUID.randomUUID();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsById( id ) ).thenReturn( true );
        doNothing().when( userServicePort ).delete( id );
        
        Throwable t = catchThrowable( () -> userService.delete( id ) ); 
        assertThat( t ).isNull();
    }

    @Test
    @DisplayName("Deve lançar exceção de usuário não encontrado.")
    void shouldThrowsExceptionOfUserNotFound() {
        UUID id = UUID.randomUUID();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsById( id ) ).thenReturn( false );
        doNothing().when( userServicePort ).delete( id );
        
        Throwable t = catchThrowable( () -> userService.delete( id ) ); 

        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Usuário não encontrado." ); 
    }

    @Test
    @DisplayName("Deve lançar exceção de usuário não removido.")
    void shouldThrowsExceptionOfUserNotRemoved() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsById( user.getId() ) ).thenReturn( true );
        when( userServicePort.save( user ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.delete( user.getId() ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Não foi possível remover o usuário." );
    }

}
