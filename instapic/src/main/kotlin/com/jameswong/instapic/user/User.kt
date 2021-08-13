package com.jameswong.instapic.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User (var username: String, var password: String, @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null)