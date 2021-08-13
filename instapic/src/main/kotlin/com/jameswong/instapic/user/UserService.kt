package com.jameswong.instapic.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {
    fun register(username: String, password: String) {
        val existingUser = userRepository.findUserByUsername(username);
        if (existingUser != null) {
            throw Exception("Username in use") // TODO: handling
        }
        userRepository.save(User(username, passwordEncoder.encode(password)))
    }
}