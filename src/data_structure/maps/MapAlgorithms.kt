package data_structure.maps

/**
 * MAP ALGORITHMS
 * 
 * This file contains algorithms that use Maps for efficient problem solving
 * including frequency counting, grouping, caching, and other map-based algorithms.
 * 
 * COMMON MAP ALGORITHMS:
 * - Frequency Counting: Count occurrences of elements
 * - Grouping: Group elements by properties
 * - Caching: Store computed results for reuse
 * - Memoization: Cache function results
 * - Deduplication: Remove duplicates efficiently
 * - Intersection/Union: Set operations using maps
 */

object MapAlgorithms {
    
    /**
     * PROBLEM: Count Frequency of Elements
     * 
     * Given a list of elements, count how many times each element appears.
     * 
     * INPUT: A list of elements (can be any type)
     * OUTPUT: A map where keys are elements and values are their frequencies
     * 
     * EXAMPLES:
     * Input: ["apple", "banana", "apple", "cherry", "banana", "apple"]
     * Output: {"apple": 3, "banana": 2, "cherry": 1}
     * 
     * Input: [1, 2, 2, 3, 2, 1, 4]
     * Output: {1: 2, 2: 3, 3: 1, 4: 1}
     * 
     * INTUITION:
     * - Use a map to store element -> count mapping
     * - Iterate through the list once
     * - For each element, increment its count in the map
     * - If element doesn't exist, initialize count to 1
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements to count
     * @return Map with element frequencies
     */
    fun <T> countFrequency(elements: List<T>): Map<T, Int> {
        return elements.groupingBy { it }.eachCount()
    }
    
    /**
     * PROBLEM: Find First Non-Repeating Element
     * 
     * Given a list of elements, find the first element that appears exactly once.
     * 
     * INPUT: A list of elements
     * OUTPUT: The first element that appears only once, or null if none exists
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 2, 1, 4, 5]
     * Output: 3 (first element appearing only once)
     * 
     * Input: ["a", "b", "a", "c", "b"]
     * Output: "c" (only element appearing once)
     * 
     * Input: [1, 1, 2, 2, 3, 3]
     * Output: null (no element appears only once)
     * 
     * INTUITION:
     * - First, count frequency of all elements
     * - Then iterate through the original list in order
     * - Return the first element with frequency = 1
     * - This preserves the original order requirement
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements
     * @return First non-repeating element or null
     */
    fun <T> findFirstNonRepeating(elements: List<T>): T? {
        val frequency = countFrequency(elements)
        return elements.find { frequency[it] == 1 }
    }
    
    /**
     * PROBLEM: Group Elements by Property
     * 
     * Given a list of elements, group them by a specific property or characteristic.
     * 
     * INPUT: A list of elements and a function to extract the grouping key
     * OUTPUT: A map where keys are the grouping property and values are lists of elements
     * 
     * EXAMPLES:
     * Input: ["apple", "banana", "apricot", "blueberry", "cherry"], key = first letter
     * Output: {"a": ["apple", "apricot"], "b": ["banana", "blueberry"], "c": ["cherry"]}
     * 
     * Input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], key = even/odd
     * Output: {true: [2, 4, 6, 8, 10], false: [1, 3, 5, 7, 9]}
     * 
     * INTUITION:
     * - Use groupBy() function which creates a map automatically
     * - Each element is processed by the key selector function
     * - Elements with the same key are grouped together
     * - Result is a map of key -> list of elements
     * 
     * TIME COMPLEXITY: O(n) - n elements to group
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements List of elements to group
     * @param keySelector Function to extract grouping key
     * @return Map with grouped elements
     */
    fun <T, K> groupByProperty(elements: List<T>, keySelector: (T) -> K): Map<K, List<T>> {
        return elements.groupBy(keySelector)
    }
    
