package day05

import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day05", "Day05")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val stackData = getStack(inputs)
    println("stack - $stackData")

    val procedureList = getProcedure(inputs)
    procedureList.forEach {
        for (i in 0 until it[0].toInt()) {
            val last = stackData[it[1].toInt() - 1]?.pop()
            if (last != null)
                stackData[it[2].toInt() - 1]?.push(last)
        }
    }
    println("after procedure - $stackData")

    val top = StringBuilder()
    stackData.forEach {
        top.append(it?.peek())
    }
    println("top - $top")
}

private fun part2(inputs: List<String>) {
    val stackData = getStack(inputs)
    println("stack -$stackData")

    val procedureList = getProcedure(inputs)
    procedureList.forEach {
        if (it[0].toInt() == 1) {
            val last = stackData[it[1].toInt() - 1]?.pop()
            if (last != null)
                stackData[it[2].toInt() - 1]?.push(last)
        } else {
            val list = mutableListOf<Char>()
            for (i in 0 until it[0].toInt()) {
                val last = stackData[it[1].toInt() - 1]?.pop()
                if (last != null)
                    list.add(last)
            }
            list.reversed().forEach { char ->
                stackData[it[2].toInt() - 1]?.push(char)
            }
        }
    }
    println("after procedure - $stackData")

    val top = StringBuilder()
    stackData.forEach {
        top.append(it?.peek())
    }
    println("top - $top")
}

private fun getStack(inputs: List<String>): MutableList<ArrayDeque<Char>?> {
    var index = 0
    val stack: List<String> = inputs.takeWhile { it.contains('[') }.reversed()
    val dataMap: MutableList<ArrayDeque<Char>?> = mutableListOf()
    for (i in 0 until inputs[inputs.indexOf("") - 1].last().digitToInt()) {
        dataMap.add(ArrayDeque())
    }

    for (i in 1..stack.first().length step 4) {
        stack.forEach {
            if (i < it.length && it[i].isUpperCase())
                dataMap[index]?.push(it[i])
        }
        index++
    }

    return dataMap
}

private fun getProcedure(inputs: List<String>): List<List<String>> {
    val procedureList = inputs.subList(inputs.indexOf("") + 1, inputs.size).map { value ->
        value.split(" ").filter { it.toIntOrNull() != null }
    }

    return procedureList
}