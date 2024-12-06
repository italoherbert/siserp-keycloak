package italo.siserp.core.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import italo.siserp.core.domain.User;
import italo.siserp.core.exception.BusinessException;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.core.ports.out.UserServicePort;

public class UserServiceImpl implements UserService {
    
    private final UserServicePort userServicePort;
    
    public UserServiceImpl( UserServicePort userServicePort ) {
        this.userServicePort = userServicePort;
    }

    @Override
    public void insert(User user) {
        user.validate();

        if ( userServicePort.existsByUsername( user.getUsername() ) )
            throw new BusinessException( BusinessException.USERNAME_JA_EXISTE );
        if ( userServicePort.existsByEmail( user.getEmail() ) )
            throw new BusinessException( BusinessException.EMAIL_JA_EXISTE );

        boolean created = userServicePort.save( user ); 
        if ( !created )
            throw new BusinessException( BusinessException.USER_NOT_CREATED );
    }

    @Override
    public void update(UUID userId, User user) {
        user.validate();

        String username = user.getUsername();
        String email = user.getEmail();

        Optional<User> registeredUserOp = userServicePort.findById( userId );
        if ( !registeredUserOp.isPresent() )
            throw new BusinessException( BusinessException.USER_NOT_FOUND );

        User registeredUser = registeredUserOp.get();
        String regUsername = registeredUser.getUsername();
        String regEmail = registeredUser.getEmail();

        if ( !username.equals( regUsername ) )
            if ( userServicePort.existsByUsername( username ) )
                throw new BusinessException( BusinessException.USERNAME_JA_EXISTE );
        if ( !email.equals( regEmail ) )
            if ( userServicePort.existsByEmail( email ) )
                throw new BusinessException( BusinessException.EMAIL_JA_EXISTE );

        registeredUser.setFirstName( user.getFirstName() );
        registeredUser.setLastName( user.getLastName() );
        registeredUser.setUsername( username );
        registeredUser.setEmail( email );

        boolean updated = userServicePort.save( registeredUser ); 
        if ( !updated )
            throw new BusinessException( BusinessException.USER_NOT_UPDATED );
    }

    @Override
    public void delete(UUID userId) {
        if ( !userServicePort.existsById( userId ) )
            throw new BusinessException( BusinessException.USER_NOT_FOUND );

        boolean deleted = userServicePort.delete( userId ); 
        if ( !deleted )
            throw new BusinessException( BusinessException.USER_NOT_REMOVED );
    }

    @Override
    public Optional<User> getById(UUID userId) {
        return userServicePort.findById( userId );
    }

}