    /**
     * PROBLEM: Find Majority Element
     * 
     * Given a list of elements, find an element that appears more than n/2 times.
     * 
     * INPUT: A list of elements
     * OUTPUT: The majority element or null if none exists
     * 
     * EXAMPLES:
     * Input: [1, 2, 1, 1, 3, 1, 4, 1]
     * Output: 1 (appears 5 times, more than 8/2 = 4)
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: null (no element appears more than 5/2 = 2.5 times)
     * 
     * Input: ["a", "b", "a", "a", "c"]
     * Output: "a" (appears 3 times, more than 5/2 = 2.5)
     * 
     * INTUITION:
     * - Count frequency of all elements
     * - Calculate threshold as n/2
     * - Find element with count > threshold
     * - If no such element exists, return null
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements
     * @return Majority element or null
     */
    fun <T> findMajorityElement(elements: List<T>): T? {
        val frequency = countFrequency(elements)
        val threshold = elements.size / 2
        return frequency.entries.find { it.value > threshold }?.key
    }
    
    /**
     * PROBLEM: Find Elements with Frequency Above Threshold
     * 
     * Given a list of elements and a threshold k, find all elements that appear more than k times.
     * 
     * INPUT: A list of elements and a minimum frequency threshold k
     * OUTPUT: List of elements appearing more than k times
     * 
     * EXAMPLES:
     * Input: [1, 2, 2, 3, 2, 1, 4, 4, 4], k = 2
     * Output: [2, 4] (2 appears 3 times, 4 appears 3 times)
     * 
     * Input: ["a", "b", "a", "c", "b", "a"], k = 1
     * Output: ["a", "b"] (both appear more than once)
     * 
     * Input: [1, 2, 3, 4, 5], k = 2
     * Output: [] (no element appears more than twice)
     * 
     * INTUITION:
     * - Count frequency of all elements
     * - Filter elements with count > k
     * - Return list of qualifying elements
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements
     * @param k Minimum frequency threshold
     * @return List of elements appearing more than k times
     */
    fun <T> findElementsWithFrequency(elements: List<T>, k: Int): List<T> {
        val frequency = countFrequency(elements)
        return frequency.entries.filter { it.value > k }.map { it.key }
    }
    
    /**
     * PROBLEM: Check if Two Strings are Anagrams
     * 
     * Given two strings, determine if they are anagrams (contain the same characters with same frequency).
     * 
     * INPUT: Two strings
     * OUTPUT: true if strings are anagrams, false otherwise
     * 
     * EXAMPLES:
     * Input: "listen", "silent"
     * Output: true (both contain same characters with same frequency)
     * 
     * Input: "hello", "world"
     * Output: false (different character frequencies)
     * 
     * Input: "anagram", "nagaram"
     * Output: true
     * 
     * Input: "rat", "car"
     * Output: false
     * 
     * INTUITION:
     * - First check if lengths are equal (anagrams must have same length)
     * - Count frequency of characters in both strings
     * - Compare the frequency maps
     * - If maps are identical, strings are anagrams
     * 
     * TIME COMPLEXITY: O(n) - n characters to process
     * SPACE COMPLEXITY: O(k) - k unique characters
     * 
     * @param str1 First string
     * @param str2 Second string
     * @return true if strings are anagrams
     */
    fun areAnagrams(str1: String, str2: String): Boolean {
        if (str1.length != str2.length) return false
        val freq1 = countFrequency(str1.toList())
        val freq2 = countFrequency(str2.toList())
        return freq1 == freq2
    }
    
