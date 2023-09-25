package com.example.kotlinpagingperform

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
    private val documentRepositoryWithQueryDSL: DocumentRepositoryWithQueryDSL
) {
    fun findAllForPage(pageable: Pageable): Page<Document> {
        return documentRepository.findAllForPage(pageable)
    }

    fun findAllForSlice(pageable: Pageable): Slice<Document> {
        return documentRepository.findAllForSlice(pageable)
    }

    fun findAllForNoOffset(id: Long?, size: Long): NoOffsetResponse<Document> {
        return documentRepositoryWithQueryDSL.findAllForNoOffset(id, size)
    }
}