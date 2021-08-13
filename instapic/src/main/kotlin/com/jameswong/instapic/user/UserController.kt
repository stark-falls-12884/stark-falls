package com.jameswong.instapic.user

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: UserLoginRequest): String {
        println(loginRequest)
        return "hello world";
    }

    @PostMapping("/register")
    fun register(@RequestBody loginRequest: UserLoginRequest) {
        // TODO: Validation
        userService.register(loginRequest.username, loginRequest.password)
    }

    @PostMapping("/logout")
    fun logout() {

    }
}