package italo.siserp.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import italo.siserp.core.ports.in.LoginService;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.LoginServicePort;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.core.serviceimpl.LoginServiceImpl;
import italo.siserp.core.serviceimpl.UserServiceImpl;

@Configuration
public class PortAdapterConfiguration {
    
    @Bean
    LoginService loginService( LoginServicePort port ) {
        return new LoginServiceImpl( port ); 
    }

    @Bean
    UserService userService( UserServicePort port ) {
        return new UserServiceImpl( port );
    }

}
