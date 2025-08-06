package data_structure.trie

/**
 * ENHANCED TRIE CREATION - Quick Reference
 * All Kotlin enhanced trie creation methods in one place
 */

object EnhancedTrieCreation {
    
    /**
     * Enhanced TrieNode with additional properties
     */
    class EnhancedTrieNode {
        val children = mutableMapOf<Char, EnhancedTrieNode>()
        var isEndOfWord = false
        var wordCount = 0
        var prefixCount = 0
        var frequency = 0 // For weighted tries
        var data: Any? = null // For storing additional data
    }

    /**
     * Creates an enhanced trie with map-based children
     */
    fun createEnhancedTrie(words: List<String>): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        for (word in words) {
            var node = root
            for (char in word) {
                if (!node.children.containsKey(char)) {
                    node.children[char] = EnhancedTrieNode()
                }
                node.prefixCount++
                node = node.children[char]!!
            }
            node.isEndOfWord = true
            node.wordCount++
        }
        
        return root
    }
    
    /**
     * Creates a weighted trie with frequencies
     */
    fun createWeightedTrie(words: List<Pair<String, Int>>): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        for ((word, frequency) in words) {
            var node = root
            for (char in word) {
                if (!node.children.containsKey(char)) {
                    node.children[char] = EnhancedTrieNode()
                }
                node.prefixCount++
                node = node.children[char]!!
            }
            node.isEndOfWord = true
            node.wordCount++
            node.frequency = frequency
        }
        
        return root
    }
    
    /**
     * Creates a case-sensitive trie
     */
    fun createCaseSensitiveTrie(words: List<String>): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        for (word in words) {
            var node = root
            for (char in word) {
                if (!node.children.containsKey(char)) {
                    node.children[char] = EnhancedTrieNode()
                }
                node.prefixCount++
                node = node.children[char]!!
            }
            node.isEndOfWord = true
            node.wordCount++
        }
        
        return root
    }
} 