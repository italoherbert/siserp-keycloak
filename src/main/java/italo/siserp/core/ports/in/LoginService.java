package italo.siserp.core.ports.in;

import italo.siserp.core.domain.Login;

public interface LoginService {
    
    String token( Login login );

}
