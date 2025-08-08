package data_structure.graphs

/**
 * MAX FLOW (Edmonds–Karp; BFS-based augmenting paths)
 *
 * Time Complexity: O(V * E^2)
 * Space Complexity: O(V + E)
 */
object MaxFlow {

    // Graph represented as capacity matrix (u -> v -> capacity)
    fun edmondsKarp(capacity: Array<IntArray>, source: Int, sink: Int): Int {
        val n = capacity.size
        val residual = Array(n) { IntArray(n) }
        // Initialize residual graph
        for (i in 0 until n) {
            for (j in 0 until n) {
                residual[i][j] = capacity[i][j]
            }
        }

        var maxFlow = 0
        val parent = IntArray(n) { -1 }

        fun bfs(): Boolean {
            java.util.Arrays.fill(parent, -1)
            parent[source] = -2 // Mark the source as visited with special value
            val queue = ArrayDeque<Pair<Int, Int>>() // (node, flow)
            queue.add(source to Int.MAX_VALUE)

            while (queue.isNotEmpty()) {
                val (u, flow) = queue.removeFirst()
                for (v in 0 until n) {
                    if (parent[v] == -1 && residual[u][v] > 0) {
                        parent[v] = u
                        val newFlow = minOf(flow, residual[u][v])
                        if (v == sink) {
                            // Found augmenting path
                            var cur = v
                            var prev = parent[cur]
                            while (prev != -2) {
                                residual[prev][cur] -= newFlow
                                residual[cur][prev] += newFlow
                                cur = prev
                                prev = parent[cur]
                            }
                            maxFlow += newFlow
                            return true
                        }
                        queue.add(v to newFlow)
                    }
                }
            }
            return false
        }

        while (bfs()) { /* keep augmenting */ }
        return maxFlow
    }
}


