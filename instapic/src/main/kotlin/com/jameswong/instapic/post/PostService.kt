package com.jameswong.instapic.post

import com.jameswong.instapic.image.ImageService
import com.jameswong.instapic.security.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class PostService(val postRepository: PostRepository, val imageService: ImageService) {
    fun savePost(body: String, image: MultipartFile): Post {
        val currentUser = (SecurityContextHolder.getContext().authentication.details as UserDetailsImpl).getUser()
        val imageId = imageService.saveImage(image)
        return postRepository.save(Post(currentUser, body, LocalDateTime.now(), imageId))
    }
}