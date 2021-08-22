package com.jameswong.instapic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class TestConfig {
    @Bean
    fun userDetailService(): UserDetailsService {
        return MockUserDetailsService()
    }
}