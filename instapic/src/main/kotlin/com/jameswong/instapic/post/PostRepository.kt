package com.jameswong.instapic.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

// TODO: Optimize query e.g. (n+1, overfetch)
interface PostRepository : JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    fun findAllByAuthor_Username(author_username: String, pageable: Pageable): Page<PostView>

    // JPA hack, cannot use findAll here because PostView is not subclass for Post
    fun findBy(pageable: Pageable): Page<PostView>
}