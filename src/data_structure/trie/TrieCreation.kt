package data_structure.trie

/**
 * TRIE CREATION - Quick Reference
 * All Kotlin trie creation methods in one place
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
     * All Trie Creation Methods
     * Complete reference for creating tries in Kotlin
     */
    fun allTrieCreationMethods() {
        // === BASIC TRIE CREATION ===
        val emptyTrie = TrieNode()                               // Empty trie with root node
        val enhancedEmptyTrie = EnhancedTrieNode()               // Empty enhanced trie
        
        // === FROM WORD LISTS ===
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val basicTrie = createBasicTrie(words)                   // Basic trie from word list
        val enhancedTrie = createEnhancedTrie(words)             // Enhanced trie from word list
        
        // === FROM STRINGS ===
        val singleWordTrie = createBasicTrie(listOf("hello"))    // Single word trie
        val emptyWordListTrie = createBasicTrie(emptyList())     // Empty word list trie
        val singleCharWordsTrie = createBasicTrie(listOf("a", "b", "c")) // Single character words
        
        // === FROM ARRAYS ===
        val wordArray = arrayOf("dog", "cat", "bird", "fish")
        val arrayTrie = createBasicTrie(wordArray.toList())      // Trie from array
        
        // === FROM SETS ===
        val wordSet = setOf("red", "green", "blue", "yellow")
        val setTrie = createBasicTrie(wordSet.toList())          // Trie from set
        
        // === WITH FREQUENCIES ===
        val weightedWords = listOf("apple" to 5, "banana" to 3, "cherry" to 7)
        val weightedTrie = createWeightedTrie(weightedWords)     // Trie with word frequencies
        
        // === CASE SENSITIVE ===
        val caseSensitiveWords = listOf("Apple", "apple", "BANANA", "banana")
        val caseSensitiveTrie = createCaseSensitiveTrie(caseSensitiveWords) // Case sensitive trie
        
        // === FROM PATTERNS ===
        val patternWords = listOf("cat", "bat", "rat", "hat")
        val patternTrie = createBasicTrie(patternWords)          // Pattern-based trie
        
        // === FROM RANGES ===
        val rangeWords = (1..5).map { "word$it" }
        val rangeTrie = createBasicTrie(rangeWords)              // Range-generated trie
        
        // === FROM FUNCTIONS ===
        val functionWords = (1..10).map { "item${it * 2}" }
        val functionTrie = createBasicTrie(functionWords)        // Function-generated trie
        
        // === SPECIAL PATTERNS ===
        val palindromeWords = listOf("radar", "level", "deed", "noon")
        val palindromeTrie = createBasicTrie(palindromeWords)    // Palindrome trie
        
        val anagramWords = listOf("listen", "silent", "enlist", "tinsel")
        val anagramTrie = createBasicTrie(anagramWords)          // Anagram trie
        
        // === NESTED STRUCTURES ===
        val nestedWords = listOf(
            "user.profile.name",
            "user.profile.email", 
            "user.settings.theme",
            "admin.users.list"
        )
        val nestedTrie = createBasicTrie(nestedWords)            // Nested structure trie
        
        // === WITH CUSTOM DATA ===
        val customDataWords = listOf("apple" to "fruit", "car" to "vehicle", "book" to "object")
        val customDataTrie = createCustomDataTrie(customDataWords) // Trie with custom data
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
    
    /**
     * Creates a trie with custom data
     */
    fun createCustomDataTrie(words: List<Pair<String, Any>>): EnhancedTrieNode {
        val root = EnhancedTrieNode()
        
        for ((word, data) in words) {
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
            node.data = data
        }
        
        return root
    }
} 