package com.mydomain.OrderDetails.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/ oauth / token", "/ oauth / authorize **", "/OrderUpdate/vendors", "/OrderUpdate/orders").permitAll();
        // .anyRequest (). authenticated ();
        http.requestMatchers().antMatchers("/OrderUpdate/updateOrders") // Deny access to "/ private"
                .and().authorizeRequests()
                .antMatchers("/OrderUpdate/updateOrders").access("hasRole ('USER')")
                //.and().requestMatchers().antMatchers("/ admin") // Deny access to "/ admin"
                //.antMatchers("/ admin").access("hasRole ('ADMIN')")
                .and().authorizeRequests();
    }
}
