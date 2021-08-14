package com.jameswong.instapic.file

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class LocalFileService(val localFileStorageProperties: LocalFileStorageProperties): FileService {
    override fun saveFile(id: MultipartFile): String {
        println(localFileStorageProperties)
        TODO("Not yet implemented")
    }

    override fun getFilePath(id: String): String {
        TODO("Not yet implemented")
    }

    override fun deleteFile(id: String) {
        TODO("Not yet implemented")
    }
}