package italo.siserp.core.serviceimpl.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import italo.siserp.core.domain.User;
import italo.siserp.core.exception.BusinessException;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.core.serviceimpl.UserServiceImpl;
import italo.siserp.mock.UserMocks;

public class UpdateUserTests {
    
    @Test
    @DisplayName("Deve atualizar usuário com sucesso.")
    void shouldUpdateUserWithSuccess() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( user ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) ); 
        
        assertThat( t ).isNull();
    }

    @Test
    @DisplayName("Deve atualizar usuário igual ao cadastrado com sucesso.")
    void shouldUpdateUserEqualsWithSuccess() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( user ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( true );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( true );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) ); 
        
        assertThat( t ).isNull();
    }

    @Test
    @DisplayName("Deve lançar exceção de usuário não atualizado.")
    void shouldThrowsExceptionOfUserNotUpdated() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( user ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );
        when( userServicePort.save( user ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Não foi possível atualizar o usuário." );
    }

    void shouldThrowsExceptionOfUserNotFound() {
        User user = UserMocks.newUser();

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( user ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) );

        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Usuário não encontrado." );
    }


    @Test
    @DisplayName("Deve tratar exceção de username já existe.")
    void shoudThrowsExceptionOfUsernameExists() {
        User user = UserMocks.newUser();
        User registeredUser = UserMocks.newUser();
        registeredUser.setEmail( user.getEmail() ); 

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( registeredUser ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( true );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( false );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Já existe um usuário cadastrado com o username informado." );
    }

    @Test
    @DisplayName("Deve tratar exceção de email já existe.")
    void shoudThrowsExceptionOfEmailExists() {
        User user = UserMocks.newUser();
        User registeredUser = UserMocks.newUser();
        registeredUser.setUsername( user.getUsername() ); 

        UserServicePort userServicePort = mock( UserServicePort.class );
        UserService userService = new UserServiceImpl( userServicePort );

        when( userServicePort.findById( user.getId() ) ).thenReturn( Optional.of( registeredUser ) );
        when( userServicePort.existsByUsername( user.getUsername() ) ).thenReturn( false );
        when( userServicePort.existsByEmail( user.getEmail() ) ).thenReturn( true );

        Throwable t = catchThrowable( () -> userService.update( user.getId(), user ) ); 
        
        assertThat( t ).isInstanceOf( BusinessException.class );
        assertThat( t.getMessage() ).isEqualTo( "Já existe um usuário cadastrado com o email informado." );
    }

}
