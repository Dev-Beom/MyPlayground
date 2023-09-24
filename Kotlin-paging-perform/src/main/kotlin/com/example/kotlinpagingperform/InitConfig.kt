package com.example.kotlinpagingperform

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitConfig(
    private val documentRepository: DocumentRepository,
    @Value("\${config.enable-init-data}") private val enableInitData: Boolean,
    @Value("\${config.init-data-count}") private val initDataCount: Long
) {
    @Bean
    fun initializeDatabase(): ApplicationRunner = ApplicationRunner {
        if (enableInitData) {
            val documents = (0 until initDataCount).map {
                Document(id = it, title = "$it title", content = "$it content")
            }
            documentRepository.saveAll(documents)
        }
    }
}