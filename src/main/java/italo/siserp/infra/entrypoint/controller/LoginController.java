package italo.siserp.infra.entrypoint.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import italo.siserp.infra.entrypoint.dto.LoginRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    
    @PostMapping("/token")
    public ResponseEntity<Object> token( @RequestBody LoginRequest request ) {
        String username = request.getUsername();
        String password = request.getPassword();

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add( "client_id", "siserp-client" );
        data.add( "grant_type", "password" );
        data.add( "username", username );
        data.add( "password", password );

        RestClient restClient = RestClient.create();

        ResponseEntity<Object> resp = restClient
            .post()
            .uri( "http://localhost:8081/realms/siserp/protocol/openid-connect/token" )
            .contentType( MediaType.APPLICATION_FORM_URLENCODED )
            .body( data ).retrieve().toEntity( Object.class );

        return ResponseEntity.ok( resp.getBody() );
    }    
    
}
