package day12

import println
import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day12", "Day12")
    println("Part1")
    part1(input)
    println("Part2")
    part2(input)
}

private fun part1(inputs: List<String>) {
    val list = Array(inputs.size) { CharArray(inputs[0].length) }
    var startNode = Node(0, 0)
    var endNode = Node(0, 0)
    inputs.forEachIndexed { i: Int, s: String ->
        s.toCharArray().forEachIndexed { j: Int, ch: Char ->
            if (ch == 'S')
                startNode = Node(i, j)
            if (ch == 'E')
                endNode = Node(i , j)

            list[i][j] = if (ch == 'S') 'a' else if (ch == 'E') 'z' else ch
        }
    }

    val distance = findShortestPath(list, startNode, endNode)
    println("distance - $distance")
}

private fun findShortestPath(list: Array<CharArray>, startNode: Node, endNode: Node): Int {
    val visited = HashSet<Pair<Int, Int>>()
    val queue = ArrayDeque<Node>()
    queue.add(startNode)
    visited.add(Pair(startNode.row, startNode.col))

    while (!queue.isEmpty()) {
        val currentNode = queue.pop()
        if (currentNode.row == endNode.row && currentNode.col == endNode.col) {
            return currentNode.distance
        }

        val currentChar = list[currentNode.row][currentNode.col]

        if (isNext(currentNode.row - 1, currentNode.col, list, currentChar, visited)) {
            visited.add(Pair(currentNode.row - 1, currentNode.col))
            queue.add(Node(currentNode.row - 1, currentNode.col, currentNode.distance + 1))
        }
        if (isNext(currentNode.row + 1, currentNode.col, list, currentChar, visited)) {
            visited.add(Pair(currentNode.row + 1, currentNode.col))
            queue.add(Node(currentNode.row + 1, currentNode.col, currentNode.distance + 1))
        }
        if (isNext(currentNode.row, currentNode.col - 1, list, currentChar, visited)) {
            visited.add(Pair(currentNode.row, currentNode.col - 1))
            queue.add(Node(currentNode.row, currentNode.col - 1, currentNode.distance + 1))
        }
        if (isNext(currentNode.row, currentNode.col + 1, list, currentChar, visited)) {
            visited.add(Pair(currentNode.row, currentNode.col + 1))
            queue.add(Node(currentNode.row, currentNode.col + 1, currentNode.distance + 1))
        }
    }
    return -1
}

private fun isNext(row: Int, col: Int, list: Array<CharArray>, currentChar: Char, visited: HashSet<Pair<Int, Int>>): Boolean {
    if (row !in list.indices || col !in 0 until list[0].size) return false

    var isNext = false
    val nextChar = list[row][col]
    if (currentChar + 1 == nextChar || currentChar >= nextChar) {
        if (!visited.contains(Pair(row, col))) {
            isNext = true
        }
    }
    return isNext
}

private fun part2(inputs: List<String>) {
    val list = Array(inputs.size) { CharArray(inputs[0].length) }
    val startNode = ArrayList<Node>()
    var endNode = Node(0, 0)
    inputs.forEachIndexed { i: Int, s: String ->
        s.toCharArray().forEachIndexed { j: Int, ch: Char ->
            when (ch) {
                'S' -> {
                    startNode.add(Node(i, j))
                    list[i][j] = 'a'
                }
                'a' -> {
                    startNode.add(Node(i, j))
                    list[i][j] = ch
                }
                'E' -> {
                    endNode = Node(i , j)
                    list[i][j] = 'z'
                }
                else -> list[i][j] = ch
            }
        }
    }

    var shortestDistance = Int.MAX_VALUE
    startNode.forEach {
        val distance = findShortestPath(list, it, endNode)
        if (distance < shortestDistance && distance > -1)
            shortestDistance = distance
    }
    println("shortest distance - $shortestDistance")
}

data class Node(val row: Int, val col: Int, var distance: Int = 0)