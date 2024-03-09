package com.tt.configsecurity;

import com.tt.entity.Account;
import com.tt.service.AccountService;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    private AccountService accountService;

    public ConfigSecurity(AccountService accountService) {
        this.accountService = accountService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails admin =  User
//                .withUsername("trungtt")
//                .password(passwordEncoder.encode("123"))
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().requestMatchers("/account/add").permitAll();
        httpSecurity.authorizeRequests().requestMatchers(HttpMethod.POST, "/account/save").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.formLogin();
        return httpSecurity.build();
    }

}
