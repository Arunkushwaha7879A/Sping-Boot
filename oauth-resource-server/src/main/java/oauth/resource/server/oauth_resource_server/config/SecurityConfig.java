package oauth.resource.server.oauth_resource_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private Converter customConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorizeRequest->
                        authorizeRequest.requestMatchers(HttpMethod.GET , "/api/v1/users").permitAll()
                                .anyRequest().authenticated());

        //this is resource server: configure jwt ki configuration mujhe chahiyee
        http.oauth2ResourceServer(server->
                server.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(customConverter);
        return jwtAuthenticationConverter;
    }

}
