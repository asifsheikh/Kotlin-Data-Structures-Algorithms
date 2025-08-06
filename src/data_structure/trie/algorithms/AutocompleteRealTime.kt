package data_structure.trie.algorithms

import data_structure.trie.BasicTrieCreation

/**
 * AUTOCOMPLETE REAL-TIME ALGORITHM
 * 
 * Problem: Implement real-time autocomplete functionality using tries.
 * 
 * Examples:
 * Input: ["apple", "application", "apply", "appreciate"], partialInput = "appl", maxSuggestions = 3
 * Output: ["apple", "application", "apply"] (real-time suggestions as user types)
 * 
 * Intuition: Use trie to provide instant suggestions as user types partial input
 * 
 * Time Complexity: O(n + k * m) - n = partial input length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteRealTime {
    
    /**
     * Autocomplete with real-time suggestions
     */
    fun autocompleteRealTime(
        root: BasicTrieCreation.TrieNode, 
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
                    break
                }
                node = node.children[index]!!
            }
        }
        
        // Collect suggestions from current node
        collectWords(node, partialInput, suggestions)
        return suggestions.take(maxSuggestions).sorted()
    }
    
    // Helper method
    private fun collectWords(node: BasicTrieCreation.TrieNode, prefix: String, result: MutableList<String>) {
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