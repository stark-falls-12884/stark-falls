package com.jameswong.instapic.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PostRepository : JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    fun findAllByAuthor_Username(author_username: String, pageable: Pageable): Page<Post>
}