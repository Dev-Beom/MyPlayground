package com.example.performspringwebflux

import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets


@RestController
@RequestMapping("/")
class Controller {
    @GetMapping("/empty")
    fun empty(): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("empty"))
    }
}

@RestController
@RequestMapping("/cpu-bound")
class CPUBoundController {
    @GetMapping("/matrix-multiplication")
    fun matrixMultiplication(): Mono<ResponseEntity<String>> {
        return Mono.fromSupplier {
            val matrixA = createMatrix(100, 100)
            val matrixB = createMatrix(100, 100)
            val result = matrixMultiplication(matrixA, matrixB).sumOf { row -> row.sum() }.toString()
            ResponseEntity.ok(result)
        }
    }
}

@RestController
@RequestMapping("/io-bound")
class IOBoundController {
    @PostMapping("/file-upload")
    fun fileUpload(@RequestPart("file") file: FilePart): Mono<ResponseEntity<String>> {
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
    }
}