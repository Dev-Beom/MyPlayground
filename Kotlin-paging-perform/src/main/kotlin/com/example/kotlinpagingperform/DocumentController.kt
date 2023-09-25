package com.example.kotlinpagingperform

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/document")
class DocumentController(
    private val documentService: DocumentService
) {
    @GetMapping("/page")
    fun findAllForPage(pageable: Pageable): Page<Document> {
        return documentService.findAllForPage(pageable)
    }

    @GetMapping("/slice")
    fun findAllForSlice(pageable: Pageable): Slice<Document> {
        return documentService.findAllForSlice(pageable)
    }

    @GetMapping("/no-offset")
    fun findAppForNoOffset(@RequestParam id: Long?, @RequestParam size: Long): NoOffsetResponse<Document> {
        return documentService.findAllForNoOffset(id, size)
    }
}