package data_structure.maps.algorithms

/**
 * MAP GROUPING ALGORITHMS
 * 
 * Problem: Group elements by properties or characteristics efficiently.
 * 
 * Examples:
 * Input: ["apple", "banana", "apricot", "blueberry", "cherry"], key = first letter → Output: {"a": ["apple", "apricot"], "b": ["banana", "blueberry"], "c": ["cherry"]}
 * Input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], key = even/odd → Output: {true: [2, 4, 6, 8, 10], false: [1, 3, 5, 7, 9]}
 * Input: [("John", 25), ("Jane", 30), ("Bob", 25)], key = age → Output: {25: [("John", 25), ("Bob", 25)], 30: [("Jane", 30)]}
 * 
 * Intuition: Use map to group elements by computed key
 * 
 * Time Complexity: O(n) - Single pass through collection
 * Space Complexity: O(n) - All elements stored in groups
 */

object MapGrouping {
    
    /**
     * Group elements by key selector function
     */
    fun <T, K> groupBy(elements: List<T>, keySelector: (T) -> K): Map<K, List<T>> {
        return elements.groupBy(keySelector)
    }
    
    /**
     * Group elements by key and transform values
     */
    fun <T, K, V> groupByAndTransform(elements: List<T>, keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
        return elements.groupBy(keySelector).mapValues { it.value.map(valueTransform) }
    }
    
    /**
     * Group elements by first letter
     */
    fun groupByFirstLetter(words: List<String>): Map<Char, List<String>> {
        return words.groupBy { it.first() }
    }
    
    /**
     * Group elements by last letter
     */
    fun groupByLastLetter(words: List<String>): Map<Char, List<String>> {
        return words.groupBy { it.last() }
    }
    
    /**
     * Group elements by length
     */
    fun groupByLength(words: List<String>): Map<Int, List<String>> {
        return words.groupBy { it.length }
    }
    
    /**
     * Group numbers by even/odd
     */
    fun groupByEvenOdd(numbers: List<Int>): Map<Boolean, List<Int>> {
        return numbers.groupBy { it % 2 == 0 }
    }
    
    /**
     * Group numbers by range
     */
    fun groupByRange(numbers: List<Int>): Map<String, List<Int>> {
        return numbers.groupBy { 
            when {
                it < 10 -> "small"
                it < 50 -> "medium"
                else -> "large"
            }
        }
    }
    
    /**
     * Group numbers by divisibility
     */
    fun groupByDivisibility(numbers: List<Int>, divisor: Int): Map<Int, List<Int>> {
        return numbers.groupBy { it % divisor }
    }
    
    /**
     * Group by multiple criteria
     */
    fun groupByMultipleCriteria(people: List<Pair<String, Int>>): Map<String, List<Pair<String, Int>>> {
        return people.groupBy { (name, age) ->
            when {
                age < 18 -> "minor"
                age < 65 -> "adult"
                else -> "senior"
            }
        }
    }
    
    /**
     * Group by custom range
     */
    fun groupByCustomRange(numbers: List<Int>, ranges: List<IntRange>): Map<IntRange, List<Int>> {
        return numbers.groupBy { number ->
            ranges.find { number in it } ?: IntRange.EMPTY
        }
    }
    
    /**
     * Group by frequency
     */
    fun <T> groupByFrequency(elements: List<T>): Map<Int, List<T>> {
        val frequency = elements.groupingBy { it }.eachCount()
        return elements.groupBy { frequency[it]!! }
    }
    
    /**
     * Group by prefix
     */
    fun groupByPrefix(words: List<String>, prefixLength: Int): Map<String, List<String>> {
        return words.groupBy { 
            if (it.length >= prefixLength) it.substring(0, prefixLength) else it 
        }
    }
    
    /**
     * Group by suffix
     */
    fun groupBySuffix(words: List<String>, suffixLength: Int): Map<String, List<String>> {
        return words.groupBy { 
            if (it.length >= suffixLength) it.substring(it.length - suffixLength) else it 
        }
    }
    
    /**
     * Group by case sensitivity
     */
    fun groupByCase(words: List<String>): Map<Boolean, List<String>> {
        return words.groupBy { it.all { char -> char.isUpperCase() } }
    }
    
    /**
     * Group by character count
     */
    fun groupByCharacterCount(words: List<String>, character: Char): Map<Int, List<String>> {
        return words.groupBy { it.count { char -> char == character } }
    }
    
    /**
     * Group by vowel count
     */
    fun groupByVowelCount(words: List<String>): Map<Int, List<String>> {
        return words.groupBy { it.count { char -> char in "aeiouAEIOU" } }
    }
    
    /**
     * Group by palindrome property
     */
    fun groupByPalindrome(words: List<String>): Map<Boolean, List<String>> {
        return words.groupBy { it == it.reversed() }
    }
    
    /**
     * Group by anagram groups
     */
    fun groupByAnagrams(words: List<String>): Map<String, List<String>> {
        return words.groupBy { it.toCharArray().sorted().joinToString("") }
    }
    
    /**
     * Group by word pattern
     */
    fun groupByPattern(words: List<String>): Map<String, List<String>> {
        return words.groupBy { word ->
            val pattern = mutableListOf<Int>()
            val charMap = mutableMapOf<Char, Int>()
            var counter = 0
            
            for (char in word) {
                if (char !in charMap) {
                    charMap[char] = counter++
                }
                pattern.add(charMap[char]!!)
            }
            pattern.joinToString(".")
        }
    }
    
    /**
     * Group by nested property
     */
    fun groupByNestedProperty(data: List<Map<String, Any>>, propertyPath: String): Map<Any?, List<Map<String, Any>>> {
        return data.groupBy { item ->
            var value: Any? = item
            for (key in propertyPath.split(".")) {
                value = (value as? Map<*, *>)?.get(key)
            }
            value
        }
    }
    
    /**
     * Group by sliding window
     */
    fun <T> groupBySlidingWindow(elements: List<T>, windowSize: Int): Map<String, List<List<T>>> {
        val windows = mutableListOf<List<T>>()
        for (i in 0..elements.size - windowSize) {
            windows.add(elements.subList(i, i + windowSize))
        }
        return windows.groupBy { it.joinToString(",") }
    }
    
    /**
     * Group by custom function with multiple keys
     */
    fun <T> groupByMultipleKeys(elements: List<T>, keySelectors: List<(T) -> Any>): Map<List<Any>, List<T>> {
        return elements.groupBy { element ->
            keySelectors.map { it(element) }
        }
    }
} 