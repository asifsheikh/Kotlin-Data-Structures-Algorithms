package data_structure.fenwicktrees.algorithms

import data_structure.fenwicktrees.BasicFenwickTreeCreation

/**
 * INVERSION COUNT ALGORITHM
 * 
 * Problem: Count the number of inversions in an array using Fenwick Trees.
 * 
 * Examples:
 * Input: [2, 4, 1, 3, 5] → Output: 3 (inversions: (2,1), (4,1), (4,3))
 * Input: [5, 4, 3, 2, 1] → Output: 10 (all pairs are inversions)
 * Input: [1, 2, 3, 4, 5] → Output: 0 (no inversions)
 * 
 * Intuition: Use Fenwick Tree to count elements greater than current element
 * 
 * Time Complexity: O(n log n) - n elements, log n per query
 * Space Complexity: O(n) for the tree
 */

object InversionCount {
    
    /**
     * Count inversions in array
     */
    fun countInversions(arr: IntArray): Long {
        val n = arr.size
        val fenwick = BasicFenwickTreeCreation.FenwickTree(n)
        
        // Coordinate compression
        val sorted = arr.sorted()
        val rank = mutableMapOf<Int, Int>()
        var currentRank = 0
        for (i in sorted.indices) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                rank[sorted[i]] = currentRank++
            }
        }
        
        var inversions = 0L
        
        // Count inversions from right to left
        for (i in n - 1 downTo 0) {
            val elementRank = rank[arr[i]]!!
            inversions += fenwick.query(elementRank - 1)
            fenwick.update(elementRank, 1)
        }
        
        return inversions
    }
    
    /**
     * Count inversions with duplicate elements
     */
    fun countInversionsWithDuplicates(arr: IntArray): Long {
        val n = arr.size
        val fenwick = BasicFenwickTreeCreation.FenwickTree(n)
        
        // Coordinate compression with stable sorting
        val indexed = arr.withIndex().toList()
        val sorted = indexed.sortedWith(compareBy<IndexedValue<Int>> { it.value }.thenBy { it.index })
        val rank = IntArray(n)
        for (i in sorted.indices) {
            rank[sorted[i].index] = i
        }
        
        var inversions = 0L
        
        // Count inversions from right to left
        for (i in n - 1 downTo 0) {
            inversions += fenwick.query(rank[i] - 1)
            fenwick.update(rank[i], 1)
        }
        
        return inversions
    }
    
    /**
     * Count inversions in 2D array (points)
     */
    fun countInversions2D(points: List<Pair<Int, Int>>): Long {
        val n = points.size
        val fenwick = BasicFenwickTreeCreation.FenwickTree(n)
        
        // Sort by x-coordinate, then by y-coordinate
        val sorted = points.withIndex().sortedWith(
            compareBy<IndexedValue<Pair<Int, Int>>> { it.value.first }
                .thenBy { it.value.second }
        )
        
        // Coordinate compression for y-coordinates
        val yCoords = sorted.map { it.value.second }.distinct().sorted()
        val yRank = mutableMapOf<Int, Int>()
        for (i in yCoords.indices) {
            yRank[yCoords[i]] = i
        }
        
        var inversions = 0L
        
        // Count inversions from right to left
        for (i in n - 1 downTo 0) {
            val yRankValue = yRank[sorted[i].value.second]!!
            inversions += fenwick.query(yRankValue - 1)
            fenwick.update(yRankValue, 1)
        }
        
        return inversions
    }
    
    /**
     * Count inversions with range queries
     */
    fun countInversionsInRange(arr: IntArray, left: Int, right: Int): Long {
        val subArray = arr.slice(left..right).toIntArray()
        return countInversions(subArray)
    }
    
    /**
     * Count inversions with updates
     */
    fun countInversionsWithUpdates(
        arr: IntArray, 
        updates: List<Pair<Int, Int>>
    ): List<Long> {
        val results = mutableListOf<Long>()
        val mutableArr = arr.clone()
        
        for ((index, newValue) in updates) {
            mutableArr[index] = newValue
            results.add(countInversions(mutableArr))
        }
        
        return results
    }
    
    /**
     * Count inversions for each element
     */
    fun countInversionsForEachElement(arr: IntArray): List<Long> {
        val n = arr.size
        val fenwick = BasicFenwickTreeCreation.FenwickTree(n)
        val results = mutableListOf<Long>()
        
        // Coordinate compression
        val sorted = arr.sorted()
        val rank = mutableMapOf<Int, Int>()
        var currentRank = 0
        for (i in sorted.indices) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                rank[sorted[i]] = currentRank++
            }
        }
        
        // Count inversions for each element
        for (i in 0 until n) {
            val elementRank = rank[arr[i]]!!
            val inversions = fenwick.query(elementRank - 1)
            results.add(inversions)
            fenwick.update(elementRank, 1)
        }
        
        return results
    }
    
    /**
     * Count inversions with custom comparator
     */
    fun countInversionsWithComparator(
        arr: IntArray, 
        comparator: (Int, Int) -> Int
    ): Long {
        val n = arr.size
        val fenwick = BasicFenwickTreeCreation.FenwickTree(n)
        
        // Create ranking based on custom comparator
        val indexed = arr.withIndex().toList()
        val sorted = indexed.sortedWith { a, b -> comparator(a.value, b.value) }
        val rank = IntArray(n)
        for (i in sorted.indices) {
            rank[sorted[i].index] = i
        }
        
        var inversions = 0L
        
        // Count inversions from right to left
        for (i in n - 1 downTo 0) {
            inversions += fenwick.query(rank[i] - 1)
            fenwick.update(rank[i], 1)
        }
        
        return inversions
    }
    
    /**
     * Count inversions in circular array
     */
    fun countInversionsCircular(arr: IntArray): Long {
        val n = arr.size
        var minInversions = Long.MAX_VALUE
        
        // Try all possible rotations
        for (rotation in 0 until n) {
            val rotated = IntArray(n) { arr[(it + rotation) % n] }
            val inversions = countInversions(rotated)
            minInversions = minOf(minInversions, inversions)
        }
        
        return minInversions
    }
    
    /**
     * Count inversions with sliding window
     */
    fun countInversionsSlidingWindow(arr: IntArray, windowSize: Int): List<Long> {
        val results = mutableListOf<Long>()
        
        for (i in 0..arr.size - windowSize) {
            val window = arr.slice(i until i + windowSize).toIntArray()
            results.add(countInversions(window))
        }
        
        return results
    }
} 