    /**
     * PROBLEM: Find All Anagram Groups
     * 
     * Given a list of strings, group them by anagrams (strings with same character frequency).
     * 
     * INPUT: A list of strings
     * OUTPUT: List of groups, where each group contains anagrams
     * 
     * EXAMPLES:
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
     * 
     * Input: ["abc", "cba", "bac", "def", "fed"]
     * Output: [["abc", "cba", "bac"], ["def", "fed"]]
     * 
     * INTUITION:
     * - Group strings by their sorted character representation
     * - Anagrams will have the same sorted representation
     * - Use groupBy() with sorted character string as key
     * - Filter groups with more than one string
     * 
     * TIME COMPLEXITY: O(n * m log m) - n strings, m max string length
     * SPACE COMPLEXITY: O(n * m) - n strings, m max string length
     * 
     * @param strings List of strings
     * @return List of anagram groups
     */
    fun findAnagramGroups(strings: List<String>): List<List<String>> {
        return strings.groupBy { it.toCharArray().sorted().joinToString("") }
            .values.filter { it.size > 1 }
    }
    
    /**
     * PROBLEM: Longest Substring Without Repeating Characters
     * 
     * Given a string, find the length of the longest substring without repeating characters.
     * 
     * INPUT: A string
     * OUTPUT: Length of longest substring without repeats
     * 
     * EXAMPLES:
     * Input: "abcabcbb"
     * Output: 3 (substring "abc" has length 3)
     * 
     * Input: "bbbbb"
     * Output: 1 (substring "b" has length 1)
     * 
     * Input: "pwwkew"
     * Output: 3 (substring "wke" has length 3)
     * 
     * Input: ""
     * Output: 0
     * 
     * INTUITION:
     * - Use sliding window technique with map to track character positions
     * - Maintain a window [start, end] with unique characters
     * - When duplicate found, move start pointer to position after duplicate
     * - Update max length whenever window expands
     * - Use map to store character -> last position mapping
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(min(m, n)) - m unique characters, n string length
     * 
     * @param str Input string
     * @return Length of longest substring without repeats
     */
    fun longestSubstringWithoutRepeats(str: String): Int {
        val charMap = mutableMapOf<Char, Int>()
        var maxLength = 0
        var start = 0
        
        for (end in str.indices) {
            val char = str[end]
            if (char in charMap && charMap[char]!! >= start) {
                start = charMap[char]!! + 1
            }
            charMap[char] = end
            maxLength = maxOf(maxLength, end - start + 1)
        }
        
        return maxLength
    }
    
    /**
     * PROBLEM: Two Sum
     * 
     * Given an array of integers and a target sum, find two numbers that add up to the target.
     * Return their indices.
     * 
     * INPUT: Array of integers and target sum
     * OUTPUT: Pair of indices that sum to target, or null if not found
     * 
     * EXAMPLES:
     * Input: [2, 7, 11, 15], target = 9
     * Output: (0, 1) because nums[0] + nums[1] = 2 + 7 = 9
     * 
     * Input: [3, 2, 4], target = 6
     * Output: (1, 2) because nums[1] + nums[2] = 2 + 4 = 6
     * 
     * Input: [3, 3], target = 6
     * Output: (0, 1) because nums[0] + nums[1] = 3 + 3 = 6
     * 
     * INTUITION:
     * - Use map to store complement (target - current) -> index
     * - For each number, check if its complement exists in map
     * - If found, return current index and complement's index
     * - If not found, add current number -> index to map
     * - This ensures we only need one pass through the array
     * 
     * TIME COMPLEXITY: O(n) - n numbers to process
     * SPACE COMPLEXITY: O(n) - n numbers in map
     * 
     * @param numbers List of numbers
     * @param target Target sum
     * @return Pair of indices that sum to target or null
     */
    fun findTwoSum(numbers: List<Int>, target: Int): Pair<Int, Int>? {
        val complementMap = mutableMapOf<Int, Int>()
        
        for (i in numbers.indices) {
            val complement = target - numbers[i]
            if (complement in complementMap) {
                return complementMap[complement]!! to i
            }
            complementMap[numbers[i]] = i
        }
        
        return null
    }
    
