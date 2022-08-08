package com.example.kotlinspringredisbasic

import com.example.kotlinspringredisbasic.entity.Person
import com.example.kotlinspringredisbasic.repository.PersonRedisRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RedisRepositoryTest(
    @Autowired
    private val repository: PersonRedisRepository
) {
    @Test
    fun redisRepositoryTest() {
        val person = Person(name = "benn.dev", age = 27)
        repository.save(person)
        repository.findById(person.id)
        repository.count()
//        repository.delete(person)
    }
}