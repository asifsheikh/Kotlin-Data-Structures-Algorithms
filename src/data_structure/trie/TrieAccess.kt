package data_structure.trie

/**
 * TRIE ACCESS - Quick Reference
 * All Kotlin trie access methods in one place
 */

object TrieAccess {
    
    /**
     * All Trie Access Methods
     * Complete reference for accessing tries in Kotlin
     */
    fun allTrieAccessMethods() {
        // Create trie within function - standalone
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val trie = BasicTrieCreation.createBasicTrie(words)
        val enhancedTrie = EnhancedTrieCreation.createEnhancedTrie(words)
        
        // === BASIC SEARCH OPERATIONS ===
        val hasApple = searchWord(trie, "apple")                  // true
        val hasOrange = searchWord(trie, "orange")                // false
        val hasApp = searchPrefix(trie, "app")                    // true
        val hasBan = searchPrefix(trie, "ban")                    // true
        val hasXyz = searchPrefix(trie, "xyz")                    // false
        
        // === PREFIX SEARCH OPERATIONS ===
        val wordsWithApp = findWordsWithPrefix(trie, "app")       // ["apple"]
        val wordsWithB = findWordsWithPrefix(trie, "b")           // ["banana", "blueberry"]
        val wordsWithC = findWordsWithPrefix(trie, "c")           // ["cherry"]
        val wordsWithX = findWordsWithPrefix(trie, "x")           // []
        
        // === COUNTING OPERATIONS ===
        val totalWords = countWords(trie)                         // 5
        val wordsWithAppPrefix = countWordsWithPrefix(trie, "app") // 1
        val wordsWithBPrefix = countWordsWithPrefix(trie, "b")    // 2
        val wordsWithCPrefix = countWordsWithPrefix(trie, "c")    // 1
        
        // === NODE ACCESS OPERATIONS ===
        val rootNode = trie                                       // Root node
        val appleNode = findNode(trie, "apple")                   // Node at end of "apple"
        val appNode = findNode(trie, "app")                       // Node at "app" (not end of word)
        val nullNode = findNode(trie, "xyz")                      // null
        
        // === CHARACTER ACCESS OPERATIONS ===
        val hasCharA = hasCharacter(trie, 'a')                    // true
        val hasCharZ = hasCharacter(trie, 'z')                    // false
        val charAChildren = getChildren(trie, 'a')                // Children of 'a' node
        val charBChildren = getChildren(trie, 'b')                // Children of 'b' node
        
        // === PATH ACCESS OPERATIONS ===
        val pathToApple = getPath(trie, "apple")                  // Path to "apple" node
        val pathToApp = getPath(trie, "app")                      // Path to "app" node
        val pathToXyz = getPath(trie, "xyz")                      // null
        
        // === DEPTH ACCESS OPERATIONS ===
        val depthOfApple = getDepth(trie, "apple")                // 5
        val depthOfApp = getDepth(trie, "app")                    // 3
        val depthOfA = getDepth(trie, "a")                        // 1
        val maxDepth = getMaxDepth(trie)                          // 9 (blueberry)
        
        // === LEAF ACCESS OPERATIONS ===
        val leafNodes = getLeafNodes(trie)                        // All end-of-word nodes
        val leafWords = getLeafWords(trie)                        // All complete words
        val nonLeafNodes = getNonLeafNodes(trie)                  // All non-end-of-word nodes
        
        // === ENHANCED TRIE ACCESS ===
        val enhancedHasApple = searchWordEnhanced(enhancedTrie, "apple") // true
        val enhancedWordsWithB = findWordsWithPrefixEnhanced(enhancedTrie, "b") // ["banana", "blueberry"]
        val enhancedCount = countWordsEnhanced(enhancedTrie)      // 5
        
        // === ITERATION ACCESS ===
        for (word in getAllWords(trie)) {
            // Process each word in trie
        }
        
        for (prefix in getAllPrefixes(trie)) {
            // Process each prefix in trie
        }
        
        // === CONDITIONAL ACCESS ===
        val wordsStartingWithVowel = findWordsWithCondition(trie) { word ->
            word.first() in "aeiou"
        }                                                        // ["apple", "apricot"]
        
        val longWords = findWordsWithCondition(trie) { word ->
            word.length > 5
        }                                                        // ["banana", "apricot", "blueberry", "cherry"]
        
        val shortWords = findWordsWithCondition(trie) { word ->
            word.length <= 5
        }                                                        // ["apple"]
        
        // === PATTERN ACCESS ===
        val wordsEndingWithE = findWordsEndingWith(trie, "e")     // ["apple", "blueberry"]
        val wordsEndingWithY = findWordsEndingWith(trie, "y")     // ["cherry"]
        val wordsEndingWithA = findWordsEndingWith(trie, "a")     // ["banana"]
        
        // === FREQUENCY ACCESS ===
        val mostFrequentPrefix = getMostFrequentPrefix(trie)      // Most common prefix
        val leastFrequentPrefix = getLeastFrequentPrefix(trie)    // Least common prefix
        val prefixFrequencies = getPrefixFrequencies(trie)        // All prefix frequencies
    }
    
