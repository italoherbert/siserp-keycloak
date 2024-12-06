package italo.siserp.core.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import italo.siserp.core.exception.DomainException;
import italo.siserp.mock.LoginMocks;

public class LoginTests {
    
    @Test
    @DisplayName("Deve validar com sucesso.")
    void shouldValidateWithSuccess() {
        Login login = LoginMocks.newLogin();

        Throwable t = catchThrowable( login::validate );

        assertThat( t ).isNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção de username requerido.")
    void shouldThrowsExceptionOfUsernameRequired( String username ) {
        Login login = LoginMocks.newLogin();
        login.setUsername( username );

        Throwable t = catchThrowable( login::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O campo 'username' é de preenchimento obrigatório." );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção de password requerido.")
    void shouldThrowsExceptionOfPasswordRequired( String password ) {
        Login login = LoginMocks.newLogin();
        login.setPassword( password );

        Throwable t = catchThrowable( login::validate );

        assertThat( t ).isInstanceOf( DomainException.class );
        assertThat( t.getMessage() ).isEqualTo( "O campo 'password' é de preenchimento obrigatório." );
    }

}
