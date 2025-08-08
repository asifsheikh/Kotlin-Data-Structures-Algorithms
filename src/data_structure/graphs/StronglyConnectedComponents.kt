package data_structure.graphs

/**
 * STRONGLY CONNECTED COMPONENTS (SCC)
 *
 * Implements:
 * - Tarjan's algorithm for SCCs
 * - Kosaraju's algorithm for SCCs
 * - Condensation DAG construction
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
object StronglyConnectedComponents {

    // ===== TARJAN'S SCC =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for stacks and index/lowlink maps
     */
    fun tarjanSCC(graph: Map<Int, List<Int>>): List<List<Int>> {
        val indexByNode = mutableMapOf<Int, Int>()
        val lowlinkByNode = mutableMapOf<Int, Int>()
        val onStack = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        val result = mutableListOf<List<Int>>()

        var currentIndex = 0

        fun strongConnect(node: Int) {
            indexByNode[node] = currentIndex
            lowlinkByNode[node] = currentIndex
            currentIndex++
            stack.addLast(node)
            onStack.add(node)

            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in indexByNode) {
                    strongConnect(neighbor)
                    lowlinkByNode[node] = minOf(lowlinkByNode[node]!!, lowlinkByNode[neighbor]!!)
                } else if (neighbor in onStack) {
                    lowlinkByNode[node] = minOf(lowlinkByNode[node]!!, indexByNode[neighbor]!!)
                }
            }

            if (lowlinkByNode[node] == indexByNode[node]) {
                val component = mutableListOf<Int>()
                while (true) {
                    val v = stack.removeLast()
                    onStack.remove(v)
                    component.add(v)
                    if (v == node) break
                }
                result.add(component)
            }
        }

        for (node in graph.keys) {
            if (node !in indexByNode) {
                strongConnect(node)
            }
        }

        return result
    }

    // ===== KOSARAJU'S SCC =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E) for reverse graph construction
     */
    fun kosarajuSCC(graph: Map<Int, List<Int>>): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val order = mutableListOf<Int>()

        fun dfs1(node: Int) {
            visited.add(node)
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) dfs1(neighbor)
            }
            order.add(node)
        }

        for (node in graph.keys) {
            if (node !in visited) dfs1(node)
        }

        // Build reverse graph
        val reverse = mutableMapOf<Int, MutableList<Int>>()
        for ((u, neighbors) in graph) {
            for (v in neighbors) {
                reverse.computeIfAbsent(v) { mutableListOf() }.add(u)
            }
            reverse.computeIfAbsent(u) { mutableListOf() }
        }

        val result = mutableListOf<List<Int>>()
        visited.clear()

        fun dfs2(node: Int, component: MutableList<Int>) {
            visited.add(node)
            component.add(node)
            for (neighbor in reverse[node] ?: emptyList()) {
                if (neighbor !in visited) dfs2(neighbor, component)
            }
        }

        for (node in order.asReversed()) {
            if (node !in visited) {
                val component = mutableListOf<Int>()
                dfs2(node, component)
                result.add(component)
            }
        }

        return result
    }

    // ===== CONDENSATION DAG (SCC DAG) =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    fun condensationDAG(graph: Map<Int, List<Int>>, sccs: List<List<Int>>): Map<Int, List<Int>> {
        val nodeToComp = mutableMapOf<Int, Int>()
        for ((compId, comp) in sccs.withIndex()) {
            for (node in comp) nodeToComp[node] = compId
        }

        val dagEdges = mutableMapOf<Int, MutableSet<Int>>()
        for ((u, neighbors) in graph) {
            val cu = nodeToComp[u] ?: continue
            for (v in neighbors) {
                val cv = nodeToComp[v] ?: continue
                if (cu != cv) {
                    dagEdges.computeIfAbsent(cu) { mutableSetOf() }.add(cv)
                }
            }
        }

        val dag = mutableMapOf<Int, List<Int>>()
        for (compId in sccs.indices) {
            dag[compId] = dagEdges[compId]?.toList() ?: emptyList()
        }
        return dag
    }
}


