package data_structure.arrays

/**
 * ARRAY STATISTICS ALGORITHMS
 * 
 * Problem: Calculate various statistical measures and properties of arrays.
 * 
 * Different statistics:
 * 1. Count occurrences of elements
 * 2. Find most frequent element
 * 3. Find unique elements
 * 4. Find missing numbers
 * 5. Calculate frequency distributions
 * 6. Find statistical measures (mean, median, mode)
 * 
 * Example:
 * Array: [1, 2, 2, 3, 3, 3, 4, 5]
 * Count of 3: 3
 * Most frequent: 3
 * Unique elements: [1, 2, 3, 4, 5]
 * Missing numbers (1-6): [6]
 * 
 * Intuition:
 * - Use hash maps for frequency counting
 * - Use sets for unique elements
 * - Use mathematical formulas for missing numbers
 * - Sort for median calculation
 * - Use mathematical properties for optimization
 */

object ArrayStatistics {
    
    /**
     * Count Occurrences
     * 
     * Problem: Count how many times a specific value appears in array
     * 
     * Algorithm:
     * 1. Traverse array and count matches
     * 2. Return total count
     * 
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(1) - Constant extra space
     */
    fun countOccurrences(arr: IntArray, value: Int): Int {
        return arr.count { it == value }
    }
    
    /**
     * Count All Occurrences
     * 
     * Problem: Count occurrences of all elements in array
     * 
     * Algorithm:
     * 1. Use hash map to store frequency of each element
     * 2. Traverse array and increment counts
     * 3. Return frequency map
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(k) - k unique elements
     */
    fun countAllOccurrences(arr: IntArray): Map<Int, Int> {
        val frequency = mutableMapOf<Int, Int>()
        for (element in arr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
        }
        return frequency
    }
    
    /**
     * Find Most Frequent Element
     * 
     * Problem: Find element that appears most frequently in array
     * 
     * Algorithm:
     * 1. Count frequency of all elements
     * 2. Find element with maximum frequency
     * 3. Handle ties (return first occurrence)
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(k) - k unique elements
     */
    fun findMostFrequent(arr: IntArray): Int? {
        if (arr.isEmpty()) return null
        
        val frequency = mutableMapOf<Int, Int>()
        for (element in arr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
        }
        
        return frequency.maxByOrNull { it.value }?.key
    }
    
    /**
     * Find All Most Frequent Elements
     * 
     * Problem: Find all elements that appear with maximum frequency
     * 
     * Algorithm:
     * 1. Count frequency of all elements
     * 2. Find maximum frequency
     * 3. Return all elements with that frequency
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    fun findAllMostFrequent(arr: IntArray): List<Int> {
        if (arr.isEmpty()) return emptyList()
        
        val frequency = mutableMapOf<Int, Int>()
        for (element in arr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
        }
        
        val maxFreq = frequency.values.maxOrNull()!!
        return frequency.filter { it.value == maxFreq }.keys.toList()
    }
    
    /**
     * Find Unique Elements
     * 
     * Problem: Find all unique elements in array
     * 
     * Algorithm:
     * 1. Use set to store unique elements
     * 2. Add all elements to set
     * 3. Convert set to array
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(k) - k unique elements
     */
    fun findUniqueElements(arr: IntArray): IntArray {
        return arr.toSet().toIntArray()
    }
    
    /**
     * Find Missing Numbers
     * 
     * Problem: Find missing numbers in range from min to max
     * 
     * Algorithm:
     * 1. Convert array to set for O(1) lookup
     * 2. Check each number in range
     * 3. Add missing numbers to result
     * 
     * Time Complexity: O(max - min + 1) - We check each number in range
     * Space Complexity: O(n) - Set to store array elements
     */
    fun findMissingNumbers(arr: IntArray, min: Int, max: Int): IntArray {
        val present = arr.toSet()
        return (min..max).filter { it !in present }.toIntArray()
    }
    
