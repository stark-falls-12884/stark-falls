package com.jameswong.instapic.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jameswong.instapic.user.UserLoginRequest
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginRequestHandler : UsernamePasswordAuthenticationFilter() {
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        return if (request.contentType != null && isJsonContentType(request.contentType)) {
            val authRequest: UsernamePasswordAuthenticationToken = try {
                jacksonObjectMapper().readValue<UserLoginRequest>(request.inputStream)
                    .run { UsernamePasswordAuthenticationToken(username, password) }
            } catch (e: IOException) {
                logger.error("JSON parse error, ${e.message}")
                UsernamePasswordAuthenticationToken("", "")
            }
            setDetails(request, authRequest)
            this.authenticationManager.authenticate(authRequest)

        } else {
            super.attemptAuthentication(request, response)
        }
    }

    private fun isJsonContentType(contentType: String) = MediaType.APPLICATION_JSON_VALUE === contentType
}