package com.jameswong.instapic.file

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LocalFileRepository : CrudRepository<FileEntity, UUID>