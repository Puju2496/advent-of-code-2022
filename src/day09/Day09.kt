package day09

import readInput
import java.util.*
import kotlin.math.abs

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day09", "Day09")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val size = 4000
    val visited: MutableList<Position> = mutableListOf()
    var head = Position(size/2, size/2)
    var tail = Position(size/2, size/2)
    visited.add(tail)
    inputs.forEach {
        val direction = it.split(" ")

        for (i in 0 until direction[1].toInt()) {
            when(direction[0]) {
                "L" -> {
                   head = head.copy(col = head.col - 1)
                }
                "R" -> {
                    head = head.copy(col = head.col + 1)
                }
                "U" -> {
                    head = head.copy(row = head.row - 1)
                }
                "D" -> {
                    head = head.copy(row = head.row + 1)
                }
            }
            val rowDifference = tail.row - head.row
            val colDifference = tail.col - head.col

            if (abs(rowDifference) == 2 && abs(colDifference) == 1) {
                val row = tail.row + if (rowDifference > 0)  - 1 else + 1
                tail = tail.copy(row = row, col = head.col)
            }
            else if (abs(colDifference) == 2 && abs(rowDifference) == 1) {
                val col = tail.col + if (colDifference > 0) - 1 else + 1
                tail = tail.copy(row = head.row, col = col)
            }
            else if (head.row == tail.row + 2) {
                tail = tail.copy(row = tail.row + 1)
            }
            else if (head.row == tail.row - 2) {
                tail = tail.copy(row = tail.row - 1)
            }
            else if (head.col == tail.col + 2) {
                tail = tail.copy(col = tail.col + 1)
            }
            else if (head.col == tail.col - 2) {
                tail = tail.copy(col = tail.col  - 1)
            }
            if (!visited.contains(tail))
                visited.add(tail)
        }
    }
    println("tail - ${visited.size} - ${visited}")
}

private fun part2(inputs: List<String>) {
    val size = 4000
    val visited: MutableList<Position> = mutableListOf()
    var head = Position(size/2, size/2)
    val tail: MutableList<Position> = generateSequence { Position(size/2, size/2) }.take(9).toMutableList()

    visited.add(tail[8])

    inputs.forEach {
        val direction = it.split(" ")

        for (i in 0 until direction[1].toInt()) {
            when (direction[0]) {
                "L" -> head = head.copy(col = head.col - 1)
                "R" -> head = head.copy(col = head.col + 1)
                "U" -> head = head.copy(row = head.row - 1)
                "D" -> head = head.copy(row = head.row + 1)
            }

            for (j in 0 until tail.size) {
                val head1 = if (j == 0) head else tail[j-1]

                val rowDifference = tail[j].row - head1.row
                val colDifference = tail[j].col - head1.col

                if (abs(rowDifference) == 2 && abs(colDifference) == 2) {
                    val row = tail[j].row + if (rowDifference > 0) - 1 else + 1
                    val col = tail[j].col + if (colDifference > 0) - 1 else + 1
                    tail[j] = Position(row, col)
                } else if (abs(rowDifference) == 2 && abs(colDifference) ==  1) {
                    val row = tail[j].row + if (rowDifference > 0) - 1 else + 1
                    tail[j] = Position(row, head1.col)
                } else if (abs(colDifference) == 2 && abs(rowDifference) == 1) {
                    val col = tail[j].col + if (colDifference > 0) - 1 else + 1
                    tail[j] = Position(head1.row, col)
                } else if (head1.row == tail[j].row + 2)
                    tail[j] = tail[j].copy(row = tail[j].row + 1)
                else if (head1.row == tail[j].row - 2)
                    tail[j] = tail[j].copy(row = tail[j].row - 1)
                else if (head1.col == tail[j].col + 2)
                    tail[j] = tail[j].copy(col = tail[j].col + 1)
                else if (head1.col == tail[j].col - 2)
                    tail[j] = tail[j].copy(col = tail[j].col - 1)
            }
            if (!visited.contains(tail[8]))
                visited.add(tail[8])
        }
    }
    println("tail - ${visited.size} - $visited")
}

data class Position(val row: Int, val col: Int)