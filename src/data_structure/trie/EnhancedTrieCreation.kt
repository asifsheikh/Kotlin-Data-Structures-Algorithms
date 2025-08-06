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
     * All Enhanced Trie Creation Methods
     * Complete reference for creating enhanced tries in Kotlin
     */
    fun allEnhancedTrieCreationMethods() {
        // === ENHANCED TRIE CREATION ===
        val enhancedEmptyTrie = EnhancedTrieNode()               // Empty enhanced trie
        
        // === FROM WORD LISTS ===
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val enhancedTrie = createEnhancedTrie(words)             // Enhanced trie from word list
        
        // === WITH FREQUENCIES ===
        val weightedWords = listOf("apple" to 5, "banana" to 3, "cherry" to 7)
        val weightedTrie = createWeightedTrie(weightedWords)     // Trie with word frequencies
        
        // === CASE SENSITIVE ===
        val caseSensitiveWords = listOf("Apple", "apple", "BANANA", "banana")
        val caseSensitiveTrie = createCaseSensitiveTrie(caseSensitiveWords) // Case sensitive trie
        
        // === WITH CUSTOM DATA ===
        val customDataWords = listOf("apple" to "fruit", "car" to "vehicle", "book" to "object")
        val customDataTrie = createCustomDataTrie(customDataWords) // Trie with custom data
        
        // === WITH NUMERIC DATA ===
        val numericDataWords = listOf("apple" to 42, "banana" to 17, "cherry" to 99)
        val numericDataTrie = createNumericDataTrie(numericDataWords) // Trie with numeric data
        
        // === WITH BOOLEAN DATA ===
        val booleanDataWords = listOf("active" to true, "inactive" to false, "pending" to true)
        val booleanDataTrie = createBooleanDataTrie(booleanDataWords) // Trie with boolean data
        
        // === WITH LIST DATA ===
        val listDataWords = listOf(
            "fruits" to listOf("apple", "banana", "cherry"),
            "colors" to listOf("red", "green", "blue"),
            "numbers" to listOf(1, 2, 3, 4, 5)
        )
        val listDataTrie = createListDataTrie(listDataWords)     // Trie with list data
        
        // === WITH MAP DATA ===
        val mapDataWords = listOf(
            "user1" to mapOf("name" to "John", "age" to 25),
            "user2" to mapOf("name" to "Jane", "age" to 30)
        )
        val mapDataTrie = createMapDataTrie(mapDataWords)        // Trie with map data
        
        // === WITH OBJECT DATA ===
        data class Person(val name: String, val age: Int)
        val objectDataWords = listOf(
            "person1" to Person("Alice", 25),
            "person2" to Person("Bob", 30)
        )
        val objectDataTrie = createObjectDataTrie(objectDataWords) // Trie with object data
        
        // === WITH TIMESTAMP DATA ===
        val timestampDataWords = listOf(
            "event1" to System.currentTimeMillis(),
            "event2" to System.currentTimeMillis() + 1000
        )
        val timestampDataTrie = createTimestampDataTrie(timestampDataWords) // Trie with timestamp data
        
        // === WITH PRIORITY DATA ===
        val priorityDataWords = listOf("high" to 1, "medium" to 2, "low" to 3)
        val priorityDataTrie = createPriorityDataTrie(priorityDataWords) // Trie with priority data
        
        // === WITH SCORE DATA ===
        val scoreDataWords = listOf("player1" to 95.5, "player2" to 87.2, "player3" to 92.8)
        val scoreDataTrie = createScoreDataTrie(scoreDataWords)  // Trie with score data
        
        // === WITH CATEGORY DATA ===
        val categoryDataWords = listOf(
            "apple" to "fruit",
            "carrot" to "vegetable",
            "chicken" to "meat",
            "bread" to "grain"
        )
        val categoryDataTrie = createCategoryDataTrie(categoryDataWords) // Trie with category data
        
        // === WITH STATUS DATA ===
        val statusDataWords = listOf(
            "task1" to "completed",
            "task2" to "in_progress",
            "task3" to "pending"
        )
        val statusDataTrie = createStatusDataTrie(statusDataWords) // Trie with status data
        
        // === WITH RATING DATA ===
        val ratingDataWords = listOf(
            "movie1" to 4.5,
            "movie2" to 3.8,
            "movie3" to 4.9
        )
        val ratingDataTrie = createRatingDataTrie(ratingDataWords) // Trie with rating data
        
        // === WITH LOCATION DATA ===
        data class Location(val lat: Double, val lng: Double)
        val locationDataWords = listOf(
            "home" to Location(40.7128, -74.0060),
            "work" to Location(40.7589, -73.9851)
        )
        val locationDataTrie = createLocationDataTrie(locationDataWords) // Trie with location data
        
        // === WITH CONFIGURATION DATA ===
        val configDataWords = listOf(
            "theme" to "dark",
            "language" to "en",
            "timezone" to "UTC"
        )
        val configDataTrie = createConfigDataTrie(configDataWords) // Trie with configuration data
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
    
    /**
     * Creates a trie with numeric data
     */
    fun createNumericDataTrie(words: List<Pair<String, Int>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with boolean data
     */
    fun createBooleanDataTrie(words: List<Pair<String, Boolean>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with list data
     */
    fun createListDataTrie(words: List<Pair<String, List<Any>>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with map data
     */
    fun createMapDataTrie(words: List<Pair<String, Map<String, Any>>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with object data
     */
    fun createObjectDataTrie(words: List<Pair<String, Any>>): EnhancedTrieNode {
        return createCustomDataTrie(words)
    }
    
    /**
     * Creates a trie with timestamp data
     */
    fun createTimestampDataTrie(words: List<Pair<String, Long>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with priority data
     */
    fun createPriorityDataTrie(words: List<Pair<String, Int>>): EnhancedTrieNode {
        return createNumericDataTrie(words)
    }
    
    /**
     * Creates a trie with score data
     */
    fun createScoreDataTrie(words: List<Pair<String, Double>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with category data
     */
    fun createCategoryDataTrie(words: List<Pair<String, String>>): EnhancedTrieNode {
        return createCustomDataTrie(words.map { it.first to it.second as Any })
    }
    
    /**
     * Creates a trie with status data
     */
    fun createStatusDataTrie(words: List<Pair<String, String>>): EnhancedTrieNode {
        return createCategoryDataTrie(words)
    }
    
    /**
     * Creates a trie with rating data
     */
    fun createRatingDataTrie(words: List<Pair<String, Double>>): EnhancedTrieNode {
        return createScoreDataTrie(words)
    }
    
    /**
     * Creates a trie with location data
     */
    fun createLocationDataTrie(words: List<Pair<String, Any>>): EnhancedTrieNode {
        return createObjectDataTrie(words)
    }
    
    /**
     * Creates a trie with configuration data
     */
    fun createConfigDataTrie(words: List<Pair<String, String>>): EnhancedTrieNode {
        return createCategoryDataTrie(words)
    }
} 