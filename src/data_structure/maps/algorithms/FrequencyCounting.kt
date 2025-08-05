package data_structure.maps.algorithms

/**
 * FREQUENCY COUNTING ALGORITHMS
 * 
 * Problem: Count occurrences of elements in collections efficiently.
 * 
 * Examples:
 * Input: ["apple", "banana", "apple", "cherry", "banana", "apple"] → Output: {"apple": 3, "banana": 2, "cherry": 1}
 * Input: [1, 2, 2, 3, 2, 1, 4] → Output: {1: 2, 2: 3, 3: 1, 4: 1}
 * Input: "hello" → Output: {'h': 1, 'e': 1, 'l': 2, 'o': 1}
 * 
 * Intuition: Use map to store element -> count mapping
 * 
 * Time Complexity: O(n) - Single pass through collection
 * Space Complexity: O(k) - k unique elements
 */

object FrequencyCounting {
    
    /**
     * Count frequency of elements in list
     */
    fun <T> countFrequency(elements: List<T>): Map<T, Int> {
        return elements.groupingBy { it }.eachCount()
    }
    
    /**
     * Count frequency using manual approach
     */
    fun <T> countFrequencyManual(elements: List<T>): Map<T, Int> {
        val frequency = mutableMapOf<T, Int>()
        
        for (element in elements) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
        }
        
        return frequency
    }
    
    /**
     * Count frequency of characters in string
     */
    fun countCharacterFrequency(str: String): Map<Char, Int> {
        return str.groupingBy { it }.eachCount()
    }
    
    /**
     * Count frequency of words in string
     */
    fun countWordFrequency(text: String): Map<String, Int> {
        return text.split("\\s+".toRegex()).groupingBy { it }.eachCount()
    }
    
    /**
     * Find most frequent element
     */
    fun <T> findMostFrequent(elements: List<T>): T? {
        val frequency = countFrequency(elements)
        return frequency.maxByOrNull { it.value }?.key
    }
    
    /**
     * Find k most frequent elements
     */
    fun <T> findKMostFrequent(elements: List<T>, k: Int): List<T> {
        val frequency = countFrequency(elements)
        return frequency.entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
    }
    
    /**
     * Find least frequent element
     */
    fun <T> findLeastFrequent(elements: List<T>): T? {
        val frequency = countFrequency(elements)
        return frequency.minByOrNull { it.value }?.key
    }
    
    /**
     * Find elements with frequency equal to k
     */
    fun <T> findElementsWithFrequency(elements: List<T>, k: Int): List<T> {
        val frequency = countFrequency(elements)
        return frequency.filterValues { it == k }.keys.toList()
    }
    
    /**
     * Find first non-repeating element
     */
    fun <T> findFirstNonRepeating(elements: List<T>): T? {
        val frequency = countFrequency(elements)
        return elements.find { frequency[it] == 1 }
    }
    
    /**
     * Find all non-repeating elements
     */
    fun <T> findAllNonRepeating(elements: List<T>): List<T> {
        val frequency = countFrequency(elements)
        return elements.filter { frequency[it] == 1 }
    }
    
    /**
     * Find elements that appear more than once
     */
    fun <T> findDuplicates(elements: List<T>): List<T> {
        val frequency = countFrequency(elements)
        return frequency.filterValues { it > 1 }.keys.toList()
    }
    
    /**
     * Check if all elements have same frequency
     */
    fun <T> hasSameFrequency(elements: List<T>): Boolean {
        if (elements.isEmpty()) return true
        val frequency = countFrequency(elements)
        val firstFreq = frequency.values.first()
        return frequency.values.all { it == firstFreq }
    }
    
    /**
     * Count frequency with custom grouping
     */
    fun <T, K> countFrequencyBy(elements: List<T>, keySelector: (T) -> K): Map<K, Int> {
        return elements.groupingBy(keySelector).eachCount()
    }
    
    /**
     * Count frequency of substrings
     */
    fun countSubstringFrequency(str: String, length: Int): Map<String, Int> {
        val substrings = mutableListOf<String>()
        for (i in 0..str.length - length) {
            substrings.add(str.substring(i, i + length))
        }
        return countFrequency(substrings)
    }
    
    /**
     * Count frequency with case-insensitive grouping
     */
    fun countCaseInsensitiveFrequency(words: List<String>): Map<String, Int> {
        return words.groupingBy { it.lowercase() }.eachCount()
    }
    
    /**
     * Count frequency with value transformation
     */
    fun <T, R> countFrequencyWithTransform(elements: List<T>, transform: (T) -> R): Map<R, Int> {
        return elements.groupingBy(transform).eachCount()
    }
    
    /**
     * Count frequency of pairs
     */
    fun <T> countPairFrequency(elements: List<T>): Map<Pair<T, T>, Int> {
        val pairs = mutableListOf<Pair<T, T>>()
        for (i in 0 until elements.size - 1) {
            pairs.add(elements[i] to elements[i + 1])
        }
        return countFrequency(pairs)
    }
    
    /**
     * Count frequency with sliding window
     */
    fun <T> countSlidingWindowFrequency(elements: List<T>, windowSize: Int): List<Map<T, Int>> {
        val result = mutableListOf<Map<T, Int>>()
        for (i in 0..elements.size - windowSize) {
            val window = elements.subList(i, i + windowSize)
            result.add(countFrequency(window))
        }
        return result
    }
} 