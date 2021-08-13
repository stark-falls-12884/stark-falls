package com.jameswong.instapic.image

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService {
    fun saveImage(image: MultipartFile): String {
        return "dummy.png"
    }
}