    /**
     * PROBLEM: Find All Pairs That Sum to Target
     * 
     * Given an array of integers and a target sum, find all pairs of numbers that add up to the target.
     * 
     * INPUT: Array of integers and target sum
     * OUTPUT: List of pairs that sum to target
     * 
     * EXAMPLES:
     * Input: [1, 5, 3, 3, 2, 4], target = 6
     * Output: [(1, 5), (3, 3), (2, 4)]
     * 
     * Input: [2, 2, 2, 2], target = 4
     * Output: [(2, 2), (2, 2), (2, 2)] (6 pairs total)
     * 
     * Input: [1, 2, 3, 4, 5], target = 10
     * Output: [] (no pairs sum to 10)
     * 
     * INTUITION:
     * - Count frequency of all numbers
     * - For each number, find its complement (target - number)
     * - If complement exists, add pairs based on frequency
     * - Handle case where number equals complement (count/2 pairs)
     * - Handle case where number != complement (min of both frequencies)
     * 
     * TIME COMPLEXITY: O(n) - n numbers to process
     * SPACE COMPLEXITY: O(n) - n numbers in map
     * 
     * @param numbers List of numbers
     * @param target Target sum
     * @return List of pairs that sum to target
     */
    fun findAllPairsSum(numbers: List<Int>, target: Int): List<Pair<Int, Int>> {
        val frequency = countFrequency(numbers)
        val pairs = mutableListOf<Pair<Int, Int>>()
        
        for (num in frequency.keys) {
            val complement = target - num
            if (complement in frequency) {
                val count = if (num == complement) {
                    frequency[num]!! / 2
                } else {
                    minOf(frequency[num]!!, frequency[complement]!!)
                }
                repeat(count) {
                    pairs.add(num to complement)
                }
            }
        }
        
        return pairs
    }
    
    /**
     * PROBLEM: Valid Parentheses
     * 
     * Given a string containing parentheses, determine if the parentheses are valid.
     * Valid parentheses must be properly closed in the correct order.
     * 
     * INPUT: String containing parentheses characters
     * OUTPUT: true if parentheses are valid, false otherwise
     * 
     * EXAMPLES:
     * Input: "()"
     * Output: true
     * 
     * Input: "()[]{}"
     * Output: true
     * 
     * Input: "(]"
     * Output: false
     * 
     * Input: "([)]"
     * Output: false
     * 
     * Input: "{[]}"
     * Output: true
     * 
     * INTUITION:
     * - Use stack to track opening brackets
     * - Use map to store closing bracket -> opening bracket mapping
     * - For opening brackets, push to stack
     * - For closing brackets, check if top of stack matches
     * - If stack is empty or doesn't match, return false
     * - At end, check if stack is empty
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(n) - n characters in stack
     * 
     * @param str String with parentheses
     * @return true if parentheses are valid
     */
    fun isValidParentheses(str: String): Boolean {
        val bracketMap = mapOf(')' to '(', '}' to '{', ']' to '[')
        val stack = mutableListOf<Char>()
        
        for (char in str) {
            when (char) {
                '(', '{', '[' -> stack.add(char)
                ')', '}', ']' -> {
                    if (stack.isEmpty() || stack.removeLast() != bracketMap[char]) {
                        return false
                    }
                }
            }
        }
        
        return stack.isEmpty()
    }
    
    /**
     * PROBLEM: Longest Common Prefix
     * 
     * Given an array of strings, find the longest common prefix among all strings.
     * 
     * INPUT: Array of strings
     * OUTPUT: Longest common prefix string
     * 
     * EXAMPLES:
     * Input: ["flower", "flow", "flight"]
     * Output: "fl"
     * 
     * Input: ["dog", "racecar", "car"]
     * Output: "" (no common prefix)
     * 
     * Input: ["interspecies", "interstellar", "interstate"]
     * Output: "inters"
     * 
     * Input: ["throne", "throne"]
     * Output: "throne"
     * 
     * INTUITION:
     * - Find minimum length among all strings
     * - Check each position from 0 to min length
     * - At each position, check if all strings have same character
     * - If characters match, add to prefix
     * - If any character differs, stop and return current prefix
     * - Use map to count character frequencies at each position
     * 
     * TIME COMPLEXITY: O(n * m) - n strings, m min string length
     * SPACE COMPLEXITY: O(m) - m characters in map
     * 
     * @param strings List of strings
     * @return Longest common prefix
     */
    fun longestCommonPrefix(strings: List<String>): String {
        if (strings.isEmpty()) return ""
        if (strings.size == 1) return strings[0]
        
        val minLength = strings.minOf { it.length }
        val prefix = StringBuilder()
        
        for (i in 0 until minLength) {
            val charMap = mutableMapOf<Char, Int>()
            for (str in strings) {
                charMap[str[i]] = charMap.getOrDefault(str[i], 0) + 1
            }
            if (charMap.size == 1) {
                prefix.append(strings[0][i])
            } else {
                break
            }
        }
        
        return prefix.toString()
    }
    
