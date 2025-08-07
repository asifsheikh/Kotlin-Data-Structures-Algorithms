package data_structure.fenwicktrees

/**
 * FENWICK TREE ACCESS - Quick Reference
 * All Kotlin Fenwick Tree access methods in one place
 */

object FenwickTreeAccess {
    
    /**
     * All Fenwick Tree Access Methods
     * Complete reference for accessing Fenwick Trees in Kotlin
     */
    fun allFenwickTreeAccessMethods() {
        // Create Fenwick tree within function - standalone
        val fenwick = BasicFenwickTreeCreation.FenwickTree(10)
        val enhancedFenwick = EnhancedFenwickTreeCreation.EnhancedFenwickTree(10)
        
        // Initialize with some values
        for (i in 0 until 10) {
            fenwick.update(i, (i + 1).toLong())
            enhancedFenwick.set(i, (i + 1).toLong())
        }
        
        // === BASIC QUERY OPERATIONS ===
        val prefixSum0 = fenwick.query(0)                         // 1
        val prefixSum5 = fenwick.query(5)                         // 21 (1+2+3+4+5+6)
        val prefixSum9 = fenwick.query(9)                         // 55 (sum of all elements)
        val rangeSum2to5 = fenwick.rangeQuery(2, 5)               // 18 (3+4+5+6)
        val rangeSum0to9 = fenwick.rangeQuery(0, 9)               // 55 (sum of all elements)
        
        // === ENHANCED ACCESS OPERATIONS ===
        val valueAtIndex3 = enhancedFenwick.get(3)                // 4
        val enhancedPrefixSum5 = enhancedFenwick.query(5)         // 21
        val enhancedRangeSum2to5 = enhancedFenwick.rangeQuery(2, 5) // 18
        
        // === SIZE AND CAPACITY OPERATIONS ===
        val size = fenwick.getSize()                              // 10
        val enhancedSize = enhancedFenwick.getSize()              // 10
        val isEmpty = size == 0                                   // false
        val isNotEmpty = size > 0                                 // true
        
        // === RANGE ACCESS OPERATIONS ===
        val firstElement = fenwick.query(0)                       // 1
        val lastElement = fenwick.query(9) - fenwick.query(8)    // 10
        val middleElement = fenwick.query(4) - fenwick.query(3)  // 5
        
        // === CONDITIONAL ACCESS OPERATIONS ===
        val sumOfEvenIndices = (0..9 step 2).sumOf { fenwick.query(it) - fenwick.query(it - 1) } // Sum of elements at even indices
        val sumOfOddIndices = (1..9 step 2).sumOf { fenwick.query(it) - fenwick.query(it - 1) }  // Sum of elements at odd indices
        
        // === PATTERN ACCESS OPERATIONS ===
        val firstHalfSum = fenwick.query(4)                       // Sum of first 5 elements
        val secondHalfSum = fenwick.rangeQuery(5, 9)              // Sum of last 5 elements
        val firstQuarterSum = fenwick.query(2)                    // Sum of first 3 elements
        val lastQuarterSum = fenwick.rangeQuery(7, 9)             // Sum of last 3 elements
        
        // === STATISTICAL ACCESS OPERATIONS ===
        val totalSum = fenwick.query(9)                           // 55
        val average = totalSum / 10.0                             // 5.5
        val maxPossibleSum = fenwick.query(9)                     // Maximum possible sum
        
        // === INDEX-BASED ACCESS OPERATIONS ===
        val elementAt0 = fenwick.query(0)                         // 1
        val elementAt5 = fenwick.query(5) - fenwick.query(4)     // 6
        val elementAt9 = fenwick.query(9) - fenwick.query(8)     // 10
        
        // === RANGE VALIDATION ACCESS ===
        val isValidRange0to5 = 0 <= 0 && 5 <= 9                  // true
        val isValidRange5to15 = 5 <= 5 && 15 <= 9                // false
        val isValidIndex5 = 0 <= 5 && 5 <= 9                     // true
        val isValidIndex15 = 0 <= 15 && 15 <= 9                  // false
        
        // === ITERATION ACCESS OPERATIONS ===
        for (i in 0 until fenwick.getSize()) {
            val element = fenwick.query(i) - fenwick.query(i - 1) // Access each element
        }
        
        for (i in 0 until fenwick.getSize()) {
            val prefixSum = fenwick.query(i)                      // Access each prefix sum
        }
        
        // === CONDITIONAL RANGE ACCESS ===
        val sumIfPositive = fenwick.query(9)                      // All elements are positive
        val sumOfLargeElements = (0..9).filter { 
            fenwick.query(it) - fenwick.query(it - 1) > 5 
        }.sumOf { fenwick.query(it) - fenwick.query(it - 1) }    // Sum of elements > 5
        
        // === MULTIPLE RANGE ACCESS ===
        val ranges = listOf(0..2, 3..5, 6..8, 9..9)
        val rangeSums = ranges.map { range ->
            fenwick.rangeQuery(range.first, range.last)
        }                                                        // [6, 15, 24, 10]
        
        // === SLIDING WINDOW ACCESS ===
        val windowSize = 3
        val slidingWindowSums = (0..9-windowSize+1).map { start ->
            fenwick.rangeQuery(start, start + windowSize - 1)
        }                                                        // [6, 9, 12, 15, 18, 21, 24, 27]
        
        // === PREFIX SUMS ARRAY ACCESS ===
        val prefixSums = (0..9).map { fenwick.query(it) }        // [1, 3, 6, 10, 15, 21, 28, 36, 45, 55]
        val suffixSums = (0..9).map { fenwick.rangeQuery(it, 9) } // [55, 54, 52, 49, 45, 40, 34, 27, 19, 10]
        
        // === BINARY SEARCH ACCESS ===
        val targetSum = 25L
        val binarySearchResult = binarySearchPrefixSum(fenwick, targetSum) // Find index where prefix sum >= targetSum
        
        // === KTH ELEMENT ACCESS ===
        val kthElement = findKthElement(fenwick, 5)               // Find 5th element (1-indexed)
        val kthLargest = findKthLargest(fenwick, 3)               // Find 3rd largest element
        
        // === FREQUENCY ACCESS ===
        val frequencyMap = mutableMapOf<Long, Int>()
        for (i in 0 until fenwick.getSize()) {
            val element = fenwick.query(i) - fenwick.query(i - 1)
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }                                                        // Frequency count of each element
        
        // === MEDIAN ACCESS ===
        val median = findMedian(fenwick)                          // Find median of all elements
        
        // === MODE ACCESS ===
        val mode = findMode(fenwick)                              // Find most frequent element
    }
    
