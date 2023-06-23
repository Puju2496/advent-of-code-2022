package day13

import println
import readInput
import java.util.*
import kotlinx.serialization.json.JSON

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day13", "Day13")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val data: MutableList<ArrayDeque<Char>> = mutableListOf()
    var indexs: MutableList<Int> = mutableListOf()
    var index = 0
    val obj = JSON.parse()
    inputs.forEach {
        val stack = ArrayDeque<Char>()
        for (i in it.indices) {
            if (it[i] != ',')
                stack.add(it[i])
        }
        if (it.isNotEmpty()) {
            data.add(stack)
        }
    }

    for (i in 0 until data.size - 1 step 2) {
        index++
        var first = data[i].pop()
        var second = data[i + 1].pop()
        var isAvailable: Boolean = true
        while (isAvailable) {
            first = data[i].poll()
            second = data[i + 1].poll()

            if (first == null || second == null)
                break
            when {
                first == '[' || second == '[' -> {
                    val firstNew: MutableList<Char> = mutableListOf()
                    val secondNew: MutableList<Char> = mutableListOf()
                    if (first == ']' || second == ']')
                        break
                    else if (first == '[' && second == '[') {
                        var firstChar = data[i].pop()
                        var secondChar = data[i+1].pop()
                        while (firstChar != ']') {
                            firstNew.add(firstChar)
                            firstChar = data[i].pop()
                        }
                        while (secondChar != ']') {
                            secondNew.add(secondChar)
                            secondChar = data[i+1].pop()
                        }
                    }
                    else if (first == '[') {
                        var char = data[i].pop()
                        while (char != ']') {
                            firstNew.add(char)
                            char = data[i].pop()
                        }
                        secondNew.add(second)
                    } else if (second == '[') {
                        var char = data[i+1].pop()
                        while (char != ']') {
                            secondNew.add(char)
                            char = data[i+1].pop()
                        }
                        firstNew.add(first)
                    }


                    for (j in firstNew.indices) {
                        if (Character.isDigit(firstNew[j]) && Character.isDigit(secondNew[j]) && firstNew[j].digitToInt() < secondNew[j].digitToInt()) {
                            indexs.add(index)
                            break
                        } else if (Character.isDigit(firstNew[j]) && Character.isDigit(secondNew[j]) && firstNew[j].digitToInt() > secondNew[j].digitToInt()) {
                            break
                        }
                    }
                }
                first == ']' || second == ']' -> {
                    if (first == ']' && Character.isDigit(second)) {
                        indexs.add(index)
                        break
                    }
                    else if (second == ']' && Character.isDigit(first)) {
                        break
                    }
                }
                Character.isDigit(first) && Character.isDigit(second) -> {
                    if (first.digitToInt() < second.digitToInt()) {
                        indexs.add(index)
                        break
                    } else if (first.digitToInt() > second.digitToInt()) {
                        break
                    }
                }
            }
            isAvailable = data[i].isNotEmpty() && data[i].isNotEmpty()
        }
    }
    println("indexs - $indexs - ${indexs.sum()}")
}

private fun part2(inputs: List<String>) {

}