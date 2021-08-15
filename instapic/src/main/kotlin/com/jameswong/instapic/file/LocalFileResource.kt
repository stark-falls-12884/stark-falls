package com.jameswong.instapic.file

import org.springframework.core.io.Resource

data class LocalFileResource(val fileName: String, val contentType: String, val resource: Resource)