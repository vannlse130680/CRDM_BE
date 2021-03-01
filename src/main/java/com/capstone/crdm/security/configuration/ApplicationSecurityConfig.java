package com.capstone.crdm.security.configuration;

import com.capstone.crdm.filters.JwtExtracterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled=true, jsr250Enabled = true, securedEnabled = true)
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Value("${crdm.security.cors.allowed-headers:*}")
    private String allowedHeaders; // yes, a string

    @Value("${crdm.security.cors.allowed-origins:*}")
    private String allowedOrigins; // yes, a string

    private final String[] ignoringResources = new String[]{
            "/actuator**",
            "/actuator/**",
            "/swagger-ui**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "**/*.ico",
            "/favicon.ico",
            "**/*.css",
            "**/*.js",
            "**/*.png",
            "**/*.jpg",
            "**/*.jpeg",
            "**/*.svg",
    };

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.jwtParsingFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().antMatchers(ignoringResources).permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();

        http.cors().configurationSource(request -> this.initCORSConfig());
    }

    private JwtExtracterFilter jwtParsingFilter() {
        return this.beanFactory.createBean(JwtExtracterFilter.class);
    }

    private CorsConfiguration initCORSConfig() {
        var crossOriginResourceSharingConfig =  new CorsConfiguration();

        crossOriginResourceSharingConfig.addAllowedHeader(this.allowedHeaders);
        crossOriginResourceSharingConfig.addAllowedOrigin(this.allowedOrigins);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.OPTIONS);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.GET);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.POST);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.PUT);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.PATCH);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.DELETE);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.TRACE);
        crossOriginResourceSharingConfig.addAllowedMethod(HttpMethod.HEAD);

        return crossOriginResourceSharingConfig;
    }

}
