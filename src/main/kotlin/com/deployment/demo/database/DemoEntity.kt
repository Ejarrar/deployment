package com.deployment.demo.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "demo")
data class DemoEntity(@Id val id: UUID, val info: String)