package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH LENGTH FILTER ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with length-based filtering using tries.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], prefix = "app", minLength = 5, maxLength = 8
 * Output: ["apple", "apply"] (only words with length 5-8)
 * 
 * Intuition: Use trie to find words with prefix, then filter by length constraints
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithLengthFilter {
    
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
    
    // Helper method
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
} 