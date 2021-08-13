package com.jameswong.instapic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
    // TODO: Block all other then /register
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable() // TODO: Remove
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}