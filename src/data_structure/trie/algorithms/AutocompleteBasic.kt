package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * BASIC AUTOCOMPLETE ALGORITHM
 * 
 * Problem: Implement basic autocomplete functionality using tries for efficient prefix-based suggestions.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], prefix = "app" → Output: ["apple", "application", "apply", "appreciate"]
 * Input: ["car", "card", "care", "careful"], prefix = "car" → Output: ["car", "card", "care", "careful"]
 * Input: ["hello", "help", "hero"], prefix = "he" → Output: ["hello", "help", "hero"]
 * 
 * Intuition: Use trie to efficiently find all words with given prefix, then sort alphabetically
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length, k log k for sorting
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteBasic {
    
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
    
    // Helper method
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
} 