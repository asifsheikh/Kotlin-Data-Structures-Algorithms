package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH CUSTOM RANKING ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with custom ranking function using tries.
 * 
 * Examples:
 * Input: [("apple", 5), ("application", 3), ("apply", 7)], prefix = "app", k = 2, ranking = length * frequency
 * Output: ["application", "apply"] (sorted by custom ranking)
 * 
 * Intuition: Use trie with custom ranking function to provide personalized suggestions
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithRanking {
    
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
    
    // Helper method
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
} 