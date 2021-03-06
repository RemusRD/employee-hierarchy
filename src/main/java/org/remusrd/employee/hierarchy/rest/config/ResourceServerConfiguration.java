package org.remusrd.employee.hierarchy.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    private static final String PERSONIO_ROLE = "PERSONIO";

    protected ResourceServerConfiguration(final AuthenticationManagerBuilder auth,
                                          @Value("${hierarchy.internal.auth.username}") final String internalUser,
                                          @Value("${hierarchy.internal.auth.password}") final String internalPassword) throws Exception {
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
