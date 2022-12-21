package day08

import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day08", "Day08")
    println("Part1")
    val treeHeightMatrix = part1(input)
    println("Part2")
    part2(treeHeightMatrix)
}

private fun part1(inputs: List<String>): Array<IntArray> {
    val rowSize = inputs[0].length
    val colSize = inputs.size
    val inputMatrix = Array(rowSize) { IntArray(colSize) }
    var visibleTreesSize = 0
    visibleTreesSize += ((colSize - 2) * 2)
    visibleTreesSize += rowSize * 2

    inputs.forEachIndexed { index, value ->
        val treesHeights = value.split("").toMutableList()
        treesHeights.removeAll(listOf(""))

        treesHeights.mapIndexed { treeIndex, treeValue ->
            inputMatrix[index][treeIndex] = treeValue.toInt()
        }
    }

    for (i in 1 until rowSize - 1) {
        for (j in 1 until colSize - 1) {
            val current = inputMatrix[i][j]
            val isVisible = checkLeftHorizontal(current, inputMatrix, i, j - 1)
                        || checkRightHorizontal(current, inputMatrix, i, j + 1)
                        || checkTopVertical(current, inputMatrix, j, i - 1)
                        || checkBottomVertical(current, inputMatrix, j, i + 1)

            if (isVisible)
                visibleTreesSize++
        }
    }
    println("visible trees - $visibleTreesSize")
    return inputMatrix
}

private fun checkLeftHorizontal(current: Int, matrix: Array<IntArray>, index: Int, end: Int): Boolean {
    for (j in 0..end) {
        if (matrix[index][j] >= current)
            return false
    }
    return true
}

private fun checkRightHorizontal(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Boolean {
    for (j in start until matrix[0].size) {
        if (matrix[index][j] >= current)
            return false
    }
    return true
}

private fun checkTopVertical(current: Int, matrix: Array<IntArray>, index: Int, end: Int): Boolean {
    for (i in 0..end) {
        if (matrix[i][index] >= current)
            return false
    }
    return true
}

private fun checkBottomVertical(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Boolean {
    for (i in start until matrix.size) {
        if (matrix[i][index] >= current)
            return false
    }
    return true
}

private fun part2(inputMatrix: Array<IntArray>) {
    var max = Int.MIN_VALUE

    println("${inputMatrix.size}, ${inputMatrix[0].size}")
    for (i in 1 until inputMatrix[0].size - 1) {
        for (j in 1 until inputMatrix.size - 1) {
            val current = inputMatrix[i][j]

            val result = getLeftHorizontal(current, inputMatrix, i, j - 1) * getRightHorizontal(current, inputMatrix, i, j + 1) * getTopVertical(current, inputMatrix, j, i - 1) * getBottomVertical(current, inputMatrix, j, i + 1)
            println("$i-$j- $max - $result")
            if (result > max)
                max = result
        }
    }
    println("max - $max")
}

private fun getLeftHorizontal(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Int {
    var count = 0
    for (j in start downTo 0) {
        if (matrix[index][j] <= current)
            ++count
        if (matrix[index][j] >= current)
            break
    }
    return count
}

private fun getRightHorizontal(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Int {
    var count = 0

    for (j in start until matrix[0].size) {
        if (matrix[index][j] <= current)
            ++count
        if (matrix[index][j] >= current)
            break
    }
    return count
}

private fun getTopVertical(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Int {
    var count = 0
    for (i in start downTo 0) {
        if (matrix[i][index] <= current)
            ++count
        if (matrix[i][index] >= current)
            break
    }
    return count
}

private fun getBottomVertical(current: Int, matrix: Array<IntArray>, index: Int, start: Int): Int {
    var count = 0
    for (i in start until matrix.size) {
        if (matrix[i][index] <= current)
            ++count
        if (matrix[i][index] >= current)
            break
    }
    return count
}