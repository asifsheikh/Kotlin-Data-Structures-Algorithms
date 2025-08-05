package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH CONTEXT ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with context awareness using tries.
 * 
 * Examples:
 * Input: [("apple", 5), ("application", 3), ("apply", 7)], prefix = "app", context = "fruit", k = 2
 * Output: ["apple", "apply"] (ranked by context relevance)
 * 
 * Intuition: Use trie with context relevance calculation to provide contextual suggestions
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithContext {
    
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
    
    // Helper methods
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
    
    private fun calculateContextRelevance(word: String, context: String): Double {
        // Simple relevance calculation based on word overlap
        val wordSet = word.lowercase().toSet()
        val contextSet = context.lowercase().toSet()
        val intersection = wordSet.intersect(contextSet)
        return intersection.size.toDouble() / wordSet.size
    }
} 