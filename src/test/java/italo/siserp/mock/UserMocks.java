package italo.siserp.mock;

import java.util.UUID;

import com.github.javafaker.Faker;

import italo.siserp.core.domain.User;
import italo.siserp.infra.entrypoint.dto.user.CreateUserRequest;

public class UserMocks {
    
    private final static Faker faker = new Faker();
    private final static String strongPassword = "&7456jgfsjjhJHHJhh87)_.;";

    public static User newUser() {
        return User.builder()
            .id( UUID.randomUUID() ) 
            .firstName( faker.name().firstName() )
            .lastName( faker.name().lastName() )
            .username( faker.name().username() )
            .email( faker.internet().emailAddress() )
            .password( strongPassword ) 
            .build();
    }

    public static CreateUserRequest newCreateUserRequest() {
        return CreateUserRequest.builder()
            .firstName( faker.name().firstName() )
            .lastName( faker.name().lastName() )
            .username( faker.name().username() )
            .email( faker.internet().emailAddress() )
            .password( strongPassword )             
            .build();
    }

}
