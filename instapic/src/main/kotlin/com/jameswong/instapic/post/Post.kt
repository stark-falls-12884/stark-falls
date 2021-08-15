package com.jameswong.instapic.post

import com.jameswong.instapic.file.FileEntity
import com.jameswong.instapic.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Post(
    @ManyToOne var author: User,
    var body: String,
    var createdTime: LocalDateTime,
    @OneToOne var image: FileEntity,
    @Id @GeneratedValue var id: Long? = null
)