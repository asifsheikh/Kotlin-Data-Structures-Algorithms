package data_structure.trie.algorithms

import data_structure.trie.BasicTrieCreation

/**
 * AUTOCOMPLETE MULTIPLE PREFIXES ALGORITHM
 * 
 * Problem: Implement autocomplete functionality for multiple prefixes using tries.
 * 
 * Examples:
 * Input: ["apple", "banana", "cherry"], prefixes = ["app", "ban", "che"]
 * Output: {"app": ["apple"], "ban": ["banana"], "che": ["cherry"]}
 * 
 * Intuition: Use trie to efficiently find words for multiple prefixes simultaneously
 * 
 * Time Complexity: O(p * (n + k * m)) - p = number of prefixes, n = prefix length, k = suggestions per prefix, m = avg word length
 * Space Complexity: O(p * k * m) - to store suggestions for all prefixes
 */

object AutocompleteMultiplePrefixes {
    
    /**
     * Autocomplete with multiple prefixes
     */
    fun autocompleteMultiplePrefixes(
        root: BasicTrieCreation.TrieNode, 
        prefixes: List<String>
    ): Map<String, List<String>> {
        val result = mutableMapOf<String, List<String>>()
        
        for (prefix in prefixes) {
            result[prefix] = autocompleteBasic(root, prefix)
        }
        
        return result
    }
    
    // Helper method
    private fun autocompleteBasic(root: BasicTrieCreation.TrieNode, prefix: String): List<String> {
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