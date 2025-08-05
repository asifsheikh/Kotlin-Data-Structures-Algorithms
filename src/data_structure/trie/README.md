# Trie Data Structure

This directory contains the trie data structure implementation broken down into multiple files for better organization and navigation.

## File Structure

### 1. `TrieCreation.kt`
Contains functions for creating different types of tries:
- **Basic Creation**: `createBasicTrie()`, `createEnhancedTrie()`
- **Specialized Tries**: `createCaseInsensitiveTrie()`, `createWeightedTrie()`
- **Application-Specific**: `createAutocompleteTrie()`, `createSpellCheckerTrie()`
- **File Operations**: `createTrieFromFile()`

### 2. `TrieOperations.kt`
Contains standard operations on tries:
- **Insertion**: `insertWord()`, `insertWordEnhanced()`
- **Search**: `searchWord()`, `searchWordEnhanced()`
- **Prefix Operations**: `startsWith()`, `getWordsWithPrefix()`, `countWordsWithPrefix()`
- **Deletion**: `deleteWord()`
- **Analysis**: `getLongestCommonPrefix()`

### 3. `TrieAlgorithms.kt`
Contains algorithms that use tries:
- **Autocomplete**: `autocomplete()` with frequency-based ranking
- **Spell Checking**: `spellCheck()` with edit distance suggestions
- **Word Search**: `wordSearch()` in 2D grids
- **Concatenation**: `longestWordBuiltFromOthers()`, `findAllConcatenatedWords()`
- **Bit Operations**: `findMaximumXOR()` using binary tries
- **Stream Processing**: `StreamChecker` class for real-time word detection

### 4. `TrieDemo.kt`
Coordinates all trie functionality for demonstration:
- `demonstrateTrieAlgorithms()` - Shows all trie capabilities

## Quick Reference

### Trie Types
- **Basic Trie**: Array-based children for lowercase letters (a-z)
- **Enhanced Trie**: HashMap-based children for any character
- **Case-Insensitive Trie**: Normalized to lowercase
- **Weighted Trie**: Includes frequency information for ranking

### Common Operations
```kotlin
// Creation
val trie = TrieCreation.createBasicTrie(listOf("hello", "world", "help"))

// Basic Operations
TrieOperations.insertWord(trie, "hero")
TrieOperations.searchWord(trie, "hello")  // true
TrieOperations.startsWith(trie, "hel")    // true
TrieOperations.getWordsWithPrefix(trie, "he")  // ["hello", "help", "hero"]

// Advanced Algorithms
val suggestions = TrieAlgorithms.autocomplete(weightedTrie, "app", 3)
val corrections = TrieAlgorithms.spellCheck(trie, "helo", 2)
```

### Time Complexity
- **Insert**: O(n) - n is word length
- **Search**: O(n) - n is word length
- **Prefix Search**: O(n + k*m) - n is prefix length, k words, m average length
- **Autocomplete**: O(n + k*m + k log k) - includes sorting
- **Spell Check**: O(n + 26^n * m) - n is word length, m is dictionary size

### Space Complexity
- **Storage**: O(m*n) - m words, n average length
- **Prefix Operations**: O(k*m) - k matching words
- **Autocomplete**: O(k*m) - k suggestions

## Common Patterns

### Basic Trie Node
```kotlin
class TrieNode {
    val children = Array<TrieNode?>(26) { null }
    var isEndOfWord = false
    var wordCount = 0
    var prefixCount = 0
}
```

### Enhanced Trie Node
```kotlin
class EnhancedTrieNode {
    val children = mutableMapOf<Char, EnhancedTrieNode>()
    var isEndOfWord = false
    var frequency = 0
    var data: Any? = null
}
```

### Prefix Search Pattern
```kotlin
fun getWordsWithPrefix(root: TrieNode, prefix: String): List<String> {
    // 1. Traverse to prefix node
    var node = root
    for (char in prefix) {
        node = node.children[char - 'a'] ?: return emptyList()
    }
    
    // 2. DFS to collect all words
    val result = mutableListOf<String>()
    dfsCollectWords(node, prefix, result)
    return result
}
```

### Autocomplete Pattern
```kotlin
fun autocomplete(root: EnhancedTrieNode, prefix: String, k: Int): List<String> {
    // 1. Find prefix node
    // 2. Collect words with frequencies
    // 3. Sort by frequency and alphabetically
    // 4. Return top k suggestions
}
```

## Usage Examples

### Basic Trie Operations
```kotlin
// Create and use a basic trie
val trie = TrieCreation.createBasicTrie(listOf("hello", "world", "help"))
TrieOperations.insertWord(trie, "hero")

println(TrieOperations.searchWord(trie, "hello"))  // true
println(TrieOperations.getWordsWithPrefix(trie, "he"))  // ["hello", "help", "hero"]
```

### Autocomplete System
```kotlin
// Create weighted trie for autocomplete
val wordFrequencies = mapOf(
    "apple" to 100,
    "application" to 80,
    "apply" to 90
)
val trie = TrieCreation.createWeightedTrie(wordFrequencies)

val suggestions = TrieAlgorithms.autocomplete(trie, "app", 2)
// Returns: ["apply", "apple"] (sorted by frequency)
```

### Spell Checker
```kotlin
// Create spell checker with corrections
val dictionary = listOf("hello", "world", "help")
val corrections = mapOf("helo" to "hello", "wrold" to "world")
val trie = TrieCreation.createSpellCheckerTrie(dictionary, corrections)

val suggestions = TrieAlgorithms.spellCheck(trie, "helo", 2)
// Returns: ["hello"] (with edit distance 1)
```

### Word Search in Grid
```kotlin
val grid = arrayOf(
    charArrayOf('o', 'a', 'a', 'n'),
    charArrayOf('e', 't', 'a', 'e'),
    charArrayOf('i', 'h', 'k', 'r')
)
val words = listOf("oath", "pea", "eat", "rain")

val foundWords = TrieAlgorithms.wordSearch(grid, words)
// Returns: ["oath", "eat"]
```

## Performance Tips

1. **Choose Right Trie Type:**
   - Use basic trie for lowercase letters only
   - Use enhanced trie for any character set
   - Use weighted trie for autocomplete with ranking

2. **Optimize for Use Case:**
   - For prefix-heavy operations, maintain prefix counts
   - For autocomplete, include frequency information
   - For spell checking, include common corrections

3. **Memory Management:**
   - Basic trie uses fixed array (26 elements)
   - Enhanced trie uses HashMap (more flexible, more memory)
   - Consider compression for large dictionaries

4. **Algorithm Selection:**
   - Use trie for prefix operations
   - Consider alternatives for exact string matching
   - Use specialized tries for specific applications 