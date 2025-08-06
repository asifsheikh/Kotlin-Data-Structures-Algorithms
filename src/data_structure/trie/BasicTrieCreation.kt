package data_structure.trie

/**
 * BASIC TRIE CREATION - Quick Reference
 * All Kotlin basic trie creation methods in one place
 */

object BasicTrieCreation {
    
    /**
     * Basic TrieNode class for string storage
     */
    class TrieNode {
        val children = Array<TrieNode?>(26) { null }
        var isEndOfWord = false
        var wordCount = 0 // Number of words ending at this node
        var prefixCount = 0 // Number of words with this prefix
    }

    /**
     * Creates a basic trie from a list of words
     */
    fun createBasicTrie(words: List<String>): TrieNode {
        val root = TrieNode()
        
        for (word in words) {
            var node = root
            for (char in word.lowercase()) {
                if (char.isLetter()) {
                    val index = char - 'a'
                    if (node.children[index] == null) {
                        node.children[index] = TrieNode()
                    }
                    node.prefixCount++
                    node = node.children[index]!!
                }
            }
            node.isEndOfWord = true
            node.wordCount++
        }
        
        return root
    }
} 