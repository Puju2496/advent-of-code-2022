package day11

import println
import readInput
import java.util.*
import kotlin.math.abs
import kotlin.collections.MutableList

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day11", "Day11")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val monkeyList: MutableList<MonkeyData> = mutableListOf()
    var monkeyData = MonkeyData(ArrayDeque(), Operation(' ', 0), 0, 0, 0)
    inputs.forEachIndexed { index: Int, value: String ->
        when (index % 7) {
            1 -> {
                val values = value.split(":", ",").toMutableList()
                values.removeAt(0)

                val worryLevelList = monkeyData.worryLevelList
                worryLevelList.addAll(values.map {
                    it.trim().toInt()
                })
                monkeyData = monkeyData.copy(worryLevelList = worryLevelList)
            }
            2 -> {
                val values = value.split(" ")
                val operationNumber = if (values[values.size - 1].toIntOrNull() != null) values[values.size - 1].toInt() else -1
                val operator = values[values.size - 2].last()
                monkeyData = monkeyData.copy(operation = Operation(operator, operationNumber))
            }
            3 -> {
                val values = value.split(" ")
                val divisibleIndex = values[values.size - 1].toInt()
                monkeyData = monkeyData.copy(divisible = divisibleIndex)
            }
            4 -> {
                val values = value.split(" ")
                val trueIndex = values[values.size - 1].toInt()
                monkeyData = monkeyData.copy(onTrue = trueIndex)
            }
            5 -> {
                val values = value.split(" ")
                val falseIndex = values[values.size - 1].toInt()
               monkeyData = monkeyData.copy(onFalse = falseIndex)
            }
            6 -> {
                monkeyList.add(monkeyData)
                monkeyData = MonkeyData(ArrayDeque(), Operation(' ', 0), 0, 0, 0)
            }
        }
    }
    monkeyList.add(monkeyData)
    monkeyData = MonkeyData(ArrayDeque(), Operation(' ', 0), 0, 0, 0)

    val activeMonkey = Array(monkeyList.size) {
        0
    }

    for (k in 0 until 20) {
        for (i in 0 until monkeyList.size) {
            var monkey = monkeyList[i]
            for (j in 0 until monkey.worryLevelList.size) {
                val worryLevel = monkey.worryLevelList.peek()
                if (worryLevel != 0) {
                    activeMonkey[i] = activeMonkey[i] + 1
                }
                var operatedLevel: Int = when (monkey.operation.sign) {
                    '*' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel * worryLevel
                        } else {
                            worryLevel * monkey.operation.number
                        }
                    }
                    '+' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel + worryLevel
                        } else {
                            worryLevel + monkey.operation.number
                        }
                    }
                    '-' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel - worryLevel
                        } else {
                            worryLevel - monkey.operation.number
                        }
                    }
                    '/' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel / worryLevel
                        } else {
                            worryLevel / monkey.operation.number
                        }
                    }
                    else -> 0
                }
                operatedLevel = Math.floor((operatedLevel.toDouble() / 3)).toInt()
                if (operatedLevel % monkey.divisible == 0) {
                    var monkeyTrue = monkeyList[monkey.onTrue]
                    val list = monkeyTrue.worryLevelList
                    list.add(operatedLevel)
                    monkeyTrue = monkeyTrue.copy(worryLevelList = list)
                    monkeyList[monkey.onTrue] = monkeyTrue
                } else {
                    var monkeyFalse = monkeyList[monkey.onFalse]
                    val list = monkeyFalse.worryLevelList
                    list.add(operatedLevel)
                    monkeyFalse = monkeyFalse.copy(worryLevelList = list)
                    monkeyList[monkey.onFalse] = monkeyFalse
                }

                val index = monkeyList.indexOf(monkey)
                monkey.worryLevelList.remove()
                monkey = monkey.copy(worryLevelList = monkey.worryLevelList)
                monkeyList[index] = monkey
            }
        }
    }

    println("inputs - $monkeyList")
    var max = 0
    var max2 = 0
    for (i in activeMonkey) {
        if (i > max)
            max = i
    }

    for (i in activeMonkey) {
        if (i in (max2 + 1) until max)
            max2 = i
    }

    println("max - $max - $max2 - ${max * max2}")

}

