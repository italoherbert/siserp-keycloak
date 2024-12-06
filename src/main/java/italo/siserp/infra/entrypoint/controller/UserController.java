package italo.siserp.infra.entrypoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.siserp.core.domain.User;
import italo.siserp.core.ports.in.UserService;
import italo.siserp.infra.entrypoint.dto.user.CreateUserRequest;
import italo.siserp.infra.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Object> createUser( @RequestBody CreateUserRequest request ) {                
        User user = userMapper.map( request );
        userService.insert( user );
        return ResponseEntity.ok().build();
    }   

}
