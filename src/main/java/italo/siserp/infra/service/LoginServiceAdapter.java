package italo.siserp.infra.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import italo.siserp.core.domain.Login;
import italo.siserp.core.ports.out.LoginServicePort;

@Service
public class LoginServiceAdapter implements LoginServicePort {

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String keycloakTokenUri;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String keycloakClientId;

    @Override
    public String token(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add( "client_id", keycloakClientId );
        data.add( "grant_type", "password" );
        data.add( "username", username );
        data.add( "password", password );

        RestClient restClient = RestClient.create();

        ResponseEntity<Object> resp = restClient
            .post()
            .uri( keycloakTokenUri )
            .contentType( MediaType.APPLICATION_FORM_URLENCODED )
            .body( data ).retrieve().toEntity( Object.class );

        return String.valueOf( resp.getBody() );
    }
    
}
