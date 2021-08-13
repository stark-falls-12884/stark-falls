package com.jameswong.instapic.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository


interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUsername(username: String): User?
}