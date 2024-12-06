package italo.siserp.core.ports.out;

import italo.siserp.core.domain.Login;

public interface LoginServicePort {
    
    String token( Login login );

}
