package italo.siserp.core.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import italo.siserp.core.exception.DomainException;
import italo.siserp.mock.UserMocks;

public class UserTests {
    
    @Test
    @DisplayName( "Deve validar um usuário válido com sucesso.")
    void shouldValidateWithSuccess() {
        User user = UserMocks.newUser();
        
        Throwable t = catchThrowable( user::validate );

        assertThat( t ).isNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção sobre username em branco.")
    void shouldThrowsExceptionOfUsernameRequired( String username ) {
        User user = UserMocks.newUser();
        user.setUsername( username );

        Throwable t = catchThrowable( user::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O campo 'username' é de preenchimento obrigatório." );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção sobre senha em branco.")
    void shouldThrowsExceptionOfPasswordRequired( String password ) {
        User user = UserMocks.newUser();
        user.setPassword( password );

        Throwable t = catchThrowable( user::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O campo 'password' é de preenchimento obrigatório." );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção sobre email em branco.")
    void shouldThrowsExceptionOfEmailRequired( String email ) {
        User user = UserMocks.newUser();
        user.setEmail( email );

        Throwable t = catchThrowable( user::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O campo 'email' é de preenchimento obrigatório." );
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "abc@abc", "abc@abc.abc.abc", "abc@.abc"})
    @DisplayName( "Deve lançar exceção sobre email inválido.")
    void shouldThrowsExceptionOfInvalidEmail( String email ) {
        User user = UserMocks.newUser();
        user.setEmail( email );

        Throwable t = catchThrowable( user::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O email informado está em formato inválido." );
    }

}
