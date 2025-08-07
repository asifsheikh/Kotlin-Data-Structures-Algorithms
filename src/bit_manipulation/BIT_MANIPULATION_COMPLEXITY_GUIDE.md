# BIT MANIPULATION COMPLEXITY GUIDE

## Overview
This guide provides detailed time and space complexity analysis for bit manipulation operations and algorithms. Understanding these complexities is crucial for optimizing performance in competitive programming and system design.

## Basic Bitwise Operations

### **Constant Time Operations (O(1))**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| AND (`&`) | O(1) | O(1) | Bitwise AND of two numbers |
| OR (`\|`) | O(1) | O(1) | Bitwise OR of two numbers |
| XOR (`^`) | O(1) | O(1) | Bitwise XOR of two numbers |
| NOT (`~`) | O(1) | O(1) | Bitwise NOT (complement) |
| Left Shift (`<<`) | O(1) | O(1) | Shift bits left by n positions |
| Right Shift (`>>`) | O(1) | O(1) | Arithmetic right shift |
| Unsigned Right Shift (`>>>`) | O(1) | O(1) | Logical right shift |

**Note**: These operations are hardware-accelerated and execute in a single CPU cycle.

### **Single Bit Operations (O(1))**

| Operation | Time | Space | Formula |
|-----------|------|-------|---------|
| Set bit | O(1) | O(1) | `num \| (1 << pos)` |
| Clear bit | O(1) | O(1) | `num & ~(1 << pos)` |
| Toggle bit | O(1) | O(1) | `num ^ (1 << pos)` |
| Get bit | O(1) | O(1) | `(num >> pos) & 1` |
| Check if bit is set | O(1) | O(1) | `(num & (1 << pos)) != 0` |

## Advanced Bit Operations

### **Counting Operations**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Count set bits (naive) | O(log n) | O(1) | Check each bit individually |
| Count set bits (Brian Kernighan) | O(k) | O(1) | k = number of set bits |
| Count set bits (built-in) | O(1) | O(1) | `Integer.bitCount()` |
| Count leading zeros | O(1) | O(1) | `Integer.numberOfLeadingZeros()` |
| Count trailing zeros | O(1) | O(1) | `Integer.numberOfTrailingZeros()` |

**Brian Kernighan's Algorithm**:
```kotlin
fun countSetBits(n: Int): Int {
    var count = 0
    var num = n
    while (num > 0) {
        num = num and (num - 1)  // Clear least significant set bit
        count++
    }
    return count
}
```

### **Pattern Recognition Operations**

| Operation | Time | Space | Formula |
|-----------|------|-------|---------|
| Power of 2 check | O(1) | O(1) | `(n & (n - 1)) == 0` |
| Power of 4 check | O(1) | O(1) | `(n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0` |
| Get lowest set bit | O(1) | O(1) | `n & -n` |
| Clear lowest set bit | O(1) | O(1) | `n & (n - 1)` |
| Get highest set bit | O(1) | O(1) | `Integer.highestOneBit(n)` |

## Algorithm Complexities

### **Single Number Problems**

| Problem | Time | Space | Approach |
|---------|------|-------|----------|
| Single Number I | O(n) | O(1) | XOR all elements |
| Single Number II | O(n) | O(1) | Count bits modulo 3 |
| Single Number III | O(n) | O(1) | XOR and find different bit |

**Single Number I** (element appears once, others twice):
```kotlin
fun singleNumber(nums: IntArray): Int {
    return nums.reduce { acc, num -> acc xor num }
}
```

### **Hamming Distance**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Hamming distance | O(1) | O(1) | XOR and count bits |
| Total hamming distance | O(n²) | O(1) | Compare all pairs |
| Total hamming distance (optimized) | O(n) | O(1) | Count bits at each position |

**Hamming Distance**:
```kotlin
fun hammingDistance(x: Int, y: Int): Int {
    return Integer.bitCount(x xor y)
}
```

### **Power Operations**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Check power of 2 | O(1) | O(1) | Bitwise AND with (n-1) |
| Next power of 2 | O(1) | O(1) | Use leading zeros |
| Previous power of 2 | O(1) | O(1) | Use highest one bit |

**Next Power of 2**:
```kotlin
fun nextPowerOf2(n: Int): Int {
    if (n <= 0) return 1
    return 1 shl (32 - Integer.numberOfLeadingZeros(n - 1))
}
```

