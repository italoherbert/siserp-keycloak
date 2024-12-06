package italo.siserp.core.serviceimpl;

import italo.siserp.core.domain.Login;
import italo.siserp.core.ports.in.LoginService;
import italo.siserp.core.ports.out.LoginServicePort;

public class LoginServiceImpl implements LoginService {

    private final LoginServicePort loginServicePort;

    public LoginServiceImpl( LoginServicePort loginServicePort ) {
        this.loginServicePort = loginServicePort;
    }

    @Override
    public String token(Login login) {
        login.validate();

        return loginServicePort.token( login );
    }
    
}
