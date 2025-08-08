package data_structure.graphs

/**
 * BRIDGES AND ARTICULATION POINTS (Tarjan's algorithm variants)
 *
 * - Bridges (critical connections)
 * - Articulation points (cut vertices)
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
object BridgesAndArticulation {

    // ===== BRIDGES (Critical Connections) =====
    fun findBridges(graph: Map<Int, List<Int>>): List<Pair<Int, Int>> {
        val visited = mutableSetOf<Int>()
        val discoveryTime = mutableMapOf<Int, Int>()
        val lowTime = mutableMapOf<Int, Int>()
        val parent = mutableMapOf<Int, Int?>()
        val bridges = mutableListOf<Pair<Int, Int>>()

        var time = 0

        fun dfs(u: Int) {
            visited.add(u)
            time++
            discoveryTime[u] = time
            lowTime[u] = time

            for (v in graph[u] ?: emptyList()) {
                if (v !in visited) {
                    parent[v] = u
                    dfs(v)
                    lowTime[u] = minOf(lowTime[u]!!, lowTime[v]!!)
                    if (lowTime[v]!! > discoveryTime[u]!!) {
                        bridges.add(minOf(u, v) to maxOf(u, v))
                    }
                } else if (v != parent[u]) {
                    lowTime[u] = minOf(lowTime[u]!!, discoveryTime[v]!!)
                }
            }
        }

        for (node in graph.keys) {
            if (node !in visited) {
                parent[node] = null
                dfs(node)
            }
        }

        return bridges.sortedWith(compareBy({ it.first }, { it.second }))
    }

    // ===== ARTICULATION POINTS (Cut Vertices) =====
    fun findArticulationPoints(graph: Map<Int, List<Int>>): Set<Int> {
        val visited = mutableSetOf<Int>()
        val discoveryTime = mutableMapOf<Int, Int>()
        val lowTime = mutableMapOf<Int, Int>()
        val parent = mutableMapOf<Int, Int?>()
        val articulationPoints = mutableSetOf<Int>()

        var time = 0

        fun dfs(u: Int) {
            visited.add(u)
            time++
            discoveryTime[u] = time
            lowTime[u] = time
            var childCount = 0

            for (v in graph[u] ?: emptyList()) {
                if (v !in visited) {
                    parent[v] = u
                    childCount++
                    dfs(v)
                    lowTime[u] = minOf(lowTime[u]!!, lowTime[v]!!)

                    // Non-root condition
                    if (parent[u] != null && lowTime[v]!! >= discoveryTime[u]!!) {
                        articulationPoints.add(u)
                    }
                } else if (v != parent[u]) {
                    lowTime[u] = minOf(lowTime[u]!!, discoveryTime[v]!!)
                }
            }

            // Root condition
            if (parent[u] == null && childCount > 1) {
                articulationPoints.add(u)
            }
        }

        for (node in graph.keys) {
            if (node !in visited) {
                parent[node] = null
                dfs(node)
            }
        }

        return articulationPoints
    }
}