    /**
     * Find Missing Numbers (Optimized for consecutive range)
     * 
     * Problem: Find missing numbers when array should contain consecutive numbers
     * 
     * Algorithm:
     * 1. Calculate expected sum: n * (n + 1) / 2
     * 2. Calculate actual sum
     * 3. Missing numbers = expected - actual
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findMissingNumbersOptimized(arr: IntArray, expectedSize: Int): Int {
        val expectedSum = expectedSize * (expectedSize + 1) / 2
        val actualSum = arr.sum()
        return expectedSum - actualSum
    }
    
    /**
     * Calculate Mean (Average)
     * 
     * Problem: Calculate arithmetic mean of array elements
     * 
     * Algorithm:
     * 1. Sum all elements
     * 2. Divide by number of elements
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun calculateMean(arr: IntArray): Double {
        if (arr.isEmpty()) return 0.0
        return arr.sum().toDouble() / arr.size
    }
    
    /**
     * Calculate Median
     * 
     * Problem: Find middle value of sorted array
     * 
     * Algorithm:
     * 1. Sort array
     * 2. If odd length: return middle element
     * 3. If even length: return average of two middle elements
     * 
     * Time Complexity: O(n log n) - Due to sorting
     * Space Complexity: O(1) - In-place sorting
     */
    fun calculateMedian(arr: IntArray): Double {
        if (arr.isEmpty()) return 0.0
        
        val sorted = arr.sorted()
        val n = sorted.size
        
        return if (n % 2 == 0) {
            (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0
        } else {
            sorted[n / 2].toDouble()
        }
    }
    
    /**
     * Calculate Mode
     * 
     * Problem: Find most frequent element (statistical mode)
     * 
     * Algorithm:
     * 1. Count frequency of all elements
     * 2. Find element with maximum frequency
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    fun calculateMode(arr: IntArray): Int? {
        return findMostFrequent(arr)
    }
    
    /**
     * Calculate Variance
     * 
     * Problem: Calculate variance of array elements
     * 
     * Algorithm:
     * 1. Calculate mean
     * 2. Calculate squared differences from mean
     * 3. Calculate average of squared differences
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun calculateVariance(arr: IntArray): Double {
        if (arr.isEmpty()) return 0.0
        
        val mean = calculateMean(arr)
        val squaredDifferences = arr.map { (it - mean) * (it - mean) }
        return squaredDifferences.average()
    }
    
    /**
     * Calculate Standard Deviation
     * 
     * Problem: Calculate standard deviation of array elements
     * 
     * Algorithm:
     * 1. Calculate variance
     * 2. Take square root of variance
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun calculateStandardDeviation(arr: IntArray): Double {
        return kotlin.math.sqrt(calculateVariance(arr))
    }
    
    /**
     * Find Range
     * 
     * Problem: Find difference between maximum and minimum values
     * 
     * Algorithm:
     * 1. Find min and max values
     * 2. Return max - min
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findRange(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        return arr.maxOrNull()!! - arr.minOrNull()!!
    }
    
    /**
     * Find Quartiles
     * 
     * Problem: Find 25th, 50th (median), and 75th percentiles
     * 
     * Algorithm:
     * 1. Sort array
     * 2. Calculate positions for Q1, Q2, Q3
     * 3. Handle interpolation for non-integer positions
     * 
     * Time Complexity: O(n log n) - Due to sorting
     * Space Complexity: O(1)
     */
    fun findQuartiles(arr: IntArray): Triple<Double, Double, Double> {
        if (arr.isEmpty()) return Triple(0.0, 0.0, 0.0)
        
        val sorted = arr.sorted()
        val n = sorted.size
        
        val q1 = findPercentile(sorted, 0.25)
        val q2 = findPercentile(sorted, 0.50)
        val q3 = findPercentile(sorted, 0.75)
        
        return Triple(q1, q2, q3)
    }
    
    private fun findPercentile(sorted: List<Int>, percentile: Double): Double {
        val n = sorted.size
        val position = percentile * (n - 1)
        val lowerIndex = position.toInt()
        val upperIndex = (lowerIndex + 1).coerceAtMost(n - 1)
        
        if (lowerIndex == upperIndex) {
            return sorted[lowerIndex].toDouble()
        }
        
        val weight = position - lowerIndex
        return sorted[lowerIndex] * (1 - weight) + sorted[upperIndex] * weight
    }
    
    /**
     * Find Frequency Distribution
     * 
     * Problem: Create frequency distribution table
     * 
     * Algorithm:
     * 1. Count frequency of each element
     * 2. Sort by frequency (descending)
     * 3. Return sorted frequency map
     * 
     * Time Complexity: O(n log n) - Due to sorting
     * Space Complexity: O(k)
     */
    fun findFrequencyDistribution(arr: IntArray): List<Pair<Int, Int>> {
        val frequency = countAllOccurrences(arr)
        return frequency.toList().sortedByDescending { it.second }
    }
    
    /**
     * Find Elements with Frequency K
     * 
     * Problem: Find all elements that appear exactly k times
     * 
     * Algorithm:
     * 1. Count frequency of all elements
     * 2. Filter elements with frequency k
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    fun findElementsWithFrequency(arr: IntArray, k: Int): IntArray {
        val frequency = countAllOccurrences(arr)
        return frequency.filter { it.value == k }.keys.toIntArray()
    }
    
    /**
     * Calculate Sum of All Subarrays
     * 
     * Problem: Calculate sum of all possible subarrays
     * 
     * Algorithm:
     * 1. Use contribution technique
     * 2. Each element contributes to (i+1)*(n-i) subarrays
     * 3. Sum = Σ(arr[i] * (i+1) * (n-i))
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun calculateSumOfAllSubarrays(arr: IntArray): Long {
        var sum = 0L
        val n = arr.size
        
        for (i in arr.indices) {
            sum += arr[i].toLong() * (i + 1) * (n - i)
        }
        
        return sum
    }
} 