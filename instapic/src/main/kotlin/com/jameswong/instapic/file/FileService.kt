package com.jameswong.instapic.file

import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun saveFile(file: MultipartFile): FileEntity
    fun getFilePath(id: String): String
    fun deleteFile(id: String)
}