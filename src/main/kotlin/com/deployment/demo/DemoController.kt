package com.deployment.demo

import com.deployment.demo.database.DemoEntity
import com.deployment.demo.database.DemoRepository
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/demo")
class DemoController(val demoRepository: DemoRepository, val streamBridge: StreamBridge) {

    @GetMapping
    fun getTest(): List<String> = demoRepository.findAll().map { it.info }

    @PostMapping
    fun createTest(@RequestBody input: TestInput): UUID =
        demoRepository.save(DemoEntity(UUID.randomUUID(), input.data)).also { produceTestCreatedMessage(
            TestCreatedMessage(it.id, it.info)
        ) }.id

    fun produceTestCreatedMessage(testCreatedMessage: TestCreatedMessage) {
        streamBridge.send("demoProduceQueue", testCreatedMessage)
    }

    data class TestInput(val data: String)
    data class TestCreatedMessage(val id: UUID, val info: String)
}