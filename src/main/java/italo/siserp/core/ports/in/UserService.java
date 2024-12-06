package italo.siserp.core.ports.in;

import java.util.Optional;
import java.util.UUID;

import italo.siserp.core.domain.User;

public interface UserService {
    
    void insert( User user );

    void update( UUID userId, User user );

    void delete( UUID userId );

    Optional<User> getById( UUID userId );

}
