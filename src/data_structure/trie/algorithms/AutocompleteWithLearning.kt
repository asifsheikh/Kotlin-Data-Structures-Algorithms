package data_structure.trie.algorithms

import data_structure.trie.TrieCreation

/**
 * AUTOCOMPLETE WITH LEARNING ALGORITHM
 * 
 * Problem: Implement autocomplete functionality with learning capabilities using tries.
 * 
 * Examples:
 * Input: [("apple", 5), ("application", 3), ("apply", 7)], prefix = "app", selectedWord = "apple", k = 2
 * Output: ["apple", "apply"] (apple's frequency increased, affecting future rankings)
 * 
 * Intuition: Use trie with frequency updates based on user selections to improve future suggestions
 * 
 * Time Complexity: O(n + k * m + k log k) - n = prefix length, k = suggestions, m = avg word length
 * Space Complexity: O(k * m) - to store suggestions
 */

object AutocompleteWithLearning {
    
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
    
    // Helper methods
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
    
    private fun autocompleteWithFrequency(root: TrieCreation.EnhancedTrieNode, prefix: String, k: Int): List<String> {
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