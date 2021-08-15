package com.jameswong.instapic.post

import org.springframework.beans.factory.annotation.Value
import java.time.LocalDateTime

interface PostView {
    val body: String

    val createdTime: LocalDateTime

    val id: Long

    @get:Value("#{target.author.username}")
    val authorName: String

    @get:Value("#{target.image.url}")
    val imageUrl: String

}