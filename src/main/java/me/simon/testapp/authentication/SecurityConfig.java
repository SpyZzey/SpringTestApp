package me.simon.testapp.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * @author Simon Brebeck on 16/06/2023
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     * Returns the security filter chain used to configure the security of the application.
     * The security filter chain is used to configure the CORS policy, the CSRF policy,
     * the authorization policy and the authentication policy.
     * A Security Filter Chain is a chain of filters that are applied to an http request.
     * They limit access to the application by checking the request for certain properties.
     *
     * @param http the http security object used to configure the security filter chain
     * @return {@link SecurityFilterChain} the security filter chain
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CORS policy
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        // Allows all origins !! Testing only !! Change to specific origin in production !!
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        /*
        Disable CSRF policy (Cross-Site Request Forgery) !! Testing only !! Change to enabled in production !!
        Configures the CORS policy (Cross-Origin Resource Sharing) !! Testing only !! Change to specific origin in production !!
        Only allows authenticated users to access the application
        */
        http
                .csrf().disable()
                .cors().configurationSource(request -> corsConfiguration)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    /**
     * Returns the user details service used to create the user that is used to authenticate the user.
     * @param passwordEncoder the password encoder used to encode the password of the user
     * @return {@link InMemoryUserDetailsManager} the user details service
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

        // Custom users, should not be hardcoded in production
        UserDetails user = User.withUsername("default")
                .password(passwordEncoder.encode("default"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Returns the password encoder used to encode the password of the user created in the userDetailsService method.
     * @return {@link PasswordEncoder} the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
