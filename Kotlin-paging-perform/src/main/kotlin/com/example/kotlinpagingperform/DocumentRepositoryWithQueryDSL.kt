package com.example.kotlinpagingperform

import com.example.kotlinpagingperform.QDocument.document
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class DocumentRepositoryWithQueryDSL(
    private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Document::class.java) {
    fun findAllForNoOffset(id: Long? = 0, size: Long): Slice<Document> {
        val dynamicLtId = BooleanBuilder()
        if (id != null) {
            dynamicLtId.and(document.id.lt(id))
        }
        return queryFactory
            .selectFrom(document)
            .where(dynamicLtId)
            .orderBy(document.id.desc())
            .limit(size)
            .fetch()
            .toSlice()
    }

    fun <T> List<T>.toSlice(): SliceImpl<T> {
        return SliceImpl(this, PageRequest.ofSize(this.size), this.isNotEmpty())
    }
}