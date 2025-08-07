# BIT MANIPULATION - Quick Reference

## Overview
Bit manipulation involves working with individual bits of data using bitwise operators. It's a powerful technique for:
- **Performance optimization**: Often faster than arithmetic operations
- **Memory efficiency**: Compact data representation
- **Low-level programming**: Direct hardware interaction
- **Competitive programming**: Essential for many algorithms

## Basic Bitwise Operators

| Operator | Symbol | Description | Example |
|----------|--------|-------------|---------|
| AND | `&` | Returns 1 if both bits are 1 | `5 & 3 = 1` |
| OR | `\|` | Returns 1 if either bit is 1 | `5 \| 3 = 7` |
| XOR | `^` | Returns 1 if bits are different | `5 ^ 3 = 6` |
| NOT | `~` | Inverts all bits | `~5 = -6` |
| Left Shift | `<<` | Shifts bits left | `5 << 1 = 10` |
| Right Shift | `>>` | Shifts bits right (signed) | `5 >> 1 = 2` |
| Unsigned Right Shift | `>>>` | Shifts bits right (unsigned) | `-5 >>> 1 = 2147483645` |

## Common Operations

### Basic Bit Operations
- **Set bit**: `num \| (1 << position)`
- **Clear bit**: `num & ~(1 << position)`
- **Toggle bit**: `num ^ (1 << position)`
- **Get bit**: `(num >> position) & 1`
- **Check if bit is set**: `(num & (1 << position)) != 0`

### Advanced Operations
- **Count set bits**: Brian Kernighan's algorithm
- **Get lowest set bit**: `num & -num`
- **Clear lowest set bit**: `num & (num - 1)`
- **Power of 2 check**: `(num & (num - 1)) == 0`
- **Next power of 2**: `1 << (32 - Integer.numberOfLeadingZeros(num))`

## Time Complexity Guide

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| Basic bitwise operations | O(1) | O(1) | AND, OR, XOR, NOT, shifts |
| Count set bits | O(log n) | O(1) | Brian Kernighan's algorithm |
| Single number in array | O(n) | O(1) | XOR all elements |
| Hamming distance | O(1) | O(1) | XOR and count bits |
| Power of 2 check | O(1) | O(1) | Bitwise AND with (n-1) |
| Gray code generation | O(2^n) | O(2^n) | Generate all n-bit gray codes |
| Subset generation | O(2^n) | O(2^n) | Generate all subsets |

## Common Use Cases

### 1. **Performance Optimization**
```kotlin
// Faster than multiplication/division
val multiplyBy2 = num << 1      // num * 2
val divideBy2 = num >> 1        // num / 2
val multiplyBy4 = num << 2      // num * 4
val modulo2 = num & 1           // num % 2
```

### 2. **Flag Management**
```kotlin
// Using bits as flags
val READ = 1 << 0    // 0001
val WRITE = 1 << 1   // 0010
val EXECUTE = 1 << 2 // 0100

val permissions = READ or WRITE  // 0011
val hasRead = (permissions and READ) != 0
```

### 3. **Set Operations**
```kotlin
// Efficient set operations
val set1 = 0b1010  // {1, 3}
val set2 = 0b1100  // {2, 3}
val union = set1 or set2        // {1, 2, 3}
val intersection = set1 and set2 // {3}
val difference = set1 and set2.inv() // {1}
```

### 4. **Competitive Programming**
- **Single Number**: Find element appearing once
- **Power of 2**: Check if number is power of 2
- **Hamming Distance**: Count different bits
- **Gray Code**: Generate gray code sequence
- **Subset Generation**: Generate all subsets

## Best Practices

### 1. **Use Constants for Magic Numbers**
```kotlin
const val INT_SIZE = 32
const val LONG_SIZE = 64
const val ALL_BITS_SET = -1
```

### 2. **Handle Negative Numbers Carefully**
```kotlin
// Right shift behavior differs for negative numbers
val positive = 5 >> 1    // 2
val negative = -5 >> 1   // -3 (arithmetic shift)
val unsigned = -5 >>> 1  // 2147483645 (logical shift)
```

### 3. **Use Built-in Functions When Available**
```kotlin
// Kotlin/Java built-in functions
val bitCount = Integer.bitCount(num)
val leadingZeros = Integer.numberOfLeadingZeros(num)
val trailingZeros = Integer.numberOfTrailingZeros(num)
val highestOneBit = Integer.highestOneBit(num)
val lowestOneBit = Integer.lowestOneBit(num)
```

### 4. **Consider Platform Differences**
- **Endianness**: Byte order matters for cross-platform code
- **Integer size**: 32-bit vs 64-bit systems
- **Sign extension**: Be careful with signed vs unsigned operations

## Common Pitfalls

### 1. **Operator Precedence**
```kotlin
// Wrong: evaluates as (a & b) == c
if (a & b == c) { ... }

// Correct: explicit parentheses
if ((a & b) == c) { ... }
```

### 2. **Overflow in Shifts**
```kotlin
// Be careful with large shift amounts
val largeShift = 1 << 32  // May cause issues
val safeShift = 1L << 32  // Use Long for large shifts
```

### 3. **Sign Extension**
```kotlin
// Right shift extends sign bit
val negative = -1 >> 1  // Still -1
val positive = -1 >>> 1 // Large positive number
```

## File Structure

```
src/bit_manipulation/
├── README.md                           # This file
├── BIT_MANIPULATION_COMPLEXITY_GUIDE.md # Detailed complexity analysis
├── BitManipulationBasics.kt           # Basic operators and concepts
├── BitManipulationOperations.kt       # Common operations
├── BitManipulationUtilities.kt        # Utility functions
└── algorithms/                        # Algorithm implementations
    ├── SingleNumber.kt
    ├── PowerOfTwo.kt
    ├── HammingDistance.kt
    ├── BitwiseArithmetic.kt
    ├── BitwiseSwap.kt
    ├── BitwiseReverse.kt
    ├── BitwiseCount.kt
    ├── BitwiseParity.kt
    ├── BitwiseNextPower.kt
    ├── BitwiseGrayCode.kt
    └── BitwiseSubsets.kt
```

## Quick Reference

### **Basic Operations**
- Set bit: `num | (1 << pos)`
- Clear bit: `num & ~(1 << pos)`
- Toggle bit: `num ^ (1 << pos)`
- Get bit: `(num >> pos) & 1`
- Check bit: `(num & (1 << pos)) != 0`

### **Common Patterns**
- Power of 2: `(n & (n - 1)) == 0`
- Lowest set bit: `n & -n`
- Clear lowest set bit: `n & (n - 1)`
- Isolate rightmost 1: `n & (-n)`
- Turn off rightmost 1: `n & (n - 1)`

### **Built-in Functions**
- `Integer.bitCount(n)`: Count set bits
- `Integer.numberOfLeadingZeros(n)`: Count leading zeros
- `Integer.numberOfTrailingZeros(n)`: Count trailing zeros
- `Integer.highestOneBit(n)`: Get highest set bit
- `Integer.lowestOneBit(n)`: Get lowest set bit

This comprehensive guide covers all essential bit manipulation concepts for competitive programming and system design interviews.
