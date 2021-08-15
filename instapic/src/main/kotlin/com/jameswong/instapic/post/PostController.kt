package com.jameswong.instapic.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {

    @GetMapping
    fun getPosts(pageable: Pageable?): Page<Post> {
        return postService.getPosts(pageable ?: Pageable.unpaged())
    }

    @GetMapping("/{authorName}")
    fun getPostsByAuthor(pageable: Pageable?, @PathVariable authorName: String): Page<Post> {
        return postService.getPostsByAuthor(authorName, pageable ?: Pageable.unpaged())
    }

    @PostMapping("", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun newPost(
        @Size(min = 0, max = 5000) @RequestPart body: String,
        @RequestPart image: MultipartFile
    ) {
        postService.savePost(body, image)
    }
}