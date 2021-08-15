package com.jameswong.instapic.file

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class LocalFile(
    var filename: String,
    var mimeType: String,
    @Id var id: UUID
)