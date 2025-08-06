package data_structure.fenwicktrees.algorithms

import data_structure.fenwicktrees.FenwickTreeCreation

/**
 * FREQUENCY QUERIES ALGORITHM
 * 
 * Problem: Handle frequency-based queries using Fenwick Trees for efficient counting.
 * 
 * Examples:
 * Input: [1, 2, 1, 3, 2, 1], query(1) → Output: 3 (frequency of 1)
 * Input: [1, 2, 1, 3, 2, 1], query(2) → Output: 2 (frequency of 2)
 * Input: [1, 2, 1, 3, 2, 1], query(3) → Output: 1 (frequency of 3)
 * 
 * Intuition: Use Fenwick Tree to maintain frequency counts for efficient queries
 * 
 * Time Complexity: O(log n) per query/update
 * Space Complexity: O(n) for the tree
 */

object FrequencyQueries {
    
    /**
     * Basic frequency query
     */
    fun frequencyQuery(arr: IntArray, queries: List<Int>): List<Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        // Process queries
        return queries.map { element ->
            if (element <= maxElement) {
                fenwick.query(element) - fenwick.query(element - 1)
            } else {
                0
            }
        }
    }
    
    /**
     * Frequency query with updates
     */
    fun frequencyQueryWithUpdates(
        arr: IntArray, 
        operations: List<Pair<String, Int>>
    ): List<Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        val frequency = mutableMapOf<Int, Int>()
        
        // Initialize frequencies
        for (element in arr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
            fenwick.update(element, 1)
        }
        
        val results = mutableListOf<Int>()
        
        for ((operation, value) in operations) {
            when (operation) {
                "add" -> {
                    frequency[value] = frequency.getOrDefault(value, 0) + 1
                    fenwick.update(value, 1)
                }
                "remove" -> {
                    if (frequency.getOrDefault(value, 0) > 0) {
                        frequency[value] = frequency[value]!! - 1
                        fenwick.update(value, -1)
                    }
                }
                "query" -> {
                    results.add(frequency.getOrDefault(value, 0))
                }
            }
        }
        
        return results
    }
    
    /**
     * Range frequency query
     */
    fun rangeFrequencyQuery(
        arr: IntArray, 
        queries: List<Triple<Int, Int, Int>>
    ): List<Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        val results = mutableListOf<Int>()
        
        for ((left, right, target) in queries) {
            // Reset Fenwick tree for each query
            val tempFenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
            
            // Count frequencies in range
            for (i in left..right) {
                tempFenwick.update(arr[i], 1)
            }
            
            results.add(tempFenwick.query(target) - tempFenwick.query(target - 1))
        }
        
        return results
    }
    
    /**
     * Most frequent element query
     */
    fun mostFrequentElement(arr: IntArray): Int {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        var maxFreq = 0
        var mostFrequent = -1
        
        for (element in 0..maxElement) {
            val freq = fenwick.query(element) - fenwick.query(element - 1)
            if (freq > maxFreq) {
                maxFreq = freq
                mostFrequent = element
            }
        }
        
        return mostFrequent
    }
    
    /**
     * K-th most frequent element
     */
    fun kthMostFrequent(arr: IntArray, k: Int): Int {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        // Create list of (element, frequency) pairs
        val frequencies = mutableListOf<Pair<Int, Int>>()
        for (element in 0..maxElement) {
            val freq = fenwick.query(element) - fenwick.query(element - 1)
            if (freq > 0) {
                frequencies.add(element to freq)
            }
        }
        
        // Sort by frequency (descending)
        frequencies.sortByDescending { it.second }
        
        return if (k <= frequencies.size) frequencies[k - 1].first else -1
    }
    
    /**
     * Elements with frequency greater than threshold
     */
    fun elementsWithFrequencyGreaterThan(arr: IntArray, threshold: Int): List<Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        val result = mutableListOf<Int>()
        
        for (element in 0..maxElement) {
            val freq = fenwick.query(element) - fenwick.query(element - 1)
            if (freq > threshold) {
                result.add(element)
            }
        }
        
        return result
    }
    
    /**
     * Frequency histogram
     */
    fun frequencyHistogram(arr: IntArray): Map<Int, Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        val histogram = mutableMapOf<Int, Int>()
        
        for (element in 0..maxElement) {
            val freq = fenwick.query(element) - fenwick.query(element - 1)
            if (freq > 0) {
                histogram[element] = freq
            }
        }
        
        return histogram
    }
    
    /**
     * Frequency with sliding window
     */
    fun frequencySlidingWindow(arr: IntArray, windowSize: Int): List<Map<Int, Int>> {
        val results = mutableListOf<Map<Int, Int>>()
        
        for (i in 0..arr.size - windowSize) {
            val window = arr.slice(i until i + windowSize)
            val maxElement = window.maxOrNull() ?: 0
            val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
            
            // Count frequencies in window
            for (element in window) {
                fenwick.update(element, 1)
            }
            
            val histogram = mutableMapOf<Int, Int>()
            for (element in 0..maxElement) {
                val freq = fenwick.query(element) - fenwick.query(element - 1)
                if (freq > 0) {
                    histogram[element] = freq
                }
            }
            
            results.add(histogram)
        }
        
        return results
    }
    
    /**
     * Frequency with updates and queries
     */
    fun frequencyWithUpdatesAndQueries(
        initialArr: IntArray,
        operations: List<Triple<String, Int, Int>>
    ): List<Int> {
        val maxElement = initialArr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        val frequency = mutableMapOf<Int, Int>()
        
        // Initialize frequencies
        for (element in initialArr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
            fenwick.update(element, 1)
        }
        
        val results = mutableListOf<Int>()
        
        for ((operation, value1, value2) in operations) {
            when (operation) {
                "add" -> {
                    frequency[value1] = frequency.getOrDefault(value1, 0) + 1
                    fenwick.update(value1, 1)
                }
                "remove" -> {
                    if (frequency.getOrDefault(value1, 0) > 0) {
                        frequency[value1] = frequency[value1]!! - 1
                        fenwick.update(value1, -1)
                    }
                }
                "query" -> {
                    results.add(frequency.getOrDefault(value1, 0))
                }
                "range_query" -> {
                    var count = 0
                    for (element in value1..value2) {
                        count += frequency.getOrDefault(element, 0)
                    }
                    results.add(count)
                }
            }
        }
        
        return results
    }
    
    /**
     * Frequency with custom comparator
     */
    fun frequencyWithCustomComparator(
        arr: IntArray,
        comparator: (Int, Int) -> Int
    ): Map<Int, Int> {
        val maxElement = arr.maxOrNull() ?: 0
        val fenwick = FenwickTreeCreation.FenwickTree(maxElement + 1)
        
        // Count frequencies
        for (element in arr) {
            fenwick.update(element, 1)
        }
        
        val histogram = mutableMapOf<Int, Int>()
        
        for (element in 0..maxElement) {
            val freq = fenwick.query(element) - fenwick.query(element - 1)
            if (freq > 0) {
                histogram[element] = freq
            }
        }
        
        return histogram.toList().sortedWith { a, b -> comparator(a.first, b.first) }.toMap()
    }
} 