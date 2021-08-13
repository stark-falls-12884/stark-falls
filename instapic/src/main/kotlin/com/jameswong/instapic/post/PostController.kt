package com.jameswong.instapic.post

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/post")
class PostController {
    @GetMapping()
    fun hello(): String {
        return "hello world";
    }
}