### **Arithmetic Operations**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Addition without + | O(log n) | O(1) | Use XOR and AND |
| Subtraction without - | O(log n) | O(1) | Use XOR and borrow |
| Multiplication without * | O(log n) | O(1) | Russian peasant method |
| Division without / | O(log n) | O(1) | Repeated subtraction |

**Bitwise Addition**:
```kotlin
fun add(a: Int, b: Int): Int {
    var sum = a
    var carry = b
    while (carry != 0) {
        val temp = sum
        sum = sum xor carry
        carry = (temp and carry) shl 1
    }
    return sum
}
```

### **Generation Problems**

| Problem | Time | Space | Description |
|---------|------|-------|-------------|
| Gray code generation | O(2^n) | O(2^n) | Generate all n-bit gray codes |
| Subset generation | O(2^n) | O(2^n) | Generate all subsets |
| Power set | O(2^n) | O(2^n) | Generate all combinations |

**Gray Code Generation**:
```kotlin
fun grayCode(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    for (i in 0 until (1 shl n)) {
        result.add(i xor (i shr 1))
    }
    return result
}
```

## Memory Complexity Analysis

### **Space Usage Patterns**

| Pattern | Space | Description |
|---------|-------|-------------|
| In-place operations | O(1) | Modify input directly |
| Bit masks | O(1) | Use single integer as mask |
| Lookup tables | O(2^n) | Precompute results for n bits |
| Recursive solutions | O(n) | Call stack depth |

### **Memory Optimization Techniques**

1. **Use Bit Masks Instead of Arrays**:
   ```kotlin
   // Instead of: val visited = BooleanArray(32)
   var visited = 0  // Use bits as flags
   ```

2. **Combine Multiple Flags**:
   ```kotlin
   // Instead of: val flags = BooleanArray(4)
   var flags = 0  // 4 bits for 4 flags
   ```

3. **Use Long for Larger Sets**:
   ```kotlin
   // For sets larger than 32 elements
   var largeSet = 0L  // 64 bits
   ```

## Performance Optimization Tips

### **1. Use Built-in Functions**
```kotlin
// Faster than manual implementation
val bitCount = Integer.bitCount(num)  // Hardware accelerated
val leadingZeros = Integer.numberOfLeadingZeros(num)
```

### **2. Avoid Unnecessary Operations**
```kotlin
// Good: Direct bit manipulation
if ((num and 1) == 0) { /* even */ }

// Avoid: Unnecessary comparison
if (num % 2 == 0) { /* even */ }
```

### **3. Use Appropriate Data Types**
```kotlin
// For 32-bit operations
val mask = 0xFFFFFFFF.toInt()

// For 64-bit operations
val mask = 0xFFFFFFFFFFFFFFFFL
```

### **4. Cache Frequently Used Values**
```kotlin
// Precompute powers of 2
val powersOf2 = IntArray(32) { 1 shl it }
```

## Real-World Performance Considerations

### **CPU Architecture Impact**
- **Modern CPUs**: Bitwise operations are extremely fast (1 cycle)
- **ARM vs x86**: Slight differences in instruction timing
- **SIMD Instructions**: Can process multiple bits simultaneously

### **Memory Access Patterns**
- **Cache-friendly**: Bit operations are cache-efficient
- **Branch prediction**: Bit operations reduce branch mispredictions
- **Memory bandwidth**: Compact representation reduces memory usage

### **Compiler Optimizations**
- **Constant folding**: Compiler optimizes constant expressions
- **Instruction selection**: Compiler chooses optimal bit instructions
- **Loop unrolling**: Compiler may unroll bit-counting loops

## Common Complexity Patterns

### **O(1) Operations**
- All basic bitwise operations
- Single bit manipulations
- Pattern recognition (power of 2, etc.)

### **O(log n) Operations**
- Counting set bits (Brian Kernighan)
- Bitwise arithmetic
- Finding highest/lowest set bit

### **O(n) Operations**
- Processing all bits in a number
- Single number problems
- Array-based bit operations

### **O(2^n) Operations**
- Generating all combinations
- Gray code generation
- Subset generation

This complexity guide helps in choosing the most efficient bit manipulation approach for any given problem.
