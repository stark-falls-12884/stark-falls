package com.jameswong.instapic.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {

    @GetMapping
    fun getPosts(pageable: Pageable?): Page<PostView> {
        return postService.getPosts(pageable ?: Pageable.unpaged())
    }

    @GetMapping("/{authorName}")
    fun getPostsByAuthor(pageable: Pageable?, @PathVariable authorName: String): Page<PostView> {
        return postService.getPostsByAuthor(authorName, pageable ?: Pageable.unpaged())
    }

    @PostMapping("", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun newPost(
        @Size(min = 0, max = 5000) @RequestPart body: String,
        @RequestPart image: MultipartFile
    ): PostView {
        return postService.savePost(body, image).let { PostViewImpl(it.body, it.createdTime, it.id!!, it.author.username, it.image.filename) }
    }
}

data class PostViewImpl(
    override val body: String,
    override val createdTime: LocalDateTime,
    override val id: Long,
    override val authorName: String,
    override val imageUrl: String
) : PostView