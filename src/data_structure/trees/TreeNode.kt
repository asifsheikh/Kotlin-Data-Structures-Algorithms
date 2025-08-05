package data_structure.trees

/**
 * TREE NODE DEFINITIONS
 * 
 * This file contains tree node definitions and basic tree structures:
 * - Binary Tree Node
 * - AVL Tree Node
 * - Tree utilities and helpers
 */

object TreeNode {
    
    // ===== BINARY TREE NODE =====
    data class BinaryTreeNode(
        var value: Int,
        var left: BinaryTreeNode? = null,
        var right: BinaryTreeNode? = null
    )
    
    // ===== AVL TREE NODE =====
    data class AVLNode(
        var value: Int,
        var left: AVLNode? = null,
        var right: AVLNode? = null,
        var height: Int = 1
    ) {
        fun getBalanceFactor(): Int {
            return (left?.height ?: 0) - (right?.height ?: 0)
        }
        
        fun updateHeight() {
            height = 1 + maxOf(left?.height ?: 0, right?.height ?: 0)
        }
    }
    
    // ===== TREE CREATION UTILITIES =====
    fun createBinaryTree(values: List<Int?>): BinaryTreeNode? {
        if (values.isEmpty() || values[0] == null) return null
        
        val root = BinaryTreeNode(values[0]!!)
        val queue = ArrayDeque<BinaryTreeNode?>()
        queue.add(root)
        
        var i = 1
        while (queue.isNotEmpty() && i < values.size) {
            val current = queue.removeFirst()
            
            if (current != null) {
                // Left child
                if (i < values.size && values[i] != null) {
                    current.left = BinaryTreeNode(values[i]!!)
                    queue.add(current.left)
                } else {
                    queue.add(null)
                }
                i++
                
                // Right child
                if (i < values.size && values[i] != null) {
                    current.right = BinaryTreeNode(values[i]!!)
                    queue.add(current.right)
                } else {
                    queue.add(null)
                }
                i++
            } else {
                // Skip two positions for null nodes
                i += 2
            }
        }
        
        return root
    }
    
    fun createBinaryTreeFromArray(arr: IntArray): BinaryTreeNode? {
        if (arr.isEmpty()) return null
        return createBinaryTree(arr.map { it })
    }
    
    // ===== TREE PRINTING UTILITIES =====
    fun printTree(root: BinaryTreeNode?) {
        if (root == null) {
            println("Empty tree")
            return
        }
        
        println("Binary Tree:")
        printTreeHelper(root, "", true)
    }
    
    private fun printTreeHelper(node: BinaryTreeNode?, prefix: String, isLeft: Boolean) {
        if (node == null) return
        
        println("$prefix${if (isLeft) "└── " else "┌── "}${node.value}")
        printTreeHelper(node.left, "$prefix${if (isLeft) "    " else "│   "}", true)
        printTreeHelper(node.right, "$prefix${if (isLeft) "    " else "│   "}", false)
    }
    
    fun printAVLTree(root: AVLNode?) {
        if (root == null) {
            println("Empty AVL tree")
            return
        }
        
        println("AVL Tree:")
        printAVLTreeHelper(root, "", true)
    }
    
    private fun printAVLTreeHelper(node: AVLNode?, prefix: String, isLeft: Boolean) {
        if (node == null) return
        
        println("$prefix${if (isLeft) "└── " else "┌── "}${node.value} (h=${node.height})")
        printAVLTreeHelper(node.left, "$prefix${if (isLeft) "    " else "│   "}", true)
        printAVLTreeHelper(node.right, "$prefix${if (isLeft) "    " else "│   "}", false)
    }
    
    // ===== TREE PROPERTIES =====
    fun getTreeHeight(root: BinaryTreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(getTreeHeight(root.left), getTreeHeight(root.right))
    }
    
    fun getAVLTreeHeight(root: AVLNode?): Int {
        return root?.height ?: 0
    }
    
    fun countNodes(root: BinaryTreeNode?): Int {
        if (root == null) return 0
        return 1 + countNodes(root.left) + countNodes(root.right)
    }
    
    fun countAVLNodes(root: AVLNode?): Int {
        if (root == null) return 0
        return 1 + countAVLNodes(root.left) + countAVLNodes(root.right)
    }
    
