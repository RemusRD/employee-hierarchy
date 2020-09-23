package org.remusrd.employee.hierarchy.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    private static final String PERSONIO_ROLE = "PERSONIO";

    //FIXME: remove default values and use an application-test instead
    protected ResourceServerConfiguration(final AuthenticationManagerBuilder auth,
                                          @Value("${hierarchy.internal.auth.username:test}") final String internalUser,
                                          @Value("${hierarchy.internal.auth.password:test}") final String internalPassword) throws Exception {
        auth.inMemoryAuthentication().withUser(internalUser).password("{noop}" + internalPassword).roles(PERSONIO_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .hasRole(PERSONIO_ROLE)
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}
