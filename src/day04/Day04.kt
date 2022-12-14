package day04

import readInput

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day04", "Day04")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    var sum = 0
    inputs.forEach {
        val pair = it.split(",", "-", limit = 4).map {
            it.toInt()
        }
        if ((pair[0] <= pair[2] && pair[1] >= pair[3]) || (pair[0] >= pair[2] && pair[1] <= pair[3])) {
            sum += 1
        }

        println("${inputs.indexOf(it)} - $pair - $sum")
    }
}

private fun part2(inputs: List<String>) {
    var sum = 0
    inputs.forEach {
        val pair = it.split(",", "-", limit = 4).map {
            it.toInt()
        }
        sum += when {
            pair[2] <= pair[1] && pair[1] <= pair[3] -> 1
            pair[0] <= pair[2] && pair[2] <= pair[1] -> 1
            pair[0] <= pair[3] && pair[3] <= pair[1] -> 1
            pair[2] <= pair[0] && pair[0] <= pair[3] -> 1
            else -> 0

        }
        println("${inputs.indexOf(it)} - $pair - $sum")
    }
}