package data_structure.trie

/**
 * TRIE CREATION
 * 
 * This file contains functions for creating different types of tries
 * and various trie creation patterns.
 * 
 * TRIE PROPERTIES:
 * - Tree-like data structure for storing strings
 * - Each node represents a character
 * - Path from root to leaf represents a complete word
 * - Efficient for prefix-based operations
 * - Space-efficient for common prefixes
 * 
 * IMPLEMENTATION:
 * - Uses TrieNode with children array for lowercase letters (a-z)
 * - isEndOfWord flag marks complete words
 * - Supports case-sensitive and case-insensitive operations
 */

object TrieCreation {
    
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
     * Creates a basic trie from a list of words
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each word, traverse/create path character by character
     * 3. Mark the last node as end of word
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param words List of words to insert into trie
     * @return Root node of the created trie
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
    
    /**
     * Creates an enhanced trie with map-based children
     * 
     * ALGORITHM:
     * 1. Use HashMap for children instead of fixed array
     * 2. Supports any character, not just lowercase letters
     * 3. More flexible but slightly more memory usage
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param words List of words to insert into trie
     * @return Root node of the enhanced trie
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
     * Creates a case-insensitive trie
     * 
     * ALGORITHM:
     * 1. Convert all words to lowercase before insertion
     * 2. Use lowercase character indices for array access
     * 3. Maintain original case in separate storage if needed
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param words List of words (case-insensitive)
     * @return Root node of the case-insensitive trie
     */
    fun createCaseInsensitiveTrie(words: List<String>): TrieNode {
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
    
    /**
     * Creates a weighted trie with frequency information
     * 
     * ALGORITHM:
     * 1. Store frequency/weight at each word-ending node
     * 2. Useful for autocomplete with popularity ranking
     * 3. Higher frequency words appear first in suggestions
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param wordFrequencies Map of words to their frequencies
     * @return Root node of the weighted trie
     */
    fun createWeightedTrie(wordFrequencies: Map<String, Int>): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        for ((word, frequency) in wordFrequencies) {
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
     * Creates a trie from a dictionary file
     * 
     * ALGORITHM:
     * 1. Read words from file line by line
     * 2. Filter out invalid words (empty, non-alphabetic)
     * 3. Insert valid words into trie
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param filename Path to dictionary file
     * @return Root node of the trie, or null if file not found
     */
    fun createTrieFromFile(filename: String): TrieNode? {
        return try {
            val words = java.io.File(filename).readLines()
                .filter { it.isNotBlank() && it.all { char -> char.isLetter() } }
            createBasicTrie(words)
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Creates a trie for autocomplete functionality
     * 
     * ALGORITHM:
     * 1. Insert all words with their frequencies
     * 2. Store additional metadata for ranking
     * 3. Optimize for quick prefix lookups
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param words List of words for autocomplete
     * @return Root node of the autocomplete trie
     */
    fun createAutocompleteTrie(words: List<String>): EnhancedTrieNode {
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
            // Store the complete word for quick retrieval
            node.data = word
        }
        
        return root
    }
    
    /**
     * Creates a trie for spell checking
     * 
     * ALGORITHM:
     * 1. Insert all valid words from dictionary
     * 2. Optimize for fast word validation
     * 3. Include common misspellings with corrections
     * 
     * TIME COMPLEXITY: O(m * n) - m words, average length n
     * SPACE COMPLEXITY: O(m * n) - worst case when no common prefixes
     * 
     * @param dictionary List of valid words
     * @param corrections Map of misspellings to corrections
     * @return Root node of the spell checker trie
     */
    fun createSpellCheckerTrie(dictionary: List<String>, corrections: Map<String, String> = emptyMap()): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        // Insert all valid words
        for (word in dictionary) {
            var node = root
            for (char in word.lowercase()) {
                if (!node.children.containsKey(char)) {
                    node.children[char] = EnhancedTrieNode()
                }
                node.prefixCount++
                node = node.children[char]!!
            }
            node.isEndOfWord = true
            node.wordCount++
        }
        
        // Insert corrections
        for ((misspelling, correction) in corrections) {
            var node = root
            for (char in misspelling.lowercase()) {
                if (!node.children.containsKey(char)) {
                    node.children[char] = EnhancedTrieNode()
                }
                node.prefixCount++
                node = node.children[char]!!
            }
            node.isEndOfWord = true
            node.wordCount++
            node.data = correction // Store the correction
        }
        
        return root
    }
    
    /**
     * Demonstrates trie creation patterns
     */
    fun demonstrateTrieCreation() {
        println("=== TRIE CREATION DEMONSTRATION ===\n")
        
        // 1. Basic trie creation
        println("1. BASIC TRIE CREATION")
        val basicWords = listOf("hello", "world", "help", "hero", "heroic")
        val basicTrie = createBasicTrie(basicWords)
        println("Basic trie created with words: $basicWords")
        println()
        
        // 2. Enhanced trie creation
        println("2. ENHANCED TRIE CREATION")
        val enhancedWords = listOf("apple", "application", "apply", "appreciate")
        val enhancedTrie = createEnhancedTrie(enhancedWords)
        println("Enhanced trie created with words: $enhancedWords")
        println()
        
        // 3. Case-insensitive trie
        println("3. CASE-INSENSITIVE TRIE")
        val caseWords = listOf("Hello", "WORLD", "help", "Hero")
        val caseInsensitiveTrie = createCaseInsensitiveTrie(caseWords)
        println("Case-insensitive trie created with words: $caseWords")
        println()
        
        // 4. Weighted trie
        println("4. WEIGHTED TRIE")
        val wordFrequencies = mapOf(
            "the" to 1000,
            "be" to 800,
            "to" to 700,
            "of" to 600,
            "and" to 500
        )
        val weightedTrie = createWeightedTrie(wordFrequencies)
        println("Weighted trie created with word frequencies")
        println()
        
        // 5. Autocomplete trie
        println("5. AUTOCOMPLETE TRIE")
        val autocompleteWords = listOf("car", "card", "care", "careful", "careless")
        val autocompleteTrie = createAutocompleteTrie(autocompleteWords)
        println("Autocomplete trie created with words: $autocompleteWords")
        println()
        
        // 6. Spell checker trie
        println("6. SPELL CHECKER TRIE")
        val dictionary = listOf("hello", "world", "help", "hero")
        val corrections = mapOf("helo" to "hello", "wrold" to "world")
        val spellCheckerTrie = createSpellCheckerTrie(dictionary, corrections)
        println("Spell checker trie created with dictionary and corrections")
        println()
        
        println("=== TRIE CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 