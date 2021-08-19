package com.jameswong.instapic.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/api/user/register").permitAll()
            .antMatchers("/api/user/login").permitAll()
            .antMatchers("/h2-console/**").permitAll() // TODO: Remove
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutUrl("/api/user/logout")
            .invalidateHttpSession(true)
            .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
            .and()
            .csrf().disable()
            .headers().frameOptions().disable() // TODO: Remove

        http.addFilterAt(
            customAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailsService)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

    @Bean
    @Throws(Exception::class)
    fun customAuthenticationFilter(): LoginRequestHandler {
        val filter = LoginRequestHandler()
        filter.setAuthenticationSuccessHandler { _, response, authentication ->
            val username = (authentication.principal as (UserDetailsImpl)).username
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            val out = response.writer
            out.write(ObjectMapper().writeValueAsString(username))
            out.flush()
            out.close()
        }
        filter.setAuthenticationFailureHandler { _, response, exception ->
            val out = response.writer
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            out.write(ObjectMapper().writeValueAsString(exception))
            out.flush()
            out.close()
        }
        filter.setFilterProcessesUrl("/api/user/login")
        filter.setAuthenticationManager(authenticationManagerBean())
        return filter
    }
}