package data_structure.trees

/**
 * BINARY SEARCH TREE (BST) OPERATIONS
 * 
 * This file contains BST-specific implementations:
 * - BST validation
 * - BST search, insert, delete
 * - BST properties and utilities
 * - BST to sorted array conversion
 */

object BST {
    
    // ===== BST VALIDATION =====
    fun isValidBST(root: TreeNode.BinaryTreeNode?): Boolean {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }
    
    private fun isValidBSTHelper(node: TreeNode.BinaryTreeNode?, min: Long, max: Long): Boolean {
        if (node == null) return true
        
        if (node.value <= min || node.value >= max) return false
        
        return isValidBSTHelper(node.left, min, node.value.toLong()) &&
               isValidBSTHelper(node.right, node.value.toLong(), max)
    }
    
    // ===== BST SEARCH =====
    fun searchBST(root: TreeNode.BinaryTreeNode?, value: Int): TreeNode.BinaryTreeNode? {
        if (root == null || root.value == value) return root
        
        return if (value < root.value) {
            searchBST(root.left, value)
        } else {
            searchBST(root.right, value)
        }
    }
    
    fun searchBSTIterative(root: TreeNode.BinaryTreeNode?, value: Int): TreeNode.BinaryTreeNode? {
        var current = root
        
        while (current != null) {
            when {
                current.value == value -> return current
                value < current.value -> current = current.left
                else -> current = current.right
            }
        }
        
        return null
    }
    
    // ===== BST INSERT =====
    fun insertBST(root: TreeNode.BinaryTreeNode?, value: Int): TreeNode.BinaryTreeNode {
        if (root == null) return TreeNode.BinaryTreeNode(value)
        
        if (value < root.value) {
            root.left = insertBST(root.left, value)
        } else if (value > root.value) {
            root.right = insertBST(root.right, value)
        }
        
        return root
    }
    
    fun insertBSTIterative(root: TreeNode.BinaryTreeNode?, value: Int): TreeNode.BinaryTreeNode {
        if (root == null) return TreeNode.BinaryTreeNode(value)
        
        var current = root
        var parent: TreeNode.BinaryTreeNode? = null
        
        while (current != null) {
            parent = current
            current = if (value < current.value) current.left else current.right
        }
        
        if (value < parent!!.value) {
            parent.left = TreeNode.BinaryTreeNode(value)
        } else {
            parent.right = TreeNode.BinaryTreeNode(value)
        }
        
        return root
    }
    
    // ===== BST DELETE =====
    fun deleteBST(root: TreeNode.BinaryTreeNode?, value: Int): TreeNode.BinaryTreeNode? {
        if (root == null) return null
        
        if (value < root.value) {
            root.left = deleteBST(root.left, value)
        } else if (value > root.value) {
            root.right = deleteBST(root.right, value)
        } else {
            // Node to delete found
            
            // Case 1: Leaf node
            if (root.left == null && root.right == null) {
                return null
            }
            // Case 2: One child
            else if (root.left == null) {
                return root.right
            } else if (root.right == null) {
                return root.left
            }
            // Case 3: Two children
            else {
                val successor = findMinNode(root.right!!)
                root.value = successor.value
                root.right = deleteBST(root.right, successor.value)
            }
        }
        
        return root
    }
    
    private fun findMinNode(node: TreeNode.BinaryTreeNode): TreeNode.BinaryTreeNode {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }
    
    // ===== BST TO SORTED ARRAY =====
    fun bstToSortedArray(root: TreeNode.BinaryTreeNode?): IntArray {
        val result = mutableListOf<Int>()
        inorderTraversal(root, result)
        return result.toIntArray()
    }
    
    private fun inorderTraversal(node: TreeNode.BinaryTreeNode?, result: MutableList<Int>) {
        if (node == null) return
        inorderTraversal(node.left, result)
        result.add(node.value)
        inorderTraversal(node.right, result)
    }
    
    // ===== SORTED ARRAY TO BST =====
    fun sortedArrayToBST(nums: IntArray): TreeNode.BinaryTreeNode? {
        return sortedArrayToBSTHelper(nums, 0, nums.size - 1)
    }
    
