package com.example.kotlinpagingperform

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository: JpaRepository<Document, Long> {
    @Query(value = "select doc from Document doc order by doc.id desc")
    fun findAllForPage(pageable: Pageable): Page<Document>
    @Query(value = "select doc from Document doc order by doc.id desc")
    fun findAllForSlice(pageable: Pageable): Slice<Document>
}