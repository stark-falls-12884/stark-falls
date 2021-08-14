package com.jameswong.instapic.file

import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController()
class LocalFileController(val localFileService: LocalFileService) {
    @GetMapping("/api/file/{id}")
    fun serveFile(@PathVariable id: String): ResponseEntity<Resource> {
        println("Hello")
        return ResponseEntity.ok().body(localFileService.getAsResource(id))
    }
}