    private fun sortedArrayToBSTHelper(nums: IntArray, left: Int, right: Int): TreeNode.BinaryTreeNode? {
        if (left > right) return null
        
        val mid = left + (right - left) / 2
        val root = TreeNode.BinaryTreeNode(nums[mid])
        
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1)
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right)
        
        return root
    }
    
    // ===== BST ITERATOR =====
    class BSTIterator(root: TreeNode.BinaryTreeNode?) {
        private val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        
        init {
            pushAllLeft(root)
        }
        
        private fun pushAllLeft(node: TreeNode.BinaryTreeNode?) {
            var current = node
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
        }
        
        fun next(): Int {
            val node = stack.removeLast()
            pushAllLeft(node.right)
            return node.value
        }
        
        fun hasNext(): Boolean {
            return stack.isNotEmpty()
        }
    }
    
    // ===== KTH SMALLEST ELEMENT =====
    fun kthSmallest(root: TreeNode.BinaryTreeNode?, k: Int): Int {
        val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        var current = root
        var count = 0
        
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            
            current = stack.removeLast()
            count++
            
            if (count == k) return current.value
            
            current = current.right
        }
        
        return -1 // k is larger than number of nodes
    }
    
    // ===== KTH LARGEST ELEMENT =====
    fun kthLargest(root: TreeNode.BinaryTreeNode?, k: Int): Int {
        val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        var current = root
        var count = 0
        val values = mutableListOf<Int>()
        
        // Inorder traversal to get sorted array
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            
            current = stack.removeLast()
            values.add(current.value)
            current = current.right
        }
        
        return if (k <= values.size) values[values.size - k] else -1
    }
    
    // ===== BST SUCCESSOR AND PREDECESSOR =====
    fun inorderSuccessor(root: TreeNode.BinaryTreeNode?, p: TreeNode.BinaryTreeNode): TreeNode.BinaryTreeNode? {
        var successor: TreeNode.BinaryTreeNode? = null
        var current = root
        
        while (current != null) {
            if (current.value > p.value) {
                successor = current
                current = current.left
            } else {
                current = current.right
            }
        }
        
        return successor
    }
    
    fun inorderPredecessor(root: TreeNode.BinaryTreeNode?, p: TreeNode.BinaryTreeNode): TreeNode.BinaryTreeNode? {
        var predecessor: TreeNode.BinaryTreeNode? = null
        var current = root
        
        while (current != null) {
            if (current.value < p.value) {
                predecessor = current
                current = current.right
            } else {
                current = current.left
            }
        }
        
        return predecessor
    }
    
    // ===== BST RANGE SUM =====
    fun rangeSumBST(root: TreeNode.BinaryTreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0
        
        var sum = 0
        
        if (root.value in low..high) {
            sum += root.value
        }
        
        if (root.value > low) {
            sum += rangeSumBST(root.left, low, high)
        }
        
        if (root.value < high) {
            sum += rangeSumBST(root.right, low, high)
        }
        
        return sum
    }
    
    // ===== BST RANGE SEARCH =====
    fun rangeSearch(root: TreeNode.BinaryTreeNode?, low: Int, high: Int): List<Int> {
        val result = mutableListOf<Int>()
        rangeSearchHelper(root, low, high, result)
        return result
    }
    
    private fun rangeSearchHelper(node: TreeNode.BinaryTreeNode?, low: Int, high: Int, result: MutableList<Int>) {
        if (node == null) return
        
        if (node.value > low) {
            rangeSearchHelper(node.left, low, high, result)
        }
        
        if (node.value in low..high) {
            result.add(node.value)
        }
        
        if (node.value < high) {
            rangeSearchHelper(node.right, low, high, result)
        }
    }
    
    // ===== BST CLOSEST VALUE =====
    fun closestValue(root: TreeNode.BinaryTreeNode?, target: Double): Int {
        var closest = root!!.value
        var current = root
        
        while (current != null) {
            closest = if (Math.abs(current.value - target) < Math.abs(closest - target)) {
                current.value
            } else {
                closest
            }
            
            current = if (target < current.value) current.left else current.right
        }
        
        return closest
    }
    
    // ===== BST MODE (MOST FREQUENT VALUE) =====
    fun findMode(root: TreeNode.BinaryTreeNode?): IntArray {
        val count = mutableMapOf<Int, Int>()
        val maxCount = intArrayOf(0)
        
        fun inorder(node: TreeNode.BinaryTreeNode?) {
            if (node == null) return
            inorder(node.left)
            count[node.value] = count.getOrDefault(node.value, 0) + 1
            maxCount[0] = maxOf(maxCount[0], count[node.value]!!)
            inorder(node.right)
        }
        
        inorder(root)
        
        return count.filter { it.value == maxCount[0] }.keys.toIntArray()
    }
} 