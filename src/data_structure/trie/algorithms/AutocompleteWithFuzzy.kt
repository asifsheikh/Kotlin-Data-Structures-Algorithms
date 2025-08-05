package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH FUZZY MATCHING ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with fuzzy matching using tries.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], prefix = "app", maxDistance = 1
 * Output: ["apple", "application", "apply", "appreciate"] (including fuzzy matches)
 * 
 * Intuition: Use trie to find words with prefix, then include words with edit distance within threshold
 * 
 * Time Complexity: O(n + k * m + k * d) - n = prefix length, k = suggestions, m = avg word length, d = edit distance calculation
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithFuzzy {
    
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
    
    // Helper methods
    private fun getAllWords(root: TrieCreation.TrieNode): List<String> {
        val words = mutableListOf<String>()
        collectWords(root, "", words)
        return words
    }
    
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
} 