    // Helper methods for advanced access operations
    private fun binarySearchPrefixSum(fenwick: BasicFenwickTreeCreation.FenwickTree, targetSum: Long): Int {
        var left = 0
        var right = fenwick.getSize() - 1
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
    
    private fun findKthElement(fenwick: BasicFenwickTreeCreation.FenwickTree, k: Int): Long {
        if (k < 1 || k > fenwick.getSize()) return -1L
        return fenwick.query(k - 1) - fenwick.query(k - 2)
    }
    
    private fun findKthLargest(fenwick: BasicFenwickTreeCreation.FenwickTree, k: Int): Long {
        // This is a simplified version - in practice, you'd need a more complex approach
        val elements = mutableListOf<Long>()
        for (i in 0 until fenwick.getSize()) {
            elements.add(fenwick.query(i) - fenwick.query(i - 1))
        }
        return elements.sortedDescending()[k - 1]
    }
    
    private fun findMedian(fenwick: BasicFenwickTreeCreation.FenwickTree): Double {
        val size = fenwick.getSize()
        val totalSum = fenwick.query(size - 1)
        return totalSum / size.toDouble()
    }
    
    private fun findMode(fenwick: BasicFenwickTreeCreation.FenwickTree): Long {
        val frequencyMap = mutableMapOf<Long, Int>()
        for (i in 0 until fenwick.getSize()) {
            val element = fenwick.query(i) - fenwick.query(i - 1)
            frequencyMap[element] = frequencyMap.getOrDefault(element, 0) + 1
        }
        return frequencyMap.maxByOrNull { it.value }?.key ?: -1L
    }
} 