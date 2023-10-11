package com.example.performspringwebflux

import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import kotlin.coroutines.coroutineContext

@RestController
@RequestMapping("/coroutine")
class ControllerByCoroutine {
    @GetMapping("/empty")
    suspend fun empty(): ResponseEntity<String> {
        return ResponseEntity.ok("empty")
    }
}

@RestController
@RequestMapping("/coroutine/cpu-bound")
class CPUBoundControllerByCoroutine {
    @GetMapping("/matrix-multiplication")
    suspend fun matrixMultiplication(): ResponseEntity<String> {
        return Mono.fromSupplier {
            val matrixA = createMatrix(100, 100)
            val matrixB = createMatrix(100, 100)
            val result = matrixMultiplication(matrixA, matrixB).sumOf { row -> row.sum() }.toString()
            ResponseEntity.ok(result)
        }.awaitSingle()
    }
}

@RestController
@RequestMapping("/coroutine/io-bound")
class IOBoundControllerByCoroutine {
    @PostMapping("/file-upload")
    suspend fun fileUpload(@RequestPart("file") file: FilePart): ResponseEntity<String> {
        return file.content()
            .reduce(ByteArray(0)) { acc, dataBuffer ->
                val bytes = ByteArray(dataBuffer.readableByteCount())
                dataBuffer.read(bytes)
                DataBufferUtils.release(dataBuffer)
                acc + bytes
            }
            .map { bytes ->
                val responseMessage = "파일 업로드 성공. 파일 데이터 길이: ${bytes.size} bytes"
                ResponseEntity.ok(responseMessage)
            }
            .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body("업로드된 파일이 없습니다.")))
            .awaitSingle()
    }
}