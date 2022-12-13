package day02

import readInput

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day02", "Day02")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    var sum = 0
    inputs.forEach {
        val input: List<String> = it.split(" ")
        sum += when (input[1]) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
        sum += when {
            input[0] == "A" && input[1] == "Y" -> 6
            input[0] == "C" && input[1] == "X" -> 6
            input[0] == "B" && input[1] == "Z" -> 6
            input[0] == "A" && input[1] == "X" -> 3
            input[0] == "B" && input[1] == "Y" -> 3
            input[0] == "C" && input[1] == "Z" -> 3
            else -> 0
        }

        println("this: ${input[0]} - ${input[1]} $sum")
    }
    println("sum: $sum")
}

private fun part2(inputs: List<String>) {
    var sum = 0
    inputs.forEach {
        var selection = ""
        var game = 0
        val input: List<String> = it.split(" ")

        selection = when {
            input[1] == "X" -> {
                game = 0
                when (input[0]) {
                    "A" -> "Z"
                    "B" -> "X"
                    "C" -> "Y"
                    else -> ""
                }
            }
            input[1] == "Y" -> {
                game  = 3
                when (input[0]) {
                    "A" -> "X"
                    "B" -> "Y"
                    "C" -> "Z"
                    else -> ""
                }
            }
            input[1] == "Z" -> {
                game = 6
                when (input[0]) {
                    "A" -> "Y"
                    "B" -> "Z"
                    "C" -> "X"
                    else -> ""
                }
            }
            else -> ""
            }

        val mine: Int = when (selection) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }

        println("this: ${input[0]} - ${input[1]} - $mine - $game - $sum")
        sum += mine + game
    }
    println("sum: $sum")
}
