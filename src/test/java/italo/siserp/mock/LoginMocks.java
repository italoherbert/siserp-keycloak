package italo.siserp.mock;

import com.github.javafaker.Faker;

import italo.siserp.core.domain.Login;

public class LoginMocks {

    private final static Faker faker = new Faker();
    private final static String strongPassword = "&7456jgfsjjhJHHJhh87)_.;";

    public static Login newLogin() {
        return Login.builder()
            .username( faker.name().username() )
            .password( strongPassword )
            .build();
    }
    
}
