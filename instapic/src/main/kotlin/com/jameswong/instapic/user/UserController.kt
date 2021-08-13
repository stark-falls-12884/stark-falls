package com.jameswong.instapic.user

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {
    @PostMapping("/register")
    fun register(@RequestBody loginRequest: UserLoginRequest) {
        // TODO: Validation
        userService.register(loginRequest.username, loginRequest.password)
    }
}