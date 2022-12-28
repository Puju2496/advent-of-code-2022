package day10

import println
import readInput
import java.util.*
import kotlin.math.abs
import kotlin.collections.MutableList

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day10", "Day10")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    var result = 0
    var sum = 1
    var i = 0
    var k = 20
    inputs.forEach {
        val value = it.split(" ")

        when (value[0]) {
            "addx" -> {
                for (j in 0..1) {
                    i++
                    if (k == i) {
                        result += k * sum
                        k += 40
                    }
                    if (j == 1)
                        sum += value[1].toInt()
                }
            }
            "noop" -> {
                i++
                if (k == i) {
                    result += k * sum
                    k += 40
                }
            }
        }
    }
    println("result - $result")
}

private fun part2(inputs: List<String>) {
    val result = StringBuilder("")
    val sprite: MutableList<Char> = Array(40) {
        '.'
    }.also {
        it[0] = '#'
        it[1] = '#'
        it[2] = '#'
    }.toMutableList()
    var sum = 1
    var i = 0
    var type: Char
    inputs.forEach {
        val value = it.split(" ")
        when (value[0]) {
            "addx" -> {
                for (j in 0..1) {
                    ++i
                    type = if (i % 40 > 0) sprite[(i % 40) - 1] else sprite[0]
                    result.append(type)
                    println("sprite - i- $i - $it - $sum - $type")
                    if (j == 1) {
                        sum += value[1].toInt()
                        sprite.replaceAll {
                            '.'
                        }
                        sprite[(abs(sum) - 1) % 40] = '#'
                        sprite[abs(sum) % 40] = '#'
                        sprite[(abs(sum) + 1) % 40] = '#'
                        println("sprite -1 - $sprite")
                    }
                }
            }
            "noop" -> {
                ++i
                type = if (i % 40 > 0) sprite[(i % 40) - 1] else sprite[0]
                result.append(type)
            }
        }
    }

    for (k in 0 until result.length) {
        print("${result[k]} ")
        if ((k+1) % 40 == 0)
            println()
    }
}