    /**
     * PROBLEM: Word Pattern
     * 
     * Given a pattern and a string, determine if the string follows the pattern.
     * Pattern consists of lowercase letters, and string consists of words separated by spaces.
     * 
     * INPUT: Pattern string and space-separated string
     * OUTPUT: true if string follows pattern, false otherwise
     * 
     * EXAMPLES:
     * Input: pattern = "abba", str = "dog cat cat dog"
     * Output: true (a->dog, b->cat)
     * 
     * Input: pattern = "abba", str = "dog cat cat fish"
     * Output: false (a->dog, b->cat, but last word is fish)
     * 
     * Input: pattern = "aaaa", str = "dog cat cat dog"
     * Output: false (a should map to same word)
     * 
     * Input: pattern = "abba", str = "dog dog dog dog"
     * Output: false (different characters should map to different words)
     * 
     * INTUITION:
     * - Use two maps to track character->word and word->character mappings
     * - Split string into words
     * - Check if pattern length equals word count
     * - For each character-word pair, verify bijection
     * - If mapping conflicts, return false
     * - If all mappings are consistent, return true
     * 
     * TIME COMPLEXITY: O(n) - n words/characters
     * SPACE COMPLEXITY: O(n) - n mappings in maps
     * 
     * @param pattern Character pattern
     * @param str Space-separated string
     * @return true if pattern matches string
     */
    fun wordPattern(pattern: String, str: String): Boolean {
        val words = str.split(" ")
        if (pattern.length != words.size) return false
        
        val charToWord = mutableMapOf<Char, String>()
        val wordToChar = mutableMapOf<String, Char>()
        
        for (i in pattern.indices) {
            val char = pattern[i]
            val word = words[i]
            
            if (char in charToWord && charToWord[char] != word) return false
            if (word in wordToChar && wordToChar[word] != char) return false
            
            charToWord[char] = word
            wordToChar[word] = char
        }
        
        return true
    }
    
    /**
     * PROBLEM: Top K Frequent Elements
     * 
     * Given an array of integers and an integer k, return the k most frequent elements.
     * 
     * INPUT: Array of integers and integer k
     * OUTPUT: List of k most frequent elements
     * 
     * EXAMPLES:
     * Input: [1, 1, 1, 2, 2, 3], k = 2
     * Output: [1, 2] (1 appears 3 times, 2 appears 2 times)
     * 
     * Input: [1], k = 1
     * Output: [1]
     * 
     * Input: [1, 2, 3, 4, 5], k = 3
     * Output: [1, 2, 3] (all appear once, return first 3)
     * 
     * Input: [1, 1, 2, 2, 3, 3, 4], k = 2
     * Output: [1, 2] or [2, 3] or [1, 3] (all appear twice)
     * 
     * INTUITION:
     * - Count frequency of all elements
     * - Sort entries by frequency in descending order
     * - Take first k elements
     * - Return their keys
     * 
     * TIME COMPLEXITY: O(n log n) - n elements, sorting
     * SPACE COMPLEXITY: O(n) - n elements in map
     * 
     * @param elements List of elements
     * @param k Number of top elements
     * @return List of top k frequent elements
     */
    fun <T> topKFrequent(elements: List<T>, k: Int): List<T> {
        val frequency = countFrequency(elements)
        return frequency.entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
    }
    
