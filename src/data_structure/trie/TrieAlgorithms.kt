package data_structure.trie

/**
 * TRIE ALGORITHMS
 * 
 * This file contains advanced algorithms that use tries including
 * autocomplete, spell checking, word search, and other trie-based algorithms.
 * 
 * COMMON TRIE ALGORITHMS:
 * - Autocomplete: Suggest words based on partial input
 * - Spell Checking: Find and suggest corrections for misspelled words
 * - Word Search: Find words in a 2D grid
 * - Prefix Matching: Advanced prefix-based operations
 * - String Matching: Pattern matching using tries
 */

object TrieAlgorithms {
    
    // Use the TrieNode class from TrieCreation directly
    
    /**
     * Autocomplete algorithm with frequency-based ranking
     * 
     * ALGORITHM:
     * 1. Traverse to the prefix node
     * 2. Collect all words with the prefix using DFS
     * 3. Sort by frequency (if available) or alphabetically
     * 4. Return top k suggestions
     * 
     * TIME COMPLEXITY: O(n + k * m + k log k)
     * - n = prefix length
     * - k = number of matching words
     * - m = average word length
     * - k log k for sorting
     * SPACE COMPLEXITY: O(k * m) - to store suggestions
     * 
     * @param root Root node of the weighted trie
     * @param prefix Partial word input
     * @param k Number of suggestions to return
     * @return List of top k autocomplete suggestions
     */
    fun autocomplete(root: TrieCreation.EnhancedTrieNode, prefix: String, k: Int): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        
        val suggestions = mutableListOf<Pair<String, Int>>()
        dfsCollectWithFrequency(node, prefix, suggestions)
        
