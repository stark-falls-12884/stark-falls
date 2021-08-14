package com.jameswong.instapic.image

import com.jameswong.instapic.file.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(val fileService: FileService) {
    fun saveImage(image: MultipartFile): String {
        return fileService.saveFile(image)
    }

    fun getImageUrl(id: String): String {
        return fileService.getFilePath(id)
    }

    fun delete(id: String) {
        fileService.deleteFile(id)
    }
}