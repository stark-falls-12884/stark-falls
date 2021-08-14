package com.jameswong.instapic.file

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "local-file-service")
data class LocalFileStorageProperties(val uploadDirectory: String)
