package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH FREQUENCY ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with frequency-based ranking using tries.
 * 
 * Examples:
 * Input: [("apple", 5), ("application", 3), ("apply", 7), ("appreciate", 2)], prefix = "app", k = 3
 * Output: ["apply", "apple", "application"] (sorted by frequency descending, then alphabetically)
 * 
 * Intuition: Use trie with frequency data to rank suggestions by popularity
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithFrequency {
    
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
    
    // Helper method
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
} 