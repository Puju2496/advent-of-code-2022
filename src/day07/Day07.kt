package day07

import readInput
import java.util.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("src/day07", "Day07")
    println("Part1")
    val tree = part1(input)
    println("Part2")
    part2(tree)
}

private fun part1(inputs: List<String>): Node {
    val tree = buildTree(inputs)
    var totalSize = 0L

    println("start")
    tree.updateNode(tree.calculateSize())
    tree.depthSearch {
        println("node - ${it.name} - ${it.type} - ${it.size}")
        if (it.type == NodeType.DIR && it.size <= 100000)
            totalSize += it.size
    }
    println("total size - $totalSize")

    return tree
}

private fun part2(tree: Node) {
    val usedSpace = tree.size
    val unusedSpace = 70000000 - usedSpace
    val requiredMoreSpace = 30000000 - unusedSpace

    println("required space- $requiredMoreSpace")
    var min = tree.size
    tree.depthSearch {
        if (it.type == NodeType.DIR && it.size in requiredMoreSpace until min)
            min = it.size
    }
    println("file size to delete to free space - $min ")
}

private fun buildTree(inputs: List<String>): Node {
    val parentNode = Node(name = "/", type = NodeType.DIR)
    var currentNode: Node? = parentNode
    var nextList = false
    inputs.forEach {
        val nodeValue = it.split(" ")
        when (nodeValue[0]) {
            "$" -> {
                if (nodeValue[1] == "ls")
                    nextList = true
                else if (nodeValue[1] == "cd") {
                    nextList = false
                    currentNode = when (nodeValue[2]) {
                        "/" -> {
                            parentNode
                        }
                        ".." -> {
                            currentNode?.parent
                        }
                        else -> {
                            currentNode?.searchValue(nodeValue[2])
                        }
                    }
                }
            }
            "dir" -> {
                val node = Node(name = nodeValue[1], type = NodeType.DIR, parent = currentNode)
                currentNode?.addNode(node)
            }
            else -> {
                val node = Node(name = it, type = NodeType.FILE, parent = currentNode, size = nodeValue[0].toLong())
                currentNode?.addNode(node)
            }
        }
    }

    return parentNode
}