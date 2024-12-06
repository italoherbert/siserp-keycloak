package italo.siserp.core.serviceimpl.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import italo.siserp.core.domain.User;
import italo.siserp.core.exception.BusinessException;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.core.serviceimpl.UserServiceImpl;
import italo.siserp.mock.UserMocks;

public class InsertUserTests {
    
    @Test
    @DisplayName("Deve inserir usuário com sucesso.")
    void shouldInsertWithSuccess() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.insert( user ) ); 
        
        assertThat( t ).isNull();
    }

    @Test
    @DisplayName("Deve lançar exceção de usuário não criado.")
    void shouldThrowsExceptionOfUserNotCreated() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );
        when( userServicePort.save( user ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.insert( user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Não foi possível criar o usuário." );
    }

    @Test
    @DisplayName("Deve tratar exceção de username já existe.")
    void shoudThrowsExceptionOfUsernameExists() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( true );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.insert( user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Já existe um usuário cadastrado com o username informado." );
    }

    @Test
    @DisplayName("Deve tratar exceção de email já existe.")
    void shoudThrowsExceptionOfEmailExists() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( true );

        Throwable t = catchThrowable( () -> userService.insert( user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Já existe um usuário cadastrado com o email informado." );
    }

}
