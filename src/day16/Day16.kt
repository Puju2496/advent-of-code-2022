package day16

import println
import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day16", "Day16")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val valveList: MutableList<Valve> = mutableListOf()
    inputs.forEach {
        val value = it.split(" ", "=", ";")
        val list = if (it.contains("valves")) it.substringAfter("valves ").split(", ").map { Edge(it) }.toMutableList() else it.substringAfter("valve ").split(", ").map { Edge(it) }.toMutableList()
        valveList.add(Valve(value[1], value[5].toInt(), list))
    }

    println("valves - $valveList")

    var minute = 1
    var index = 0
    val openedValves: MutableList<String> = mutableListOf()

    while (minute <= 30) {
        if (valveList[index].rate != 0 && !openedValves.contains(valveList[index].name)) {
            openedValves.add(valveList[index].name)
        }
        valveList.forEach { valve ->
            valve.edges.find { it.name == valveList[index].name}?.opened = true
            var allOpened = false
            valve.edges.forEach {
                allOpened = allOpened && it.opened
            }
            valve.isAllEdgesOpened = allOpened
        }
        var isOpened = false
        var toOpen = Edge("")
        println("valves check - ${valveList[index]}")
        run go@ {
            valveList[index].edges.forEach { edge ->
                println("before - ${edge}, ${valveList.filter { it.name == edge.name}[0]}")
                isOpened = valveList.filter { it.name == edge.name}[0].isAllEdgesOpened
                if (!isOpened) {
                    println("inside if")
                    toOpen = edge
                    return@go
                }
            }
        }
        println("toOpen - $isOpened - $toOpen")
        if (!isOpened) {
            index = valveList.indexOf(valveList.first { it.name == toOpen.name})
        }
        minute++
    }
    println("opened - $openedValves")
}

private fun part2(inputs: List<String>) {

}

data class Valve(val name: String, val rate: Int, val edges: MutableList<Edge>, var isAllEdgesOpened: Boolean = false)

data class Edge(val name: String, var opened: Boolean = false)