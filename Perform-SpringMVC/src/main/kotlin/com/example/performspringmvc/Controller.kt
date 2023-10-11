package com.example.performspringmvc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/")
class Controller {
    @GetMapping("/empty")
    fun empty(): ResponseEntity<String> {
        return ResponseEntity.ok("empty")
    }
}

@RestController
@RequestMapping("/cpu-bound")
class CPUBoundController {
    @GetMapping("/matrix-multiplication")
    fun matrixMultiplication(): ResponseEntity<String> {
        val matrixA = createMatrix(100, 100)
        val matrixB = createMatrix(100, 100)
        val result = matrixMultiplication(matrixA, matrixB).sumOf { row -> row.sum() }.toString()
        return ResponseEntity.ok(result)
    }
}

@RestController
@RequestMapping("/io-bound")
class IOBoundController {
    @PostMapping("/file-upload")
    fun fileUpload(@RequestParam file: MultipartFile): ResponseEntity<String> {
        return if (!file.isEmpty) {
            try {
                val uploadedFileName = file.originalFilename
                ResponseEntity.ok("파일 업로드 성공: $uploadedFileName, 파일 사이즈: ${file.size}")
            } catch (e: Exception) {
                ResponseEntity.badRequest().body("파일 업로드 실패: ${e.message}")
            }
        } else {
            ResponseEntity.badRequest().body("업로드된 파일이 없습니다.")
        }
    }
}