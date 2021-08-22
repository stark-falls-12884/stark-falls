package com.jameswong.instapic

import com.jameswong.instapic.user.User
import com.jameswong.instapic.user.UserRepository
import com.jameswong.instapic.user.UserService
import com.jameswong.instapic.user.UsernameInUseException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


class UserServiceTest {
    @Test
    fun registerShouldBeSuccessful() {
        val userRepository: UserRepository = mock();
        whenever(userRepository.findUserByUsername(anyString())).thenReturn(null)
        val userService = UserService(userRepository, BCryptPasswordEncoder());

        userService.register("test1234", "password")
    }

    @Test
    fun registerShouldFailOnDuplicateRegister() {
        val userRepository: UserRepository = mock();
        whenever(userRepository.findUserByUsername("test1234")).thenReturn(User("test1234", "password"))
        val userService = UserService(userRepository, BCryptPasswordEncoder());

        assertThrows<UsernameInUseException> {
            userService.register("test1234", "password")
        }
    }
}