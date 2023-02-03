package com.example.bookapplication.security;

import com.example.bookapplication.entity.User;
import com.example.bookapplication.security.jwt.JWTCoder;
import com.example.bookapplication.security.userDetails.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

import static com.example.bookapplication.enums.Role.ADMIN;
import static com.example.bookapplication.enums.Role.USER;

@Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {

        private final String[] WHITE_LISTED_URLS ={"/", "/home",
                "index", "/css/*", "/js/*", "/v2/api-docs/**", "/v3/api-docs/**","/configuration/**",
                "/swagger*/**","/swagger-ui/**","/webjars/**", "/swagger-ui.html", "/api/v1/user/**", "/api/v1/auth/login"
        };

        private final AppUserDetailsService appUserDetailsService;

        private static final String AUTHORITY_PREFIX = "ROLE_";

        private static final String CLAIM_ROLES = "roles";

        private final PasswordEncoder passwordEncoder;

        private final JWTCoder jwtCoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.cors().configurationSource(new CorsConfigurationSource() {


                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;

                    }


                }).and().csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((auth) -> {
                    auth.antMatchers(WHITE_LISTED_URLS).permitAll()
                            .antMatchers("/api/v1/admin/**", "/api/v1/user/admin/**").hasAnyRole(ADMIN.name())
                            .antMatchers( "/api/v1/auth/update-password", "/api/v1/auth").hasAnyRole(USER.name())
                            .anyRequest().authenticated();
                })
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt ->
                                        jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter()))
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .httpBasic().disable()
                .build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(getJwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    private Converter<Jwt, Collection<GrantedAuthority>> getJwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix(AUTHORITY_PREFIX);
        converter.setAuthoritiesClaimName(CLAIM_ROLES);
        return converter;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    JwtAuthenticationProvider jwtTokenAuthProvider() {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtCoder.jwtDecoder());
        provider.setJwtAuthenticationConverter(getJwtAuthenticationConverter());
        return provider;
    }
}