        // Sort by frequency (descending) and then alphabetically
        return suggestions
            .sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first })
            .take(k)
            .map { it.first }
    }
    
    /**
     * Spell checker with edit distance suggestions
     * 
     * ALGORITHM:
     * 1. Check if word exists in trie
     * 2. If not found, generate candidates with edit distance ≤ 2
     * 3. Check each candidate against the trie
     * 4. Return valid words sorted by edit distance
     * 
     * TIME COMPLEXITY: O(n + 26^n * m) - n is word length, m is dictionary size
     * SPACE COMPLEXITY: O(k * n) - k candidates, n word length
     * 
     * @param root Root node of the dictionary trie
     * @param word Potentially misspelled word
     * @param maxDistance Maximum edit distance for suggestions
     * @return List of correction suggestions
     */
    fun spellCheck(root: TrieCreation.TrieNode, word: String, maxDistance: Int = 2): List<String> {
        if (TrieOperations.searchWord(root, word)) {
            return listOf(word) // Word is correct
        }
        
        val suggestions = mutableListOf<String>()
        val candidates = generateEditDistanceCandidates(word, maxDistance)
        
        for (candidate in candidates) {
            if (TrieOperations.searchWord(root, candidate)) {
                suggestions.add(candidate)
            }
        }
        
        // Sort by edit distance (closest first)
        return suggestions.sortedBy { editDistance(word, it) }
    }
    
    /**
     * Word search in 2D grid using trie
     * 
     * ALGORITHM:
     * 1. Build trie from dictionary words
     * 2. For each cell in grid, start DFS in all 8 directions
     * 3. During DFS, check if current path forms a word in trie
     * 4. Collect all found words
     * 
     * TIME COMPLEXITY: O(m * n * 8^maxLength) - m*n grid, maxLength is longest word
     * SPACE COMPLEXITY: O(k * maxLength) - k words, maxLength characters
     * 
     * @param grid 2D character grid
     * @param words List of words to search for
     * @return List of words found in the grid
     */
    fun wordSearch(grid: Array<CharArray>, words: List<String>): List<String> {
        val trie = TrieCreation.createBasicTrie(words)
        val result = mutableSetOf<String>()
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                dfsWordSearch(grid, i, j, trie, "", result, visited)
            }
        }
        
        return result.toList()
    }
    
    /**
     * Longest word that can be built from other words
     * 
     * ALGORITHM:
     * 1. Sort words by length (descending)
     * 2. Build trie from all words
     * 3. For each word, check if it can be built from other words
     * 4. Return the first (longest) word that can be built
     * 
     * TIME COMPLEXITY: O(n * m^2) - n words, m average word length
     * SPACE COMPLEXITY: O(n * m) - trie storage
     * 
     * @param words List of words
     * @return Longest word that can be built from other words, or empty string
     */
    fun longestWordBuiltFromOthers(words: List<String>): String {
        val trie = TrieCreation.createBasicTrie(words)
        val sortedWords = words.sortedByDescending { it.length }
        
        for (word in sortedWords) {
            if (canBuildWord(trie, word, 0, 0)) {
                return word
            }
        }
        
        return ""
    }
    
    /**
     * Find all concatenated words
     * 
     * ALGORITHM:
     * 1. Build trie from all words
     * 2. For each word, check if it can be formed by concatenating other words
     * 3. Return all words that can be concatenated
     * 
     * TIME COMPLEXITY: O(n * m^2) - n words, m average word length
     * SPACE COMPLEXITY: O(n * m) - trie storage
     * 
     * @param words List of words
     * @return List of concatenated words
     */
    fun findAllConcatenatedWords(words: List<String>): List<String> {
        val trie = TrieCreation.createBasicTrie(words)
        val result = mutableListOf<String>()
        
        for (word in words) {
            if (canBuildWord(trie, word, 0, 0)) {
                result.add(word)
            }
        }
        
        return result
    }
    
    /**
     * Maximum XOR of two numbers in array using trie
     * 
     * ALGORITHM:
     * 1. Build binary trie from all numbers (32-bit representation)
     * 2. For each number, find the number that gives maximum XOR
     * 3. Use trie to find the best opposite bit at each position
     * 
     * TIME COMPLEXITY: O(n * 32) - n numbers, 32 bits each
     * SPACE COMPLEXITY: O(n * 32) - trie storage
     * 
     * @param nums Array of integers
     * @return Maximum XOR value
     */
    fun findMaximumXOR(nums: IntArray): Int {
        val trie = buildBinaryTrie(nums)
        var maxXOR = 0
        
        for (num in nums) {
            maxXOR = maxOf(maxXOR, findMaxXORForNumber(trie, num))
        }
        
        return maxXOR
    }
    
    /**
     * Stream of characters with trie-based word checking
     * 
     * ALGORITHM:
     * 1. Maintain a trie of valid words
     * 2. For each character, check all possible word endings
     * 3. Use sliding window to check recent characters
     * 
     * TIME COMPLEXITY: O(k * m) per character - k words, m average length
     * SPACE COMPLEXITY: O(n * m) - n words, m average length
     */
    class StreamChecker(words: List<String>) {
        private val trie = TrieCreation.createBasicTrie(words)
        private val stream = StringBuilder()
        
        fun query(letter: Char): Boolean {
            stream.append(letter)
            
            // Check all possible word endings
            for (i in stream.length downTo 1) {
                val suffix = stream.substring(stream.length - i)
                if (TrieOperations.searchWord(trie, suffix)) {
                    return true
                }
            }
            
            return false
        }
    }
    
    /**
     * Helper function to collect words with frequency
     */
    private fun dfsCollectWithFrequency(node: TrieCreation.EnhancedTrieNode?, currentWord: String, result: MutableList<Pair<String, Int>>) {
        if (node == null) return
        
        if (node.isEndOfWord) {
            result.add(Pair(currentWord, node.frequency))
        }
        
        for ((char, child) in node.children) {
            dfsCollectWithFrequency(child, currentWord + char, result)
        }
    }
    
    /**
     * Helper function to generate edit distance candidates
     */
    private fun generateEditDistanceCandidates(word: String, maxDistance: Int): List<String> {
        val candidates = mutableSetOf<String>()
        candidates.add(word)
        
        repeat(maxDistance) {
            val newCandidates = mutableSetOf<String>()
            for (candidate in candidates) {
                newCandidates.addAll(generateOneEditDistance(candidate))
            }
            candidates.addAll(newCandidates)
        }
        
        return candidates.toList()
    }
    
    /**
     * Helper function to generate one-edit-distance words
     */
    private fun generateOneEditDistance(word: String): List<String> {
        val result = mutableListOf<String>()
        
        // Insertions
        for (i in 0..word.length) {
            for (c in 'a'..'z') {
                result.add(word.substring(0, i) + c + word.substring(i))
            }
        }
        
        // Deletions
        for (i in word.indices) {
            result.add(word.substring(0, i) + word.substring(i + 1))
        }
        
        // Substitutions
        for (i in word.indices) {
            for (c in 'a'..'z') {
                if (c != word[i]) {
                    result.add(word.substring(0, i) + c + word.substring(i + 1))
                }
            }
        }
        
        return result
    }
    
    /**
     * Helper function to calculate edit distance
     */
    private fun editDistance(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
        
        for (i in 0..word1.length) dp[i][0] = i
        for (j in 0..word2.length) dp[0][j] = j
        
        for (i in 1..word1.length) {
            for (j in 1..word2.length) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        
        return dp[word1.length][word2.length]
    }
    
    /**
     * Helper function for word search DFS
     */
    private fun dfsWordSearch(
        grid: Array<CharArray>,
        i: Int,
        j: Int,
        trie: TrieCreation.TrieNode,
        currentWord: String,
        result: MutableSet<String>,
        visited: Array<BooleanArray>
    ) {
        if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size || visited[i][j]) {
            return
        }
        
        val char = grid[i][j]
        val index = char - 'a'
        val nextTrie = trie.children[index] ?: return
        
        val newWord = currentWord + char
        if (nextTrie.isEndOfWord) {
            result.add(newWord)
        }
        
        visited[i][j] = true
        
        // Check all 8 directions
        val directions = arrayOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        
        for ((di, dj) in directions) {
            dfsWordSearch(grid, i + di, j + dj, nextTrie, newWord, result, visited)
        }
        
        visited[i][j] = false
    }
    
    /**
     * Helper function to check if word can be built from other words
     */
    private fun canBuildWord(trie: TrieCreation.TrieNode, word: String, index: Int, count: Int): Boolean {
        if (index == word.length) {
            return count > 1 // Must be built from at least 2 other words
        }
        
        var node = trie
        for (i in index until word.length) {
            val char = word[i]
            val charIndex = char - 'a'
            node = node.children[charIndex] ?: return false
            
            if (node.isEndOfWord && canBuildWord(trie, word, i + 1, count + 1)) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Helper function to build binary trie
     */
    private fun buildBinaryTrie(nums: IntArray): BinaryTrieNode {
        val root = BinaryTrieNode()
        
        for (num in nums) {
            var node = root
            for (i in 31 downTo 0) {
                val bit = (num shr i) and 1
                if (node.children[bit] == null) {
                    node.children[bit] = BinaryTrieNode()
                }
                node = node.children[bit]!!
            }
        }
        
        return root
    }
    
    /**
     * Helper function to find max XOR for a number
     */
    private fun findMaxXORForNumber(trie: BinaryTrieNode, num: Int): Int {
        var node = trie
        var result = 0
        
        for (i in 31 downTo 0) {
            val bit = (num shr i) and 1
            val oppositeBit = 1 - bit
            
            if (node.children[oppositeBit] != null) {
                result = result or (1 shl i)
                node = node.children[oppositeBit]!!
            } else {
                node = node.children[bit]!!
            }
        }
        
        return result
    }
    
    /**
     * Binary trie node for XOR operations
     */
    private class BinaryTrieNode {
        val children = Array<BinaryTrieNode?>(2) { null }
    }
    
    /**
     * Demonstrates trie algorithms
     */
    fun demonstrateTrieAlgorithms() {
        println("=== TRIE ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Autocomplete
        println("1. AUTOCOMPLETE")
        val autocompleteWords = mapOf(
            "apple" to 100,
            "application" to 80,
            "apply" to 90,
            "appreciate" to 70,
            "approach" to 85
        )
        val autocompleteTrie = TrieCreation.createWeightedTrie(autocompleteWords)
        val suggestions = autocomplete(autocompleteTrie, "app", 3)
        println("Autocomplete suggestions for 'app': $suggestions")
        println()
        
        // 2. Spell checking
        println("2. SPELL CHECKING")
        val dictionary = listOf("hello", "world", "help", "hero", "heroic")
        val spellCheckerTrie = TrieCreation.createBasicTrie(dictionary)
        val corrections = spellCheck(spellCheckerTrie, "helo", 2)
        println("Spell check suggestions for 'helo': $corrections")
        println()
        
        // 3. Word search
        println("3. WORD SEARCH")
        val grid = arrayOf(
            charArrayOf('o', 'a', 'a', 'n'),
            charArrayOf('e', 't', 'a', 'e'),
            charArrayOf('i', 'h', 'k', 'r'),
            charArrayOf('i', 'f', 'l', 'v')
        )
        val searchWords = listOf("oath", "pea", "eat", "rain")
        val foundWords = wordSearch(grid, searchWords)
        println("Words found in grid: $foundWords")
        println()
        
        // 4. Longest word built from others
        println("4. LONGEST WORD BUILT FROM OTHERS")
        val buildWords = listOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat")
        val longestBuilt = longestWordBuiltFromOthers(buildWords)
        println("Longest word built from others: '$longestBuilt'")
        println()
        
        // 5. Stream checker
        println("5. STREAM CHECKER")
        val streamWords = listOf("cd", "f", "kl")
        val streamChecker = StreamChecker(streamWords)
        println("Query 'a': ${streamChecker.query('a')}")
        println("Query 'b': ${streamChecker.query('b')}")
        println("Query 'c': ${streamChecker.query('c')}")
        println("Query 'd': ${streamChecker.query('d')}")
        println()
        
        println("=== TRIE ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 