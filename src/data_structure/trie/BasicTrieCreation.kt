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
     * All Basic Trie Creation Methods
     * Complete reference for creating basic tries in Kotlin
     */
    fun allBasicTrieCreationMethods() {
        // === BASIC TRIE CREATION ===
        val emptyTrie = TrieNode()                               // Empty trie with root node
        
        // === FROM WORD LISTS ===
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val basicTrie = createBasicTrie(words)                   // Basic trie from word list
        
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
        
        // === FROM SEQUENCES ===
        val sequenceWords = (1..10).asSequence().map { "seq$it" }
        val sequenceTrie = createBasicTrie(sequenceWords.toList()) // Trie from sequence
        
        // === FROM ITERABLES ===
        val iterableWords = (1..5).map { "iter$it" }.asIterable()
        val iterableTrie = createBasicTrie(iterableWords.toList()) // Trie from iterable
        
        // === WITH FILTERING ===
        val allWords = listOf("apple", "banana", "cat", "dog", "elephant")
        val filteredWords = allWords.filter { it.length > 3 }
        val filteredTrie = createBasicTrie(filteredWords)        // Trie with filtered words
        
        // === WITH TRANSFORMATION ===
        val originalWords = listOf("HELLO", "WORLD", "KOTLIN")
        val transformedWords = originalWords.map { it.lowercase() }
        val transformedTrie = createBasicTrie(transformedWords)  // Trie with transformed words
        
        // === WITH SORTING ===
        val unsortedWords = listOf("zebra", "apple", "banana", "cat")
        val sortedWords = unsortedWords.sorted()
        val sortedTrie = createBasicTrie(sortedWords)            // Trie with sorted words
        
        // === WITH DEDUPLICATION ===
        val duplicateWords = listOf("apple", "banana", "apple", "cherry", "banana")
        val uniqueWords = duplicateWords.distinct()
        val uniqueTrie = createBasicTrie(uniqueWords)            // Trie with unique words
        
        // === FROM STRING SPLITTING ===
        val longString = "apple,banana,cherry,dog,elephant"
        val splitWords = longString.split(",")
        val splitTrie = createBasicTrie(splitWords)              // Trie from split string
        
        // === WITH PREFIXES ===
        val prefixWords = listOf("pre", "prefix", "preliminary", "prepare")
        val prefixTrie = createBasicTrie(prefixWords)            // Trie with common prefixes
        
        // === WITH SUFFIXES ===
        val suffixWords = listOf("ing", "sing", "ring", "bring")
        val suffixTrie = createBasicTrie(suffixWords)            // Trie with common suffixes
        
        // === FROM CHARACTER ARRAYS ===
        val charArrays = listOf(charArrayOf('a', 'p', 'p', 'l', 'e'), charArrayOf('b', 'a', 'n', 'a', 'n', 'a'))
        val charArrayWords = charArrays.map { String(it) }
        val charArrayTrie = createBasicTrie(charArrayWords)      // Trie from character arrays
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
     * Creates a basic trie from a single word
     */
    fun createBasicTrieFromWord(word: String): TrieNode {
        return createBasicTrie(listOf(word))
    }
    
    /**
     * Creates a basic trie from an array of words
     */
    fun createBasicTrieFromArray(words: Array<String>): TrieNode {
        return createBasicTrie(words.toList())
    }
    
    /**
     * Creates a basic trie from a set of words
     */
    fun createBasicTrieFromSet(words: Set<String>): TrieNode {
        return createBasicTrie(words.toList())
    }
    
    /**
     * Creates a basic trie from a sequence of words
     */
    fun createBasicTrieFromSequence(words: Sequence<String>): TrieNode {
        return createBasicTrie(words.toList())
    }
    
    /**
     * Creates a basic trie with case-insensitive words
     */
    fun createCaseInsensitiveTrie(words: List<String>): TrieNode {
        val normalizedWords = words.map { it.lowercase() }
        return createBasicTrie(normalizedWords)
    }
    
    /**
     * Creates a basic trie with only alphabetic characters
     */
    fun createAlphabeticTrie(words: List<String>): TrieNode {
        val alphabeticWords = words.map { it.filter { char -> char.isLetter() } }
        return createBasicTrie(alphabeticWords)
    }
    
    /**
     * Creates a basic trie with word length filtering
     */
    fun createFilteredTrie(words: List<String>, minLength: Int): TrieNode {
        val filteredWords = words.filter { it.length >= minLength }
        return createBasicTrie(filteredWords)
    }
    
    /**
     * Creates a basic trie with word transformation
     */
    fun createTransformedTrie(words: List<String>, transform: (String) -> String): TrieNode {
        val transformedWords = words.map(transform)
        return createBasicTrie(transformedWords)
    }
} 