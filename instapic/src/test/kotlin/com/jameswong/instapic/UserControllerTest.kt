package com.jameswong.instapic

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jameswong.instapic.user.UserController
import com.jameswong.instapic.user.UserLoginRequest
import com.jameswong.instapic.user.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Unit test for validating controller behaviour
 */
@WebAppConfiguration
@Import(TestConfig::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService


    @Test
    fun registerShouldBeSuccessful() {
        Mockito.doNothing().`when`(userService).register(Mockito.anyString(), Mockito.anyString())

        mockMvc.perform(
            post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(UserLoginRequest("hello", "12345678")))
                .characterEncoding("UTF-8")
        ).andExpect(status().isOk)

    }

    @Test
    fun registerShouldFailOnEmptyField() {
        Mockito.doNothing().`when`(userService).register(Mockito.anyString(), Mockito.anyString())

        mockMvc.perform(
            post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(UserLoginRequest(username = "", password = "")))
                .characterEncoding("UTF-8")
        ).andExpect(status().is4xxClientError)
    }

    @Test
    fun registerShouldFailOnShortPassword() {
        Mockito.doNothing().`when`(userService).register(Mockito.anyString(), Mockito.anyString())

        mockMvc.perform(
            post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(UserLoginRequest("username", "1234")))
        ).andExpect(status().is4xxClientError)
    }

    @Test
    fun registerShouldFailOnBadInput() {
        Mockito.doNothing().`when`(userService).register(Mockito.anyString(), Mockito.anyString())

        mockMvc.perform(
            post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("not a valid input")
        ).andExpect(status().is4xxClientError)
    }
}