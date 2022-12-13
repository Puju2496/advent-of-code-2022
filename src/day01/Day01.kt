package day01

import readInput

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day01", "Day01")
    val elfs = part1(input)
    part2(elfs)
}

private fun part1(input: List<String>): List<Int> {
    var sum = 0
    val elfs = mutableListOf<Int>()
    var max = 0

    input.forEach {
        if (it == "" || input.indexOf(it) == input.size - 1) {
            elfs.add(sum)
            if (sum > max)
                max = sum
            sum = 0
        }
        else
            sum += it.toInt()
    }
    println("max: $max - index: ${elfs.indexOf(max) + 1}")

    return elfs
}

private fun part2(elfs: List<Int>) {
    val max: Int = elfs.max()
    var max2 = 0
    var max3 = 0

    elfs.forEach {
        if (it > max2 && it < max)
            max2 = it
    }

    elfs.forEach {
        if (it > max3 && it < max2)
            max3 = it
    }
    println("max2: $max2 && max3: $max3")
    println("${max + max2 + max3}")
}
