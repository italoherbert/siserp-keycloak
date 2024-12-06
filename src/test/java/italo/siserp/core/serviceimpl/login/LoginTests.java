package italo.siserp.core.serviceimpl.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import italo.siserp.core.domain.Login;
import italo.siserp.core.ports.in.LoginService;
import italo.siserp.core.ports.out.LoginServicePort;
import italo.siserp.core.serviceimpl.LoginServiceImpl;
import italo.siserp.mock.LoginMocks;

public class LoginTests {
    
    @Test
    @DisplayName("Deve executar login com sucesso.")
    void shouldMakeLoginWithSuccess() {
        Login login = LoginMocks.newLogin();
        String token = "sahlhhjlkas98d879799q27983";

        LoginServicePort loginServicePort = mock( LoginServicePort.class );
        LoginService loginService = new LoginServiceImpl( loginServicePort );
        
        when( loginServicePort.token( login ) ).thenReturn( token );

        String tok = loginService.token( login );

        assertThat( tok ).isEqualTo( token );
    }

}
