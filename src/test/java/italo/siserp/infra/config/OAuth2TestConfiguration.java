package italo.siserp.infra.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@TestConfiguration
public class OAuth2TestConfiguration {
    
    @Bean
    OAuth2AuthorizedClientManager oauth2AuthorizedClientManager() {
        return mock( OAuth2AuthorizedClientManager.class );
    }

}
