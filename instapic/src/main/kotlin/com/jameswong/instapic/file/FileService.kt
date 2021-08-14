package com.jameswong.instapic.file

interface FileService {
    fun getFilePath(id: String): String
    fun deleteFile(id: String)
}