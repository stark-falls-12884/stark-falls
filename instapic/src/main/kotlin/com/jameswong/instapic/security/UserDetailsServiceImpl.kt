package com.jameswong.instapic.security

import com.jameswong.instapic.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findUserByUsername(username)?.let { UserDetailsImpl(it) }
            ?: throw UsernameNotFoundException(username)
    }
}

