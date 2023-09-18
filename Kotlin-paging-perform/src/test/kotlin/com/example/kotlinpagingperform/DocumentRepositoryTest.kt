package com.example.kotlinpagingperform

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableDefault
import java.awt.print.Pageable

@SpringBootTest
class DocumentRepositoryTest @Autowired constructor(
    val documentRepository: DocumentRepository
) {
    @BeforeEach
    fun setUp() {
        (0..99L).map { Document(id = it) }.let { documentRepository.saveAll(it) }
    }

    @Test
    fun `Page`() {
        val pageable = PageRequest.of(3, 10)
        documentRepository.findAllForPage(pageable).printWithPretty()

    }

    @Test
    fun `Slice`() {
        val pageable = PageRequest.of(0, 10)
        documentRepository.findAllForSlice(pageable).printWithPretty()
    }

    fun Any.printWithPretty() {
        println(ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this))
    }
}