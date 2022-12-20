package day07

typealias Visitor = (Node) -> Unit

enum class NodeType {
    DIR,
    FILE
}

class Node(val name: String, var size: Long = 0L, val type: NodeType, val parent: Node? = null, var children: MutableList<Node> = mutableListOf()) {

    fun addNode(childNode: Node) = children.add(childNode)

    fun updateNode(size: Long) {
        this.size = size
    }

    fun depthSearch(visit: Visitor) {
        visit(this)

        children.forEach {
            it.depthSearch(visit)
        }
    }

    fun searchValue(value: String): Node? {
        var result: Node? = null

        depthSearch {
            if (it.name == value) {
                result = it
            }
        }

        return result
    }

    fun calculateSize(): Long {
        var size = this.size
        children.forEach {
            size += when (it.type) {
                NodeType.DIR -> it.calculateSize()
                else -> it.size
            }
        }
        if (this.type == NodeType.DIR)
            this.updateNode(size)
        return size
    }
}