    fun countLeaves(root: BinaryTreeNode?): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) return 1
        return countLeaves(root.left) + countLeaves(root.right)
    }
    
    fun countInternalNodes(root: BinaryTreeNode?): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) return 0
        return 1 + countInternalNodes(root.left) + countInternalNodes(root.right)
    }
    
    // ===== TREE VALIDATION =====
    fun isCompleteBinaryTree(root: BinaryTreeNode?): Boolean {
        if (root == null) return true
        
        val queue = ArrayDeque<BinaryTreeNode?>()
        queue.add(root)
        var foundNonFull = false
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            
            if (current == null) {
                foundNonFull = true
            } else {
                if (foundNonFull) return false
                
                queue.add(current.left)
                queue.add(current.right)
            }
        }
        
        return true
    }
    
    fun isPerfectBinaryTree(root: BinaryTreeNode?): Boolean {
        val height = getTreeHeight(root)
        val expectedNodes = (1 shl height) - 1
        return countNodes(root) == expectedNodes
    }
    
    fun isFullBinaryTree(root: BinaryTreeNode?): Boolean {
        if (root == null) return true
        
        // If leaf node
        if (root.left == null && root.right == null) return true
        
        // If both children exist
        if (root.left != null && root.right != null) {
            return isFullBinaryTree(root.left) && isFullBinaryTree(root.right)
        }
        
        return false
    }
    
    // ===== SAMPLE TREES =====
    fun createSampleBinaryTree(): BinaryTreeNode {
        val root = BinaryTreeNode(1)
        root.left = BinaryTreeNode(2)
        root.right = BinaryTreeNode(3)
        root.left!!.left = BinaryTreeNode(4)
        root.left!!.right = BinaryTreeNode(5)
        root.right!!.left = BinaryTreeNode(6)
        root.right!!.right = BinaryTreeNode(7)
        return root
    }
    
    fun createSampleBST(): BinaryTreeNode {
        val root = BinaryTreeNode(5)
        root.left = BinaryTreeNode(3)
        root.right = BinaryTreeNode(7)
        root.left!!.left = BinaryTreeNode(2)
        root.left!!.right = BinaryTreeNode(4)
        root.right!!.left = BinaryTreeNode(6)
        root.right!!.right = BinaryTreeNode(8)
        return root
    }
    
    fun createSampleAVLTree(): AVLNode {
        val root = AVLNode(10)
        root.left = AVLNode(5)
        root.right = AVLNode(15)
        root.left!!.left = AVLNode(3)
        root.left!!.right = AVLNode(7)
        root.right!!.left = AVLNode(12)
        root.right!!.right = AVLNode(18)
        
        // Update heights
        root.left!!.left!!.updateHeight()
        root.left!!.right!!.updateHeight()
        root.right!!.left!!.updateHeight()
        root.right!!.right!!.updateHeight()
        root.left!!.updateHeight()
        root.right!!.updateHeight()
        root.updateHeight()
        
        return root
    }
    
    // ===== TREE SERIALIZATION =====
    fun serializeTree(root: BinaryTreeNode?): String {
        if (root == null) return "null"
        return "${root.value},${serializeTree(root.left)},${serializeTree(root.right)}"
    }
    
    fun deserializeTree(data: String): BinaryTreeNode? {
        val values = data.split(",")
        val iterator = values.iterator()
        
        fun deserializeHelper(): BinaryTreeNode? {
            if (!iterator.hasNext()) return null
            
            val value = iterator.next()
            if (value == "null") return null
            
            val node = BinaryTreeNode(value.toInt())
            node.left = deserializeHelper()
            node.right = deserializeHelper()
            return node
        }
        
        return deserializeHelper()
    }
    
    // ===== TREE TO ARRAY CONVERSION =====
    fun treeToArray(root: BinaryTreeNode?): IntArray {
        if (root == null) return intArrayOf()
        
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<BinaryTreeNode?>()
        queue.add(root)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current != null) {
                result.add(current.value)
                queue.add(current.left)
                queue.add(current.right)
            }
        }
        
        return result.toIntArray()
    }
    
    fun arrayToTree(arr: IntArray): BinaryTreeNode? {
        if (arr.isEmpty()) return null
        
        val root = BinaryTreeNode(arr[0])
        val queue = ArrayDeque<BinaryTreeNode>()
        queue.add(root)
        
        var i = 1
        while (queue.isNotEmpty() && i < arr.size) {
            val current = queue.removeFirst()
            
            if (i < arr.size) {
                current.left = BinaryTreeNode(arr[i])
                queue.add(current.left!!)
                i++
            }
            
            if (i < arr.size) {
                current.right = BinaryTreeNode(arr[i])
                queue.add(current.right!!)
                i++
            }
        }
        
        return root
    }
} 