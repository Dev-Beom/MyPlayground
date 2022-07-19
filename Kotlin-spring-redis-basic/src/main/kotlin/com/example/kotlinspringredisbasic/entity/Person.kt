package com.example.kotlinspringredisbasic.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime
import java.util.UUID

@RedisHash(value = "people", timeToLive = 30)
data class Person(
    @Id // this is redis key. Setting this value to null sets the random value.
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val age: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
)