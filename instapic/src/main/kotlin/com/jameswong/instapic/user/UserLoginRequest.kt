package com.jameswong.instapic.user

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserLoginRequest (@field:NotBlank val username: String, @field:Size(min=8) val password: String)