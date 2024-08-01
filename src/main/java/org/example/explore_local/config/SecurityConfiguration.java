package org.example.explore_local.config;


import org.example.explore_local.service.oauth.OAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final String rememberMeKey;
    public SecurityConfiguration(
            @Value("${explore_local.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, OAuthSuccessHandler oAuthSuccessHandler) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                 authorizeRequests -> authorizeRequests
                  .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                  .requestMatchers("/", "/users/login", "/users/register", "/about","/contact","/users/login-error")
                  .permitAll().anyRequest().authenticated())


                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/users/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/", true)
                                        .failureUrl("/users/login-error"))
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true) //dobavi zatvarqshta skoba !
//                .rememberMe(
//                        rememberMe ->
//                                rememberMe
//                                        .key(rememberMeKey)
//                                        .rememberMeParameter("rememberme")
//                                        .rememberMeCookieName("rememberme")

//                .oauth2Login(
//                        oauth -> oauth.successHandler(oAuthSuccessHandler)
                ).build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new AppUserDetailService(userRepository);
//    }
}
