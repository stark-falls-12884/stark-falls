package com.jameswong.instapic.user

import com.jameswong.instapic.security.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody loginRequest: UserLoginRequest) {
        userService.register(loginRequest.username, loginRequest.password)
    }

    @PostMapping("/current-user")
    fun getCurrentUser(): String? {
        return SecurityContextHolder.getContext().authentication?.let {
            (it.principal as UserDetailsImpl).getUser().username
        }
    }
}