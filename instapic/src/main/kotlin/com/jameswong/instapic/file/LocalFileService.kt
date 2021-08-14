package com.jameswong.instapic.file

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileOutputStream
import kotlin.io.path.Path

@Service
class LocalFileService(val localFileStorageProperties: LocalFileStorageProperties): FileService {
    override fun saveFile(file: MultipartFile): String {
        val outputPath = Path(localFileStorageProperties.uploadDirectory, file.originalFilename!!)
        val outputFile = FileOutputStream(outputPath.toString())
        outputFile.write(file.bytes)
        outputFile.close()
        return ""
    }

    override fun getFilePath(id: String): String {
        TODO("Not yet implemented")
    }

    override fun deleteFile(id: String) {
        TODO("Not yet implemented")
    }

    fun getAsResource(id: String): Resource {
        val tryFile = Path(localFileStorageProperties.uploadDirectory, id)
        return FileSystemResource(tryFile)
    }
}