    /**
     * Demonstrates map algorithms
     */
    fun demonstrateMapAlgorithms() {
        println("=== MAP ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Frequency counting
        println("1. FREQUENCY COUNTING")
        val words = listOf("apple", "banana", "apple", "cherry", "banana", "apple")
        val frequency = countFrequency(words)
        println("Words: $words")
        println("Frequency: $frequency")
        println()
        
        // 2. First non-repeating element
        println("2. FIRST NON-REPEATING ELEMENT")
        val numbers = listOf(1, 2, 3, 2, 1, 4, 5)
        val firstNonRepeating = findFirstNonRepeating(numbers)
        println("Numbers: $numbers")
        println("First non-repeating: $firstNonRepeating")
        println()
        
        // 3. Grouping by property
        println("3. GROUPING BY PROPERTY")
        val students = listOf("Alice", "Bob", "Charlie", "David", "Eve")
        val groupedByLength = groupByProperty(students) { it.length }
        println("Students: $students")
        println("Grouped by name length: $groupedByLength")
        println()
        
        // 4. Majority element
        println("4. MAJORITY ELEMENT")
        val votes = listOf(1, 2, 1, 1, 3, 1, 4, 1)
        val majority = findMajorityElement(votes)
        println("Votes: $votes")
        println("Majority element: $majority")
        println()
        
        // 5. Anagrams
        println("5. ANAGRAMS")
        val str1 = "listen"
        val str2 = "silent"
        val areAnagrams = areAnagrams(str1, str2)
        println("'$str1' and '$str2' are anagrams: $areAnagrams")
        
        val anagramStrings = listOf("eat", "tea", "tan", "ate", "nat", "bat")
        val anagramGroups = findAnagramGroups(anagramStrings)
        println("Anagram groups: $anagramGroups")
        println()
        
        // 6. Longest substring without repeats
        println("6. LONGEST SUBSTRING WITHOUT REPEATS")
        val testStr = "abcabcbb"
        val longestSubstring = longestSubstringWithoutRepeats(testStr)
        println("String: '$testStr'")
        println("Longest substring length: $longestSubstring")
        println()
        
        // 7. Two sum
        println("7. TWO SUM")
        val numbers2 = listOf(2, 7, 11, 15)
        val target = 9
        val twoSum = findTwoSum(numbers2, target)
        println("Numbers: $numbers2, Target: $target")
        println("Two sum indices: $twoSum")
        println()
        
        // 8. Valid parentheses
        println("8. VALID PARENTHESES")
        val parentheses = "({[]})"
        val isValid = isValidParentheses(parentheses)
        println("String: '$parentheses'")
        println("Valid parentheses: $isValid")
        println()
        
        // 9. Longest common prefix
        println("9. LONGEST COMMON PREFIX")
        val prefixStrings = listOf("flower", "flow", "flight")
        val commonPrefix = longestCommonPrefix(prefixStrings)
        println("Strings: $prefixStrings")
        println("Common prefix: '$commonPrefix'")
        println()
        
        // 10. Word pattern
        println("10. WORD PATTERN")
        val pattern = "abba"
        val patternStr = "dog cat cat dog"
        val matchesPattern = wordPattern(pattern, patternStr)
        println("Pattern: '$pattern', String: '$patternStr'")
        println("Matches pattern: $matchesPattern")
        println()
        
        // 11. Top k frequent
        println("11. TOP K FREQUENT ELEMENTS")
        val frequentElements = listOf(1, 1, 1, 2, 2, 3, 4, 4, 4, 4)
        val topK = topKFrequent(frequentElements, 2)
        println("Elements: $frequentElements")
        println("Top 2 frequent: $topK")
        println()
        
        println("=== MAP ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 