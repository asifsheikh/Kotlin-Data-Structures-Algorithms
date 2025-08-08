package data_structure.graphs

/**
 * EULERIAN PATH AND CIRCUIT (Hierholzer's Algorithm)
 *
 * Supports undirected graphs.
 */
object EulerianPathCircuit {

    fun hasEulerianCircuit(graph: Map<Int, List<Int>>): Boolean {
        if (!isConnectedIgnoringZeroDegree(graph)) return false
        // All vertices have even degree
        return graph.keys.all { (graph[it]?.size ?: 0) % 2 == 0 }
    }

    fun hasEulerianPath(graph: Map<Int, List<Int>>): Boolean {
        if (!isConnectedIgnoringZeroDegree(graph)) return false
        // Exactly 0 or 2 vertices have odd degree
        val odd = graph.keys.count { (graph[it]?.size ?: 0) % 2 == 1 }
        return odd == 0 || odd == 2
    }

    // Returns Eulerian path/circuit if exists (list of vertices in order)
    fun eulerianTrail(graph: Map<Int, MutableList<Int>>): List<Int> {
        val degreesOdd = graph.filter { (it.value.size % 2) == 1 }.keys
        val start = degreesOdd.firstOrNull() ?: graph.keys.firstOrNull() ?: return emptyList()

        // Make a deep copy to safely remove edges while traversing
        val g = mutableMapOf<Int, MutableList<Int>>()
        for ((u, neighbors) in graph) {
            g[u] = neighbors.toMutableList()
        }

        val stack = ArrayDeque<Int>()
        val path = mutableListOf<Int>()
        stack.add(start)

        while (stack.isNotEmpty()) {
            val u = stack.last()
            val neighbors = g[u]
            if (neighbors != null && neighbors.isNotEmpty()) {
                val v = neighbors.removeAt(neighbors.lastIndex)
                g[v]?.remove(u)
                stack.addLast(v)
            } else {
                path.add(stack.removeLast())
            }
        }

        return path.reversed()
    }

    private fun isConnectedIgnoringZeroDegree(graph: Map<Int, List<Int>>): Boolean {
        val nonZero = graph.keys.firstOrNull { (graph[it]?.size ?: 0) > 0 } ?: return true
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        stack.add(nonZero)
        while (stack.isNotEmpty()) {
            val u = stack.removeLast()
            if (u in visited) continue
            visited.add(u)
            for (v in graph[u] ?: emptyList()) {
                if (v !in visited) stack.add(v)
            }
        }
        for (u in graph.keys) {
            if ((graph[u]?.size ?: 0) > 0 && u !in visited) return false
        }
        return true
    }
}


