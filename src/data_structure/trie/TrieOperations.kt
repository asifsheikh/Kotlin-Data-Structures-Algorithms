package data_structure.trie

/**
 * TRIE OPERATIONS
 * 
 * This file contains standard operations on tries including
 * inserting, searching, prefix matching, and other basic trie operations.
 * 
 * COMMON TRIE OPERATIONS:
 * - Insert: Add a word to the trie
 * - Search: Check if a word exists in the trie
 * - Prefix Search: Find all words with a given prefix
 * - Delete: Remove a word from the trie
 * - Count: Get number of words with a prefix
 */

object TrieOperations {
    
    // Use the TrieNode class from TrieCreation directly
    
    /**
     * Inserts a word into a basic trie
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the word:
     *    - Calculate index: char - 'a'
     *    - Create child node if it doesn't exist
     *    - Move to child node
     * 3. Mark the last node as end of word
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the word
     * SPACE COMPLEXITY: O(n) - worst case when no common prefixes
     * 
     * @param root Root node of the trie
     * @param word Word to insert
     */
    fun insertWord(root: TrieCreation.TrieNode, word: String) {
        var node = root
        
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                if (node.children[index] == null) {
                    node.children[index] = TrieCreation.TrieNode()
                }
                node.prefixCount++
                node = node.children[index]!!
            }
        }
        
        node.isEndOfWord = true
        node.wordCount++
    }
    
    /**
     * Inserts a word into an enhanced trie
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the word:
     *    - Create child node if it doesn't exist
     *    - Move to child node
     * 3. Mark the last node as end of word
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the word
     * SPACE COMPLEXITY: O(n) - worst case when no common prefixes
     * 
     * @param root Root node of the enhanced trie
     * @param word Word to insert
     */
    fun insertWordEnhanced(root: TrieCreation.EnhancedTrieNode, word: String) {
        var node = root
        
        for (char in word) {
            if (!node.children.containsKey(char)) {
                node.children[char] = TrieCreation.EnhancedTrieNode()
            }
            node.prefixCount++
            node = node.children[char]!!
        }
        
        node.isEndOfWord = true
        node.wordCount++
    }
    
    /**
     * Searches for a word in a basic trie
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the word:
     *    - Calculate index: char - 'a'
     *    - If child doesn't exist, return false
     *    - Move to child node
     * 3. Return true if last node is marked as end of word
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the word
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the trie
     * @param word Word to search for
     * @return true if word exists, false otherwise
     */
    fun searchWord(root: TrieCreation.TrieNode, word: String): Boolean {
        var node = root
        
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                node = node.children[index] ?: return false
            }
        }
        
        return node.isEndOfWord
    }
    
    /**
     * Searches for a word in an enhanced trie
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the word:
     *    - If child doesn't exist, return false
     *    - Move to child node
     * 3. Return true if last node is marked as end of word
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the word
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the enhanced trie
     * @param word Word to search for
     * @return true if word exists, false otherwise
     */
    fun searchWordEnhanced(root: TrieCreation.EnhancedTrieNode, word: String): Boolean {
        var node = root
        
        for (char in word) {
            node = node.children[char] ?: return false
        }
        
        return node.isEndOfWord
    }
    
    /**
     * Checks if any word in the trie starts with the given prefix
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the prefix:
     *    - Calculate index: char - 'a'
     *    - If child doesn't exist, return false
     *    - Move to child node
     * 3. Return true if we can traverse the entire prefix
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the prefix
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the trie
     * @param prefix Prefix to check
     * @return true if any word starts with the prefix, false otherwise
     */
    fun startsWith(root: TrieCreation.TrieNode, prefix: String): Boolean {
        var node = root
        
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                node = node.children[index] ?: return false
            }
        }
        
        return true
    }
    
    /**
     * Checks if any word in the enhanced trie starts with the given prefix
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. For each character in the prefix:
     *    - If child doesn't exist, return false
     *    - Move to child node
     * 3. Return true if we can traverse the entire prefix
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the prefix
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the enhanced trie
     * @param prefix Prefix to check
     * @return true if any word starts with the prefix, false otherwise
     */
    fun startsWithEnhanced(root: TrieCreation.EnhancedTrieNode, prefix: String): Boolean {
        var node = root
        
        for (char in prefix) {
            node = node.children[char] ?: return false
        }
        
        return true
    }
    
    /**
     * Gets all words that start with the given prefix
     * 
     * ALGORITHM:
     * 1. Traverse to the node representing the prefix
     * 2. If prefix doesn't exist, return empty list
     * 3. Use DFS to collect all words from that node
     * 4. For each child, recursively collect words
     * 
     * TIME COMPLEXITY: O(n + k * m) - n is prefix length, k is number of words, m is average word length
     * SPACE COMPLEXITY: O(k * m) - to store all matching words
     * 
     * @param root Root node of the trie
     * @param prefix Prefix to search for
     * @return List of all words starting with the prefix
     */
    fun getWordsWithPrefix(root: TrieCreation.TrieNode, prefix: String): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                node = node.children[index] ?: return emptyList()
            }
        }
        
        val result = mutableListOf<String>()
        dfsCollectWords(node, prefix, result)
        return result
    }
    
    /**
     * Gets all words that start with the given prefix in enhanced trie
     * 
     * ALGORITHM:
     * 1. Traverse to the node representing the prefix
     * 2. If prefix doesn't exist, return empty list
     * 3. Use DFS to collect all words from that node
     * 4. For each child, recursively collect words
     * 
     * TIME COMPLEXITY: O(n + k * m) - n is prefix length, k is number of words, m is average word length
     * SPACE COMPLEXITY: O(k * m) - to store all matching words
     * 
     * @param root Root node of the enhanced trie
     * @param prefix Prefix to search for
     * @return List of all words starting with the prefix
     */
    fun getWordsWithPrefixEnhanced(root: TrieCreation.EnhancedTrieNode, prefix: String): List<String> {
        var node = root
        
        // Traverse to the prefix node
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        
        val result = mutableListOf<String>()
        dfsCollectWordsEnhanced(node, prefix, result)
        return result
    }
    
    /**
     * Counts the number of words with a given prefix
     * 
     * ALGORITHM:
     * 1. Traverse to the node representing the prefix
     * 2. If prefix doesn't exist, return 0
     * 3. Return the prefixCount of that node
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the prefix
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the trie
     * @param prefix Prefix to count
     * @return Number of words with the given prefix
     */
    fun countWordsWithPrefix(root: TrieCreation.TrieNode, prefix: String): Int {
        var node = root
        
        for (char in prefix.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                node = node.children[index] ?: return 0
            }
        }
        
        return node.prefixCount
    }
    
    /**
     * Counts the number of words with a given prefix in enhanced trie
     * 
     * ALGORITHM:
     * 1. Traverse to the node representing the prefix
     * 2. If prefix doesn't exist, return 0
     * 3. Return the prefixCount of that node
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the prefix
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the enhanced trie
     * @param prefix Prefix to count
     * @return Number of words with the given prefix
     */
    fun countWordsWithPrefixEnhanced(root: TrieCreation.EnhancedTrieNode, prefix: String): Int {
        var node = root
        
        for (char in prefix) {
            node = node.children[char] ?: return 0
        }
        
        return node.prefixCount
    }
    
    /**
     * Deletes a word from the trie
     * 
     * ALGORITHM:
     * 1. Find the word in the trie
     * 2. If word doesn't exist, return false
     * 3. Mark the end node as not end of word
     * 4. Decrement prefix counts along the path
     * 5. Remove nodes that are no longer needed
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the word
     * SPACE COMPLEXITY: O(1) - only uses constant extra space
     * 
     * @param root Root node of the trie
     * @param word Word to delete
     * @return true if word was deleted, false if word didn't exist
     */
    fun deleteWord(root: TrieCreation.TrieNode, word: String): Boolean {
        if (!searchWord(root, word)) return false
        
        var node = root
        val path = mutableListOf<TrieCreation.TrieNode>()
        
        // Traverse to the word and collect path
        for (char in word.lowercase()) {
            if (char.isLetter()) {
                val index = char - 'a'
                path.add(node)
                node = node.children[index]!!
            }
        }
        
        // Mark as not end of word
        node.isEndOfWord = false
        node.wordCount--
        
        // Decrement prefix counts
        for (pathNode in path) {
            pathNode.prefixCount--
        }
        
        return true
    }
    
    /**
     * Gets the longest common prefix of all words in the trie
     * 
     * ALGORITHM:
     * 1. Start from root node
     * 2. While current node has exactly one child and is not end of word:
     *    - Move to the child
     *    - Add character to result
     * 3. Return the collected prefix
     * 
     * TIME COMPLEXITY: O(n) - n is the length of the shortest word
     * SPACE COMPLEXITY: O(n) - to store the result string
     * 
     * @param root Root node of the trie
     * @return Longest common prefix of all words
     */
    fun getLongestCommonPrefix(root: TrieCreation.TrieNode): String {
        val result = StringBuilder()
        var node = root
        
        while (node.children.count { it != null } == 1 && !node.isEndOfWord) {
            val childIndex = node.children.indexOfFirst { it != null }
            result.append((childIndex + 'a'.code).toChar())
            node = node.children[childIndex]!!
        }
        
        return result.toString()
    }
    
    /**
     * Helper function to collect words using DFS
     */
    private fun dfsCollectWords(node: TrieCreation.TrieNode?, currentWord: String, result: MutableList<String>) {
        if (node == null) return
        
        if (node.isEndOfWord) {
            result.add(currentWord)
        }
        
        for (i in node.children.indices) {
            val child = node.children[i]
            if (child != null) {
                val nextChar = (i + 'a'.code).toChar()
                dfsCollectWords(child, currentWord + nextChar, result)
            }
        }
    }
    
    /**
     * Helper function to collect words using DFS in enhanced trie
     */
    private fun dfsCollectWordsEnhanced(node: TrieCreation.EnhancedTrieNode?, currentWord: String, result: MutableList<String>) {
        if (node == null) return
        
        if (node.isEndOfWord) {
            result.add(currentWord)
        }
        
        for ((char, child) in node.children) {
            dfsCollectWordsEnhanced(child, currentWord + char, result)
        }
    }
    
    /**
     * Demonstrates standard trie operations
     */
    fun demonstrateTrieOperations() {
        println("=== TRIE OPERATIONS DEMONSTRATION ===\n")
        
        // Create a basic trie
        val basicTrie = TrieCreation.createBasicTrie(listOf("hello", "world", "help", "hero", "heroic"))
        
        // 1. Search operations
        println("1. SEARCH OPERATIONS")
        println("Search 'hello': ${searchWord(basicTrie, "hello")}")
        println("Search 'help': ${searchWord(basicTrie, "help")}")
        println("Search 'world': ${searchWord(basicTrie, "world")}")
        println("Search 'hell': ${searchWord(basicTrie, "hell")}")
        println()
        
        // 2. Prefix operations
        println("2. PREFIX OPERATIONS")
        println("StartsWith 'hel': ${startsWith(basicTrie, "hel")}")
        println("StartsWith 'xyz': ${startsWith(basicTrie, "xyz")}")
        println("Words with prefix 'hel': ${getWordsWithPrefix(basicTrie, "hel")}")
        println("Words with prefix 'hero': ${getWordsWithPrefix(basicTrie, "hero")}")
        println()
        
        // 3. Count operations
        println("3. COUNT OPERATIONS")
        println("Words with prefix 'h': ${countWordsWithPrefix(basicTrie, "h")}")
        println("Words with prefix 'he': ${countWordsWithPrefix(basicTrie, "he")}")
        println("Words with prefix 'hel': ${countWordsWithPrefix(basicTrie, "hel")}")
        println()
        
        // 4. Longest common prefix
        println("4. LONGEST COMMON PREFIX")
        val prefixTrie = TrieCreation.createBasicTrie(listOf("flower", "flow", "flight"))
        println("Longest common prefix: '${getLongestCommonPrefix(prefixTrie)}'")
        println()
        
        // 5. Enhanced trie operations
        println("5. ENHANCED TRIE OPERATIONS")
        val enhancedTrie = TrieCreation.createEnhancedTrie(listOf("apple", "application", "apply"))
        println("Enhanced trie search 'apple': ${searchWordEnhanced(enhancedTrie, "apple")}")
        println("Enhanced trie words with prefix 'app': ${getWordsWithPrefixEnhanced(enhancedTrie, "app")}")
        println()
        
        println("=== TRIE OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
}