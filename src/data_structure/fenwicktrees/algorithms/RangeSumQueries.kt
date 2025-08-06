package data_structure.fenwicktrees.algorithms

import data_structure.fenwicktrees.FenwickTreeCreation

/**
 * RANGE SUM QUERIES ALGORITHM
 * 
 * Problem: Efficiently handle range sum queries and point updates using Fenwick Trees.
 * 
 * Examples:
 * Input: [1, 2, 3, 4, 5], query(1, 3) → Output: 9 (2+3+4)
 * Input: [1, 2, 3, 4, 5], update(2, 10), query(1, 3) → Output: 16 (2+10+4)
 * 
 * Intuition: Use Fenwick Tree for O(log n) range queries and point updates
 * 
 * Time Complexity: O(log n) per query/update
 * Space Complexity: O(n) for the tree
 */

object RangeSumQueries {
    
    /**
     * Basic range sum query with point updates
     */
    fun rangeSumQuery(arr: LongArray, queries: List<Pair<String, List<Int>>>): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        val results = mutableListOf<Long>()
        
        for ((type, params) in queries) {
            when (type) {
                "query" -> {
                    val left = params[0]
                    val right = params[1]
                    results.add(fenwick.rangeQuery(left, right))
                }
                "update" -> {
                    val index = params[0]
                    val value = params[1].toLong()
                    fenwick.update(index, value)
                }
            }
        }
        
        return results
    }
    
    /**
     * Range sum with multiple updates
     */
    fun rangeSumWithMultipleUpdates(
        arr: LongArray, 
        updates: List<Triple<Int, Int, Long>>, 
        queries: List<Pair<Int, Int>>
    ): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        // Apply updates
        for ((index, _, delta) in updates) {
            fenwick.update(index, delta)
        }
        
        // Process queries
        return queries.map { (left, right) ->
            fenwick.rangeQuery(left, right)
        }
    }
    
    /**
     * Range sum with range updates (using difference array)
     */
    fun rangeSumWithRangeUpdates(
        arr: LongArray,
        rangeUpdates: List<Triple<Int, Int, Long>>,
        queries: List<Pair<Int, Int>>
    ): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize with difference array
        fenwick.update(0, arr[0])
        for (i in 1 until arr.size) {
            fenwick.update(i, arr[i] - arr[i - 1])
        }
        
        // Apply range updates
        for ((left, right, delta) in rangeUpdates) {
            fenwick.update(left, delta)
            if (right + 1 < arr.size) {
                fenwick.update(right + 1, -delta)
            }
        }
        
        // Process queries
        return queries.map { (left, right) ->
            fenwick.query(right) - (if (left > 0) fenwick.query(left - 1) else 0)
        }
    }
    
    /**
     * Range sum with frequency queries
     */
    fun rangeSumWithFrequency(
        arr: LongArray,
        queries: List<Triple<String, Int, Int>>
    ): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        val results = mutableListOf<Long>()
        
        for ((type, left, right) in queries) {
            when (type) {
                "sum" -> results.add(fenwick.rangeQuery(left, right))
                "frequency" -> {
                    val target = right.toLong()
                    var count = 0L
                    for (i in left..right) {
                        val element = fenwick.query(i) - fenwick.query(i - 1)
                        if (element == target) count++
                    }
                    results.add(count)
                }
            }
        }
        
        return results
    }
    
    /**
     * Range sum with conditional queries
     */
    fun rangeSumWithConditions(
        arr: LongArray,
        queries: List<Triple<String, Int, Int>>
    ): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        val results = mutableListOf<Long>()
        
        for ((condition, left, right) in queries) {
            when (condition) {
                "even" -> {
                    var sum = 0L
                    for (i in left..right) {
                        val element = fenwick.query(i) - fenwick.query(i - 1)
                        if (element % 2 == 0L) sum += element
                    }
                    results.add(sum)
                }
                "odd" -> {
                    var sum = 0L
                    for (i in left..right) {
                        val element = fenwick.query(i) - fenwick.query(i - 1)
                        if (element % 2 == 1L) sum += element
                    }
                    results.add(sum)
                }
                "positive" -> {
                    var sum = 0L
                    for (i in left..right) {
                        val element = fenwick.query(i) - fenwick.query(i - 1)
                        if (element > 0) sum += element
                    }
                    results.add(sum)
                }
            }
        }
        
        return results
    }
    
    /**
     * Range sum with sliding window
     */
    fun rangeSumSlidingWindow(arr: LongArray, windowSize: Int): List<Long> {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        val results = mutableListOf<Long>()
        
        for (i in 0..arr.size - windowSize) {
            results.add(fenwick.rangeQuery(i, i + windowSize - 1))
        }
        
        return results
    }
    
    /**
     * Range sum with binary search
     */
    fun rangeSumWithBinarySearch(arr: LongArray, targetSum: Long): Int {
        val fenwick = FenwickTreeCreation.FenwickTree(arr.size)
        
        // Initialize Fenwick tree
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = (left + right) / 2
            val prefixSum = fenwick.query(mid)
            
            if (prefixSum >= targetSum) {
                result = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        
        return result
    }
} 