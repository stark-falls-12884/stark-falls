package com.jameswong.instapic.file

import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class LocalFileController(val localFileService: LocalFileService) {
    @GetMapping("/api/file/{id}")
    fun serveFile(@PathVariable id: String): ResponseEntity<Resource> {
        val localFileResource = localFileService.getAsResource(id)
        return ResponseEntity.ok()
            .header("Content-Disposition", "filename=\"${localFileResource.fileName}\"")
            .header("Content-Type", localFileResource.contentType)
            .body(localFileResource.resource)
    }
}