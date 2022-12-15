package day06

import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day06", "Day06")
    println("Part1")
    part1(input[0])
    println("Part2")
    part2(input[0])
}

private fun part1(inputs: String) {
    val code = StringBuilder()
    var index = 0
    for (i in inputs.indices) {
        if (code.length < 4 && code.indexOf(inputs[i].toString()) != -1) {
            code.deleteCharAt(0)
            code.append(inputs[i])
        } else {
            code.append(inputs[i])
        }

        if (code.length == 4 && code.chars().distinct().count().toInt() == code.length) {
            index = i + 1
            break
        } else if (code.chars().distinct().count().toInt() != code.length) {
            code.deleteCharAt(0)
        }
    }

    println("index - $code - $index")
}

private fun part2(inputs: String) {
    val code = StringBuilder()
    var index = 0
    for (i in inputs.indices) {
        if (code.length < 14 && code.indexOf(inputs[i].toString()) != -1) {
            code.deleteCharAt(0)
            code.append(inputs[i])
        } else {
            code.append(inputs[i])
        }

        if (code.length == 14 && code.chars().distinct().count().toInt() == code.length) {
            index = i + 1
            break
        } else if (code.chars().distinct().count().toInt() != code.length) {
            code.deleteCharAt(0)
        }
    }

    println("index - $code - $index")
}