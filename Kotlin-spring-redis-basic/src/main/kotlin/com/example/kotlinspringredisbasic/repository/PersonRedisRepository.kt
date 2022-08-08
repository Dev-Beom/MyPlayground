package com.example.kotlinspringredisbasic.repository

import com.example.kotlinspringredisbasic.entity.Person
import org.springframework.data.repository.CrudRepository

interface PersonRedisRepository: CrudRepository<Person, String>