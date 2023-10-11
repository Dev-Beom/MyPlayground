package com.example.performspringmvc

fun createMatrix(rows: Int, cols: Int): Array<IntArray> {
    val matrix = Array(rows) { IntArray(cols) }
    var value = 1
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i][j] = value
            value++
        }
    }
    return matrix
}

fun matrixMultiplication(matrixA: Array<IntArray>, matrixB: Array<IntArray>): Array<IntArray> {
    val numRowsA = matrixA.size
    val numColsA = matrixA[0].size
    val numRowsB = matrixB.size
    val numColsB = matrixB[0].size

    if (numColsA != numRowsB) {
        throw IllegalArgumentException("행렬 A의 열 수와 행렬 B의 행 수가 일치하지 않습니다.")
    }

    val result = Array(numRowsA) { IntArray(numColsB) }

    for (i in 0 until numRowsA) {
        for (j in 0 until numColsB) {
            var sum = 0
            for (k in 0 until numColsA) {
                sum += matrixA[i][k] * matrixB[k][j]
            }
            result[i][j] = sum
        }
    }

    return result
}