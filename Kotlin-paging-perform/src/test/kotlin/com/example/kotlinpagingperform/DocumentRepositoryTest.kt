package com.example.kotlinpagingperform

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DocumentRepositoryTest @Autowired constructor(
    val documentRepository: DocumentRepository,
    val documentRepositoryWithQueryDSL: DocumentRepositoryWithQueryDSL,
) {
    private val logger = LoggerFactory.getLogger(DocumentRepositoryTest::class.java)

    @Test
    fun `Page`() {
        val pageable = PageRequest.of(999, 10)
        val result = documentRepository.findAllForPage(pageable)
        assertThat(result.content.first().id).isEqualTo(10)
        assertThat(result.content.last().id).isEqualTo(1)
    }

    @Test
    fun `Slice`() {
        val pageable = PageRequest.of(999, 10)
        val result = documentRepository.findAllForSlice(pageable)
        assertThat(result.content.first().id).isEqualTo(10)
        assertThat(result.content.last().id).isEqualTo(1)
    }

    @Test
    fun `Slice_with_No_offset`() {
        val pageSize = 10L
        val result = documentRepositoryWithQueryDSL.findAllForNoOffset(11, pageSize)
        assertThat(result.content.first().id).isEqualTo(10)
        assertThat(result.content.last().id).isEqualTo(1)
    }

    fun Any.printWithPretty() {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this))
    }
}