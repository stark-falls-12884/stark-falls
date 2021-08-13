package com.jameswong.instapic.post

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.Size

@RestController()
@RequestMapping("/api/post")
class PostController(val postService: PostService) {
    @GetMapping()
    fun hello(): String {
        return "hello world";
    }


    @PostMapping("")
    fun newPost(
        @Size(min = 0, max = 5000) @RequestParam body: String,
        @RequestParam image: MultipartFile
    ) {
        postService.savePost(body, image);
    }
}