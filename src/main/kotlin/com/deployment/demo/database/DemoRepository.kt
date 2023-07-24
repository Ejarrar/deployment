package com.deployment.demo.database

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface DemoRepository: CrudRepository<DemoEntity, UUID>