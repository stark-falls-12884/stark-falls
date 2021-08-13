package com.jameswong.instapic.post

import com.jameswong.instapic.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Post(
    @ManyToOne var author: User,
    var body: String,
    var createdTime: LocalDateTime,
    var imageId: String,
    @Id @GeneratedValue var id: Long? = null
)