    /**
     * Search for a word in basic trie
     */
    private fun searchWord(trie: TrieCreation.TrieNode, word: String): Boolean {
        var node = trie
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return false
                }
                node = node.children[index]!!
            }
        }
        return node.isEndOfWord
    }
    
    /**
     * Search for a prefix in basic trie
     */
    private fun searchPrefix(trie: TrieCreation.TrieNode, prefix: String): Boolean {
        var node = trie
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return false
                }
                node = node.children[index]!!
            }
        }
        return true
    }
    
    /**
     * Find all words with given prefix
     */
    private fun findWordsWithPrefix(trie: TrieCreation.TrieNode, prefix: String): List<String> {
        val result = mutableListOf<String>()
        var node = trie
        
        // Traverse to prefix node
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
        return result
    }
    
    /**
     * Count total words in trie
     */
    private fun countWords(trie: TrieCreation.TrieNode): Int {
        return countWordsRecursive(trie)
    }
    
    /**
     * Count words with given prefix
     */
    private fun countWordsWithPrefix(trie: TrieCreation.TrieNode, prefix: String): Int {
        var node = trie
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return 0
                }
                node = node.children[index]!!
            }
        }
        return node.wordCount
    }
    
    /**
     * Find node at given path
     */
    private fun findNode(trie: TrieCreation.TrieNode, path: String): TrieCreation.TrieNode? {
        var node = trie
        for (char in path.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return null
                }
                node = node.children[index]!!
            }
        }
        return node
    }
    
    /**
     * Check if trie has character at root
     */
    private fun hasCharacter(trie: BasicTrieCreation.TrieNode, char: Char): Boolean {
        val index = char.lowercaseChar() - 'a'
        return index in 0..25 && trie.children[index] != null
    }
    
    /**
     * Get children of character at root
     */
    private fun getChildren(trie: BasicTrieCreation.TrieNode, char: Char): BasicTrieCreation.TrieNode? {
        val index = char.lowercaseChar() - 'a'
        return if (index in 0..25) trie.children[index] else null
    }
    
    /**
     * Get path to node
     */
    private fun getPath(trie: BasicTrieCreation.TrieNode, word: String): List<BasicTrieCreation.TrieNode>? {
        val path = mutableListOf<BasicTrieCreation.TrieNode>()
        var node = trie
        path.add(node)
        
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return null
                }
                node = node.children[index]!!
                path.add(node)
            }
        }
        return path
    }
    
    /**
     * Get depth of word
     */
    private fun getDepth(trie: BasicTrieCreation.TrieNode, word: String): Int {
        var node = trie
        var depth = 0
        
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    return -1
                }
                node = node.children[index]!!
                depth++
            }
        }
        return depth
    }
    
    /**
     * Get maximum depth of trie
     */
    private fun getMaxDepth(trie: BasicTrieCreation.TrieNode): Int {
        return getMaxDepthRecursive(trie)
    }
    
    /**
     * Get all leaf nodes
     */
    private fun getLeafNodes(trie: BasicTrieCreation.TrieNode): List<BasicTrieCreation.TrieNode> {
        val leaves = mutableListOf<BasicTrieCreation.TrieNode>()
        collectLeafNodes(trie, leaves)
        return leaves
    }
    
    /**
     * Get all leaf words
     */
    private fun getLeafWords(trie: BasicTrieCreation.TrieNode): List<String> {
        return getAllWords(trie)
    }
    
    /**
     * Get all non-leaf nodes
     */
    private fun getNonLeafNodes(trie: BasicTrieCreation.TrieNode): List<BasicTrieCreation.TrieNode> {
        val nonLeaves = mutableListOf<BasicTrieCreation.TrieNode>()
        collectNonLeafNodes(trie, nonLeaves)
        return nonLeaves
    }
    
    /**
     * Get all words in trie
     */
    private fun getAllWords(trie: BasicTrieCreation.TrieNode): List<String> {
        val words = mutableListOf<String>()
        collectWords(trie, "", words)
        return words
    }
    
    /**
     * Get all prefixes in trie
     */
    private fun getAllPrefixes(trie: TrieCreation.TrieNode): List<String> {
        val prefixes = mutableListOf<String>()
        collectPrefixes(trie, "", prefixes)
        return prefixes
    }
    
    /**
     * Find words with condition
     */
    private fun findWordsWithCondition(trie: TrieCreation.TrieNode, condition: (String) -> Boolean): List<String> {
        val words = getAllWords(trie)
        return words.filter(condition)
    }
    
    /**
     * Find words ending with suffix
     */
    private fun findWordsEndingWith(trie: TrieCreation.TrieNode, suffix: String): List<String> {
        val words = getAllWords(trie)
        return words.filter { it.endsWith(suffix) }
    }
    
    /**
     * Get most frequent prefix
     */
    private fun getMostFrequentPrefix(trie: TrieCreation.TrieNode): String {
        val prefixes = getAllPrefixes(trie)
        return prefixes.maxByOrNull { countWordsWithPrefix(trie, it) } ?: ""
    }
    
    /**
     * Get least frequent prefix
     */
    private fun getLeastFrequentPrefix(trie: TrieCreation.TrieNode): String {
        val prefixes = getAllPrefixes(trie)
        return prefixes.minByOrNull { countWordsWithPrefix(trie, it) } ?: ""
    }
    
    /**
     * Get prefix frequencies
     */
    private fun getPrefixFrequencies(trie: TrieCreation.TrieNode): Map<String, Int> {
        val prefixes = getAllPrefixes(trie)
        return prefixes.associateWith { countWordsWithPrefix(trie, it) }
    }
    
    // Enhanced trie methods
    private fun searchWordEnhanced(trie: TrieCreation.EnhancedTrieNode, word: String): Boolean {
        var node = trie
        for (char in word) {
            if (!node.children.containsKey(char)) {
                return false
            }
            node = node.children[char]!!
        }
        return node.isEndOfWord
    }
    
    private fun findWordsWithPrefixEnhanced(trie: TrieCreation.EnhancedTrieNode, prefix: String): List<String> {
        val result = mutableListOf<String>()
        var node = trie
        
        for (char in prefix) {
            if (!node.children.containsKey(char)) {
                return emptyList()
            }
            node = node.children[char]!!
        }
        
        collectWordsEnhanced(node, prefix, result)
        return result
    }
    
    private fun countWordsEnhanced(trie: TrieCreation.EnhancedTrieNode): Int {
        return countWordsRecursiveEnhanced(trie)
    }
    
    // Helper methods
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
    
    private fun countWordsRecursive(node: TrieCreation.TrieNode): Int {
        var count = if (node.isEndOfWord) 1 else 0
        for (child in node.children) {
            if (child != null) {
                count += countWordsRecursive(child)
            }
        }
        return count
    }
    
    private fun getMaxDepthRecursive(node: TrieCreation.TrieNode): Int {
        var maxDepth = 0
        for (child in node.children) {
            if (child != null) {
                maxDepth = maxOf(maxDepth, getMaxDepthRecursive(child))
            }
        }
        return maxDepth + 1
    }
    
    private fun collectLeafNodes(node: TrieCreation.TrieNode, leaves: MutableList<TrieCreation.TrieNode>) {
        if (node.isEndOfWord) {
            leaves.add(node)
        }
        for (child in node.children) {
            if (child != null) {
                collectLeafNodes(child, leaves)
            }
        }
    }
    
    private fun collectNonLeafNodes(node: TrieCreation.TrieNode, nonLeaves: MutableList<TrieCreation.TrieNode>) {
        if (!node.isEndOfWord) {
            nonLeaves.add(node)
        }
        for (child in node.children) {
            if (child != null) {
                collectNonLeafNodes(child, nonLeaves)
            }
        }
    }
    
    private fun collectPrefixes(node: TrieCreation.TrieNode, prefix: String, prefixes: MutableList<String>) {
        if (prefix.isNotEmpty()) {
            prefixes.add(prefix)
        }
        for (i in 0..25) {
            if (node.children[i] != null) {
                collectPrefixes(node.children[i]!!, prefix + ('a' + i), prefixes)
            }
        }
    }
    
    private fun collectWordsEnhanced(node: TrieCreation.EnhancedTrieNode, prefix: String, result: MutableList<String>) {
        if (node.isEndOfWord) {
            result.add(prefix)
        }
        for ((char, child) in node.children) {
            collectWordsEnhanced(child, prefix + char, result)
        }
    }
    
    private fun countWordsRecursiveEnhanced(node: TrieCreation.EnhancedTrieNode): Int {
        var count = if (node.isEndOfWord) 1 else 0
        for (child in node.children.values) {
            count += countWordsRecursiveEnhanced(child)
        }
        return count
    }
} 