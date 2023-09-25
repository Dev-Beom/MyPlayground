package com.example.kotlinpagingperform

class NoOffsetResponse<T>(
    val content: List<T>,
    val hasNext: Boolean,
    val nextId: Long? = null
) {
    companion object {
        fun <T> of(content: List<T>, nextId: Long): NoOffsetResponse<T> {
            return NoOffsetResponse(content, content.isNotEmpty(), nextId)
        }
    }
}