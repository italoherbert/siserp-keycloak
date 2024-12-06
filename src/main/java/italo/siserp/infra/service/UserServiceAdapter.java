package italo.siserp.infra.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import italo.siserp.core.domain.User;
import italo.siserp.core.ports.out.UserServicePort;
import italo.siserp.infra.entrypoint.dto.user.CreateUser;
import italo.siserp.infra.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort {
    
    private final OAuth2AuthorizedClientManager oauth2AuthorizedClientManager;
    private final UserMapper userMapper;

    private final String keycloakRegistrationId = "keycloak";

    @Value("${keycloak.users.endpoint}")
    private String keycloakUsersEndpoint;
    
    @Override
    public boolean save(User user) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        if ( auth != null ) {
            OAuth2AuthorizeRequest oauth2Req = OAuth2AuthorizeRequest
                        .withClientRegistrationId( keycloakRegistrationId )
                        .principal( auth.getName() )
                        .build();
        
            OAuth2AuthorizedClient authClient = oauth2AuthorizedClientManager.authorize( oauth2Req );            
            if ( authClient != null ) {
                OAuth2AccessToken accessToken = authClient.getAccessToken();
                String token = accessToken.getTokenValue();
                
                CreateUser userDTO = userMapper.map( user );                

                String data = "{}";
                try {
                    data = new ObjectMapper().writeValueAsString( userDTO );
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                RestClient restClient = RestClient.create();                

                restClient
                    .post()
                    .uri( keycloakUsersEndpoint )
                    .contentType( MediaType.APPLICATION_JSON )
                    .header( "Authorization", "Bearer "+token )
                    .body( data )
                    .retrieve()
                    .toBodilessEntity();      
                
                return true;
            }         
        }        
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.of( User.builder().build() );
    }

    @Override
    public boolean delete(UUID userId) {
        return false;
    }

}
