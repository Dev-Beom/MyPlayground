package com.example.webfluxetc

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class Controller {
    @GetMapping("/")
    fun hello(): Mono<Map<String, Any>> {
        val currentThread = Thread.currentThread()
        val activeCount = Thread.activeCount()
        val allStackTraces = Thread.getAllStackTraces().keys.map { it.toMap() }
        val map = mapOf(
            "application" to mapOf(
                "activeCount" to activeCount,
                "allStackTraces" to allStackTraces
            ),
            "currentThread" to currentThread.toMap()
        )
        return Mono.just(map)
    }

    fun Thread.toMap(): Map<String, Any> = mapOf(
        "threadName" to this.name,
        "priority" to this.priority,
        "group" to this.threadGroup
    )
}