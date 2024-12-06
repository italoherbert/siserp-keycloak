package italo.siserp.core.ports.out;

import java.util.Optional;
import java.util.UUID;

import italo.siserp.core.domain.User;

public interface UserServicePort {
    
    boolean save( User user );

    boolean existsByUsername( String username );

    boolean existsByEmail( String email );

    boolean existsById( UUID id );

    Optional<User> findById( UUID id );

    boolean delete( UUID userId );

}
