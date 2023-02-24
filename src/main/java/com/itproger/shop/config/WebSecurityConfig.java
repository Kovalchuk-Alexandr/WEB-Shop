package com.itproger.shop.config;

import com.itproger.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import javax.sql.DataSource; // Старый вариант

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

// Старый вариант
//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());

// Старый вариант
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
//                .authoritiesByUsernameQuery("SELECT users.username, user_role.roles FROM users " +
//                        "INNER JOIN user_role ON users.id = user_role.user_id WHERE users.username = ?");

        // Get AuthenticationManager
        AuthenticationManager authenticationManager = auth.build();

        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

        http            //.csrf().disable() отключаем токен
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/item/add", "/item/*/update", "/item/*/delete", "/user").authenticated()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .authenticationManager(authenticationManager)
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/user")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain FilterChain(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
//                .authoritiesByUsernameQuery("SELECT users.username, user_role.roles FROM users \" +\n" +
//                        "\"INNER JOIN user_role ON users.id = user_role.user_id WHERE users.username = ?");
//        return (SecurityFilterChain) auth.build();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
