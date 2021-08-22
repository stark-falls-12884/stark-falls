package com.jameswong.instapic.user

class UsernameInUseException(val username: String): Exception("Username: $username already in use") {}