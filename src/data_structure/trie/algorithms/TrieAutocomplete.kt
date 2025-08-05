package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * TRIE AUTOCOMPLETE ALGORITHMS
 * 
 * Problem: Implement autocomplete functionality using tries for efficient prefix-based suggestions.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], prefix = "app" → Output: ["apple", "application", "apply", "appreciate"]
 * Input: ["car", "card", "care", "careful"], prefix = "car" → Output: ["car", "card", "care", "careful"]
 * Input: ["hello", "help", "hero"], prefix = "he" → Output: ["hello", "help", "hero"]
 * 
 * Intuition: Use trie to efficiently find all words with given prefix
 * 
 * Time Complexity: O(n + k * m) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object TrieAutocomplete {
    
    /**
     * Basic autocomplete with alphabetical sorting
     */
    fun autocompleteBasic(root: TrieCreation.TrieNode, prefix: String): List<String> {
        val result = mutableListOf<String>()
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return emptyList()
                }
                node = node.children[index]!!
            }
        }
        
        // Collect all words from this node
        collectWords(node, prefix, result)
        return result.sorted()
    }
    
    /**
     * Autocomplete with frequency-based ranking
     */
    fun autocompleteWithFrequency(root: TrieCreation.EnhancedTrieNode, prefix: String, k: Int): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        
        val suggestions = mutableListOf<Pair<String, Int>>()
        collectWordsWithFrequency(node, prefix, suggestions)
        
        // Sort by frequency (descending) and then alphabetically
        return suggestions
            .sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first })
            .take(k)
            .map { it.first }
    }
    
    /**
     * Autocomplete with custom ranking function
     */
    fun autocompleteWithRanking(
        root: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        k: Int,
        rankingFunction: (String, Int) -> Int
    ): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        
        val suggestions = mutableListOf<Triple<String, Int, Int>>()
        collectWordsWithCustomRanking(node, prefix, suggestions, rankingFunction)
        
        // Sort by custom ranking (descending)
        return suggestions
            .sortedByDescending { it.third }
            .take(k)
            .map { it.first }
    }
    
    /**
     * Autocomplete with length-based filtering
     */
    fun autocompleteWithLengthFilter(
        root: TrieCreation.TrieNode, 
        prefix: String, 
        minLength: Int = 0,
        maxLength: Int = Int.MAX_VALUE
    ): List<String> {
        val result = mutableListOf<String>()
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return emptyList()
                }
                node = node.children[index]!!
            }
        }
        
        // Collect words with length filter
        collectWordsWithLengthFilter(node, prefix, result, minLength, maxLength)
        return result.sorted()
    }
    
    /**
     * Autocomplete with pattern matching
     */
    fun autocompleteWithPattern(
        root: TrieCreation.TrieNode, 
        prefix: String, 
        pattern: String
    ): List<String> {
        val result = mutableListOf<String>()
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return emptyList()
                }
                node = node.children[index]!!
            }
        }
        
        // Collect words matching pattern
        collectWordsWithPattern(node, prefix, result, pattern)
        return result.sorted()
    }
    
    /**
     * Autocomplete with fuzzy matching
     */
    fun autocompleteWithFuzzy(
        root: TrieCreation.TrieNode, 
        prefix: String, 
        maxDistance: Int = 1
    ): List<String> {
        val result = mutableListOf<String>()
        val allWords = getAllWords(root)
        
        for (word in allWords) {
            if (word.startsWith(prefix) || editDistance(prefix, word.substring(0, minOf(prefix.length, word.length))) <= maxDistance) {
                result.add(word)
            }
        }
        
        return result.sorted()
    }
    
    /**
     * Autocomplete with context awareness
     */
    fun autocompleteWithContext(
        root: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        context: String,
        k: Int = 5
    ): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        
        val suggestions = mutableListOf<Pair<String, Double>>()
        collectWordsWithContext(node, prefix, suggestions, context)
        
        // Sort by context relevance
        return suggestions
            .sortedByDescending { it.second }
            .take(k)
            .map { it.first }
    }
    
    /**
     * Autocomplete with learning (updates frequency based on selection)
     */
    fun autocompleteWithLearning(
        root: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        selectedWord: String? = null,
        k: Int = 5
    ): List<String> {
        // Update frequency if word was selected
        selectedWord?.let { word ->
            updateWordFrequency(root, word)
        }
        
        return autocompleteWithFrequency(root, prefix, k)
    }
    
    /**
     * Autocomplete with multiple prefixes
     */
    fun autocompleteMultiplePrefixes(
        root: TrieCreation.TrieNode, 
        prefixes: List<String>
    ): Map<String, List<String>> {
        val result = mutableMapOf<String, List<String>>()
        
        for (prefix in prefixes) {
            result[prefix] = autocompleteBasic(root, prefix)
        }
        
        return result
    }
    
    /**
     * Autocomplete with real-time suggestions
     */
    fun autocompleteRealTime(
        root: TrieCreation.TrieNode, 
        partialInput: String,
        maxSuggestions: Int = 10
    ): List<String> {
        val suggestions = mutableListOf<String>()
        var node = root
        
        // Traverse as far as possible with partial input
        for (char in partialInput.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    // Can't go further, return what we have
                    break
                }
                node = node.children[index]!!
            }
        }
        
        // Collect suggestions from current node
        collectWords(node, partialInput, suggestions)
        return suggestions.take(maxSuggestions).sorted()
    }
    
    // Helper methods
    private fun collectWords(node: TrieCreation.TrieNode, prefix: String, result: MutableList<String>) {
        if (node.isEndOfWord) {
            result.add(prefix)
        }
        
        for (i in 0..25) {
            if (node.children[i] != null) {
                collectWords(node.children[i]!!, prefix + ('a' + i), result)
            }
        }
    }
    
    private fun collectWordsWithFrequency(
        node: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        result: MutableList<Pair<String, Int>>
    ) {
        if (node.isEndOfWord) {
            result.add(prefix to node.frequency)
        }
        
        for ((char, child) in node.children) {
            collectWordsWithFrequency(child, prefix + char, result)
        }
    }
    
    private fun collectWordsWithCustomRanking(
        node: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        result: MutableList<Triple<String, Int, Int>>,
        rankingFunction: (String, Int) -> Int
    ) {
        if (node.isEndOfWord) {
            val rank = rankingFunction(prefix, node.frequency)
            result.add(Triple(prefix, node.frequency, rank))
        }
        
        for ((char, child) in node.children) {
            collectWordsWithCustomRanking(child, prefix + char, result, rankingFunction)
        }
    }
    
    private fun collectWordsWithLengthFilter(
        node: TrieCreation.TrieNode, 
        prefix: String, 
        result: MutableList<String>,
        minLength: Int,
        maxLength: Int
    ) {
        if (node.isEndOfWord && prefix.length in minLength..maxLength) {
            result.add(prefix)
        }
        
        for (i in 0..25) {
            if (node.children[i] != null) {
                collectWordsWithLengthFilter(node.children[i]!!, prefix + ('a' + i), result, minLength, maxLength)
            }
        }
    }
    
    private fun collectWordsWithPattern(
        node: TrieCreation.TrieNode, 
        prefix: String, 
        result: MutableList<String>,
        pattern: String
    ) {
        if (node.isEndOfWord && prefix.matches(Regex(pattern))) {
            result.add(prefix)
        }
        
        for (i in 0..25) {
            if (node.children[i] != null) {
                collectWordsWithPattern(node.children[i]!!, prefix + ('a' + i), result, pattern)
            }
        }
    }
    
    private fun collectWordsWithContext(
        node: TrieCreation.EnhancedTrieNode, 
        prefix: String, 
        result: MutableList<Pair<String, Double>>,
        context: String
    ) {
        if (node.isEndOfWord) {
            val relevance = calculateContextRelevance(prefix, context)
            result.add(prefix to relevance)
        }
        
        for ((char, child) in node.children) {
            collectWordsWithContext(child, prefix + char, result, context)
        }
    }
    
    private fun getAllWords(root: TrieCreation.TrieNode): List<String> {
        val words = mutableListOf<String>()
        collectWords(root, "", words)
        return words
    }
    
    private fun updateWordFrequency(root: TrieCreation.EnhancedTrieNode, word: String) {
        var node = root
        for (char in word) {
            if (!node.children.containsKey(char)) {
                return
            }
            node = node.children[char]!!
        }
        if (node.isEndOfWord) {
            node.frequency++
        }
    }
    
    private fun editDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
        
        for (i in 0..s1.length) dp[i][0] = i
        for (j in 0..s2.length) dp[0][j] = j
        
        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        
        return dp[s1.length][s2.length]
    }
    
    private fun calculateContextRelevance(word: String, context: String): Double {
        // Simple relevance calculation based on word overlap
        val wordSet = word.lowercase().toSet()
        val contextSet = context.lowercase().toSet()
        val intersection = wordSet.intersect(contextSet)
        return intersection.size.toDouble() / wordSet.size
    }
} 