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
    fun findAllForNoOffset(id: Long?, size: Long = 20): NoOffsetResponse<Document> {
        val dynamicLtId = BooleanBuilder()
        if (id != null) {
            dynamicLtId.and(document.id.lt(id))
        }
        val queryResult = queryFactory
            .selectFrom(document)
            .where(dynamicLtId)
            .orderBy(document.id.desc())
            .limit(size)
            .fetch()
        val nextId = queryResult.last().id
        return queryResult.toOffsetResponse(nextId)
    }

    fun <T> List<T>.toOffsetResponse(nextId: Long): NoOffsetResponse<T> =
        NoOffsetResponse.of(this, nextId)
}