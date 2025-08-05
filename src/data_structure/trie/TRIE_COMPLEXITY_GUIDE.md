# Trie Operations & Algorithms Complexity Guide

This document provides a comprehensive reference for time and space complexity of all trie operations and algorithms implemented in this project.

## 📊 **TRIE OPERATIONS COMPLEXITY**

### **Basic Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `insertWord()` | O(n) | O(n) | Insert word of length n |
| `searchWord()` | O(n) | O(1) | Search for word of length n |
| `startsWith()` | O(n) | O(1) | Check prefix of length n |
| `deleteWord()` | O(n) | O(1) | Delete word of length n |
| `countWordsWithPrefix()` | O(n) | O(1) | Count words with prefix |

### **Prefix Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `getWordsWithPrefix()` | O(n + k*m) | O(k*m) | n=prefix length, k=words, m=avg length |
| `getLongestCommonPrefix()` | O(n) | O(n) | n=length of shortest word |

### **Creation Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `createBasicTrie()` | O(m*n) | O(m*n) | m words, n average length |
| `createEnhancedTrie()` | O(m*n) | O(m*n) | m words, n average length |
| `createWeightedTrie()` | O(m*n) | O(m*n) | m words, n average length |
| `createTrieFromFile()` | O(m*n) | O(m*n) | m words, n average length |

---

## 🧮 **TRIE ALGORITHMS COMPLEXITY**

### **Autocomplete & Suggestions**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `autocomplete()` | O(n + k*m + k log k) | O(k*m) | n=prefix, k=suggestions, m=avg length |
| `getWordsWithPrefix()` | O(n + k*m) | O(k*m) | n=prefix length, k=words, m=avg length |

**Breakdown:**
- Traverse to prefix: O(n)
- Collect words: O(k*m)
- Sort suggestions: O(k log k)

### **Spell Checking**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `spellCheck()` | O(n + 26^n * m) | O(k*n) | n=word length, m=dictionary size, k=candidates |
| `generateEditDistanceCandidates()` | O(26^n) | O(26^n) | n=word length |
| `editDistance()` | O(n*m) | O(n*m) | n,m=word lengths |

**Intuition:**
- Generate all possible candidates with edit distance ≤ 2
- Check each candidate against trie
- Sort by actual edit distance

### **Word Search & Grid Operations**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `wordSearch()` | O(m*n * 8^maxLength) | O(k*maxLength) | m*n grid, k words, maxLength |
| `dfsWordSearch()` | O(8^maxLength) | O(maxLength) | per cell, maxLength is longest word |

**Implementation:**
- For each cell, DFS in 8 directions
- Check trie path at each step
- Use visited array to prevent cycles

### **Concatenation & Building**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `longestWordBuiltFromOthers()` | O(n*m²) | O(n*m) | n words, m average length |
| `findAllConcatenatedWords()` | O(n*m²) | O(n*m) | n words, m average length |
| `canBuildWord()` | O(m²) | O(m) | m is word length |

**Intuition:**
- For each word, check if it can be built from other words
- Use trie to find word boundaries
- Recursive approach with memoization

### **Bit Operations**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `findMaximumXOR()` | O(n*32) | O(n*32) | n numbers, 32 bits each |
| `buildBinaryTrie()` | O(n*32) | O(n*32) | n numbers, 32 bits each |
| `findMaxXORForNumber()` | O(32) | O(1) | per number, 32 bits |

**Implementation:**
- Build binary trie from all numbers
- For each number, find best opposite bit at each position
- Use trie to find maximum XOR efficiently

### **Stream Processing**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `StreamChecker.query()` | O(k*m) | O(n*m) | k words, m avg length, n total words |
| `StreamChecker` construction | O(n*m) | O(n*m) | n words, m average length |

**Implementation:**
- Maintain trie of all words
- For each character, check all possible word endings
- Use sliding window approach

---

## 🎯 **OPTIMIZATION STRATEGIES**

### **For Prefix Operations**
- Use prefix counts to avoid full traversal
- Cache common prefix results
- Use compressed tries for large datasets

### **For Autocomplete**
- Use weighted tries with frequency information
- Implement lazy loading for large suggestion sets
- Use fuzzy matching for better suggestions

### **For Spell Checking**
- Limit edit distance to 2 for practical performance
- Use phonetic algorithms for better suggestions
- Cache common misspellings

### **For Word Search**
- Use trie pruning when no words match current path
- Implement early termination for impossible paths
- Use bit manipulation for visited tracking

### **For Memory Optimization**
- Use compressed tries (Radix trees)
- Implement node sharing for common prefixes
- Use lazy loading for large dictionaries

---

## 📝 **COMMON PATTERNS**

### **Basic Trie Traversal**
```kotlin
fun traverseTrie(root: TrieNode, word: String): TrieNode? {
    var node = root
    for (char in word) {
        val index = char - 'a'
        node = node.children[index] ?: return null
    }
    return node
}
```

### **DFS Word Collection**
```kotlin
fun collectWords(node: TrieNode?, currentWord: String, result: MutableList<String>) {
    if (node == null) return
    
    if (node.isEndOfWord) {
        result.add(currentWord)
    }
    
    for (i in node.children.indices) {
        val child = node.children[i]
        if (child != null) {
            val nextChar = (i + 'a'.code).toChar()
            collectWords(child, currentWord + nextChar, result)
        }
    }
}
```

### **Prefix Matching**
```kotlin
fun findPrefixNode(root: TrieNode, prefix: String): TrieNode? {
    var node = root
    for (char in prefix) {
        val index = char - 'a'
        node = node.children[index] ?: return null
    }
    return node
}
```

### **Weighted Trie Operations**
```kotlin
fun insertWithFrequency(root: EnhancedTrieNode, word: String, frequency: Int) {
    var node = root
    for (char in word) {
        if (!node.children.containsKey(char)) {
            node.children[char] = EnhancedTrieNode()
        }
        node.prefixCount++
        node = node.children[char]!!
    }
    node.isEndOfWord = true
    node.frequency = frequency
}
```

---

## ⚡ **PERFORMANCE TIPS**

1. **Choose Right Trie Type:**
   - Basic trie for lowercase letters only
   - Enhanced trie for any character set
   - Weighted trie for autocomplete with ranking

2. **Optimize for Use Case:**
   - Use prefix counts for counting operations
   - Include frequency data for ranking
   - Use specialized tries for specific applications

3. **Memory Management:**
   - Basic trie uses fixed array (26 elements)
   - Enhanced trie uses HashMap (more flexible)
   - Consider compression for large datasets

4. **Algorithm Selection:**
   - Use trie for prefix operations
   - Consider alternatives for exact matching
   - Use specialized algorithms for specific problems

5. **Caching Strategies:**
   - Cache common prefix results
   - Store frequently accessed nodes
   - Use memoization for expensive operations

6. **Early Termination:**
   - Stop traversal when no words match
   - Use pruning for impossible paths
   - Implement lazy evaluation where possible 