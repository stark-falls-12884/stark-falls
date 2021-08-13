package com.jameswong.instapic.post;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PostRepository : JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>