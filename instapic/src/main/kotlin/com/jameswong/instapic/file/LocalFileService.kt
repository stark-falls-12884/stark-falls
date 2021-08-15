package com.jameswong.instapic.file

import org.springframework.core.io.FileSystemResource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileOutputStream
import java.util.*
import kotlin.io.path.Path

@Service
class LocalFileService(
    val localFileRepository: LocalFileRepository,
    val localFileStorageProperties: LocalFileStorageProperties
) : FileService {
    override fun saveFile(file: MultipartFile): FileEntity {
        val localFileId = UUID.randomUUID()
        val outputPath = Path(localFileStorageProperties.uploadDirectory, localFileId.toString())
        val fileEntity = FileEntity(
            "/api/file/$localFileId",
            file.originalFilename ?: file.name,
            file.contentType ?: MediaType.APPLICATION_OCTET_STREAM_VALUE,
            localFileId
        )
        localFileRepository.save(
            fileEntity
        )

        val outputFile = FileOutputStream(outputPath.toString())
        outputFile.write(file.bytes)
        outputFile.close()
        return fileEntity
    }

    override fun getFilePath(id: String): String {
        TODO("Not yet implemented")
    }

    override fun deleteFile(id: String) {
        TODO("Not yet implemented")
    }

    fun getAsResource(id: String): LocalFileResource {
        val localFile = localFileRepository.findByIdOrNull(UUID.fromString(id)) ?: throw Exception()
        val tryFile = Path(localFileStorageProperties.uploadDirectory, localFile.id.toString())
        val fileSystemResource = FileSystemResource(tryFile)
        return LocalFileResource(localFile.filename, localFile.mimeType, fileSystemResource)
    }
}