package day03

import readInput

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day03", "Day03")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    var sum = 0
    inputs.forEach {
        val first: String = it.subSequence(0, it.length / 2).toString()
        val second: String = it.subSequence(it.length / 2, it.length).toString()

        val same: String = first.filter { a -> second.contains(a) }
        sum += same.first().code - if (same.first().isUpperCase()) 38 else 96

        println("input $it - $first - $second - $same - ${same.first().code} - $sum")
    }
    println("sum - $sum")
}

private fun part2(inputs: List<String>) {
    val list: List<List<String>> = inputs.groupBy { inputs.indexOf(it) / 3 }.values.toList()
    var sum = 0

    list.forEach {
        val same: String = it[0].filter { a -> it[1].contains(a) && it[2].contains(a) }
        sum += same.first().code - if (same.first().isUpperCase()) 38 else 96

        println("sub : $it - $same - $sum")
    }
    println("sum - $sum")
}