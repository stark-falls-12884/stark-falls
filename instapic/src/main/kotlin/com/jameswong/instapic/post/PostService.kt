package com.jameswong.instapic.post

import com.jameswong.instapic.image.ImageService
import com.jameswong.instapic.security.UserDetailsImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class PostService(private val postRepository: PostRepository, private val imageService: ImageService) {
    fun savePost(body: String, image: MultipartFile): Post {
        val currentUser = (SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl).getUser()
        val imageEntity = imageService.saveImage(image)
        return postRepository.save(Post(currentUser, body, LocalDateTime.now(), imageEntity))
    }

    fun getPosts(pageable: Pageable): Page<PostView> {
        return postRepository.findBy(pageable)
    }

    fun getPostsByAuthor(authorName: String, pageable: Pageable): Page<PostView> {
        return postRepository.findAllByAuthor_Username(authorName, pageable)
    }
}