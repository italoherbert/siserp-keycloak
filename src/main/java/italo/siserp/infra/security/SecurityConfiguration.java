package italo.siserp.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    private final String[] PUBLIC = {
        "/api/login/token", "/api/users"
    };

    @Bean
    SecurityFilterChain configure( HttpSecurity http ) throws Exception {
        http.authorizeHttpRequests( authHttpRequests -> 
                authHttpRequests
                    .requestMatchers( PUBLIC ).permitAll()
                    .anyRequest().authenticated() )
            .csrf( csrf-> csrf.disable())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2Client( Customizer.withDefaults() );
        return http.build();
    }

    /*
    @Bean
    GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return authorities -> {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            authorities.forEach( authority -> {
                if ( authority instanceof OidcUserAuthority oidcAuthority) {
                    grantedAuthorities.addAll( mapAuthorities( oidcAuthority.getIdToken().getClaims() ) );
                } else if ( authority instanceof OAuth2UserAuthority oauth2Auth ) {
                    grantedAuthorities.addAll( mapAuthorities( oauth2Auth.getAttributes() ) );
                }
            } );            
            return grantedAuthorities;
        };
    }

    private List<SimpleGrantedAuthority> mapAuthorities( final Map<String, Object> attributes ) {
        final Map<String, Object> realmAccess = (Map<String, Object>)attributes.getOrDefault( "realm_access", Collections.emptyMap() );
        final Collection<String> roles = (Collection<String>)realmAccess.getOrDefault( "roles", Collections.emptyList() );
        return roles.stream()
                .map( role -> new SimpleGrantedAuthority( role ) )
                .toList();
    }
    */

}
