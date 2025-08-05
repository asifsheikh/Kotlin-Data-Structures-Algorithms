package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH PATTERN ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with pattern matching using tries.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], prefix = "app", pattern = ".*e.*"
 * Output: ["apple", "appreciate"] (only words matching the regex pattern)
 * 
 * Intuition: Use trie to find words with prefix, then filter by regex pattern
 * 
 * Time Complexity: O(n + k * m + k * p) - n = prefix length, k = suggestions, m = avg word length, p = pattern complexity
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithPattern {
    
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
    
    // Helper method
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
} 