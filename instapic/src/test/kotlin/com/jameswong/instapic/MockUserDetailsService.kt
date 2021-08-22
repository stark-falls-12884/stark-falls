package com.jameswong.instapic

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class MockUserDetailsService: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        throw UsernameNotFoundException(username)
    }
}