private fun part2(inputs: List<String>) {
    val monkeyList: MutableList<MonkeyData2> = mutableListOf()
    var monkeyData = MonkeyData2(ArrayDeque(), Operation(' ', 0), 0, 0, 0)
    inputs.forEachIndexed { index: Int, value: String ->
        when (index % 7) {
            1 -> {
                val values = value.split(":", ",").toMutableList()
                values.removeAt(0)

                val worryLevelList = monkeyData.worryLevelList
                worryLevelList.addAll(values.map {
                    it.trim().toLong()
                })
                monkeyData = monkeyData.copy(worryLevelList = worryLevelList)
            }
            2 -> {
                val values = value.split(" ")
                val operationNumber = if (values[values.size - 1].toIntOrNull() != null) values[values.size - 1].toInt() else -1
                val operator = values[values.size - 2].last()
                monkeyData = monkeyData.copy(operation = Operation(operator, operationNumber))
            }
            3 -> {
                val values = value.split(" ")
                val divisibleIndex = values[values.size - 1].toInt()
                monkeyData = monkeyData.copy(divisible = divisibleIndex)
            }
            4 -> {
                val values = value.split(" ")
                val trueIndex = values[values.size - 1].toInt()
                monkeyData = monkeyData.copy(onTrue = trueIndex)
            }
            5 -> {
                val values = value.split(" ")
                val falseIndex = values[values.size - 1].toInt()
                monkeyData = monkeyData.copy(onFalse = falseIndex)
            }
            6 -> {
                monkeyList.add(monkeyData)
                monkeyData = MonkeyData2(ArrayDeque(), Operation(' ', 0), 0, 0, 0)
            }
        }
    }
    monkeyList.add(monkeyData)
    monkeyData = MonkeyData2(ArrayDeque(), Operation(' ', 0), 0, 0, 0)

    val activeMonkey = Array(monkeyList.size) {
        0L
    }

    var modBy = 1
    for (monkey in monkeyList) {
        modBy *= monkey.divisible
    }

    for (k in 0 until 10000) {
        for (i in 0 until monkeyList.size) {
            var monkey = monkeyList[i]
            for (j in 0 until monkey.worryLevelList.size) {
                val worryLevel = monkey.worryLevelList.peek()
                if (worryLevel != 0L) {
                    activeMonkey[i] = activeMonkey[i] + 1
                }
                var operatedLevel: Long = when (monkey.operation.sign) {
                    '*' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel * worryLevel
                        } else {
                            worryLevel * monkey.operation.number
                        }
                    }
                    '+' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel + worryLevel
                        } else {
                            worryLevel + monkey.operation.number
                        }
                    }
                    '-' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel - worryLevel
                        } else {
                            worryLevel - monkey.operation.number
                        }
                    }
                    '/' -> {
                        if (monkey.operation.number == -1) {
                            worryLevel / worryLevel
                        } else {
                            worryLevel / monkey.operation.number
                        }
                    }
                    else -> 0
                }
                operatedLevel %= modBy
                if (operatedLevel % monkey.divisible == 0L) {
                    var monkeyTrue = monkeyList[monkey.onTrue]
                    val list = monkeyTrue.worryLevelList
                    list.add(operatedLevel)
                    monkeyTrue = monkeyTrue.copy(worryLevelList = list)
                    monkeyList[monkey.onTrue] = monkeyTrue
                } else {
                    var monkeyFalse = monkeyList[monkey.onFalse]
                    val list = monkeyFalse.worryLevelList
                    list.add(operatedLevel)
                    monkeyFalse = monkeyFalse.copy(worryLevelList = list)
                    monkeyList[monkey.onFalse] = monkeyFalse
                }

                val index = monkeyList.indexOf(monkey)
                monkey.worryLevelList.remove()
                monkey = monkey.copy(worryLevelList = monkey.worryLevelList)
                monkeyList[index] = monkey
            }
        }
    }

    println("inputs - $monkeyList")
    var max = 0L
    var max2 = 0L
    for (i in activeMonkey) {
        if (i > max)
            max = i
    }

    for (i in activeMonkey) {
        if (i in (max2 + 1) until max)
            max2 = i
    }

    println("max - $max - $max2 - ${max * max2}")
}

data class MonkeyData(val worryLevelList: Queue<Int>, val operation: Operation, val divisible: Int, val onTrue: Int, val onFalse: Int)

data class Operation(val sign: Char, val number: Int)

data class MonkeyData2(val worryLevelList: Queue<Long>, val operation: Operation, val divisible: Int, val onTrue: Int, val onFalse: Int)