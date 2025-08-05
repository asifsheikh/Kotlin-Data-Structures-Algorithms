package data_structure.trees

/**
 * AVL TREE OPERATIONS
 * 
 * This file contains AVL Tree implementations:
 * - AVL Tree rotations (left, right, left-right, right-left)
 * - AVL Tree insert and delete operations
 * - Balance factor calculations
 * - Height maintenance
 */

object AVLTree {
    
    // ===== AVL TREE ROTATIONS =====
    fun rightRotate(y: TreeNode.AVLNode): TreeNode.AVLNode {
        val x = y.left!!
        val T2 = x.right
        
        // Perform rotation
        x.right = y
        y.left = T2
        
        // Update heights
        y.updateHeight()
        x.updateHeight()
        
        return x
    }
    
    fun leftRotate(x: TreeNode.AVLNode): TreeNode.AVLNode {
        val y = x.right!!
        val T2 = y.left
        
        // Perform rotation
        y.left = x
        x.right = T2
        
        // Update heights
        x.updateHeight()
        y.updateHeight()
        
        return y
    }
    
    // ===== AVL TREE INSERT =====
    fun insertAVL(root: TreeNode.AVLNode?, value: Int): TreeNode.AVLNode {
        // Perform normal BST insert
        if (root == null) {
            return TreeNode.AVLNode(value)
        }
        
        if (value < root.value) {
            root.left = insertAVL(root.left, value)
        } else if (value > root.value) {
            root.right = insertAVL(root.right, value)
        } else {
            // Duplicate values not allowed
            return root
        }
        
        // Update height of current node
        root.updateHeight()
        
        // Get balance factor
        val balance = root.getBalanceFactor()
        
        // Left Left Case
        if (balance > 1 && value < root.left!!.value) {
            return rightRotate(root)
        }
        
        // Right Right Case
        if (balance < -1 && value > root.right!!.value) {
            return leftRotate(root)
        }
        
        // Left Right Case
        if (balance > 1 && value > root.left!!.value) {
            root.left = leftRotate(root.left!!)
            return rightRotate(root)
        }
        
        // Right Left Case
        if (balance < -1 && value < root.right!!.value) {
            root.right = rightRotate(root.right!!)
            return leftRotate(root)
        }
        
        return root
    }
    
    // ===== AVL TREE DELETE =====
    fun deleteAVL(root: TreeNode.AVLNode?, value: Int): TreeNode.AVLNode? {
        if (root == null) return null
        
        // Perform normal BST delete
        if (value < root.value) {
            root.left = deleteAVL(root.left, value)
        } else if (value > root.value) {
            root.right = deleteAVL(root.right, value)
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
                val successor = findMinAVLNode(root.right!!)
                root.value = successor.value
                root.right = deleteAVL(root.right, successor.value)
            }
        }
        
        // If tree had only one node then return
        if (root == null) return null
        
        // Update height of current node
        root.updateHeight()
        
        // Get balance factor
        val balance = root.getBalanceFactor()
        
        // Left Left Case
        if (balance > 1 && root.left!!.getBalanceFactor() >= 0) {
            return rightRotate(root)
        }
        
        // Left Right Case
        if (balance > 1 && root.left!!.getBalanceFactor() < 0) {
            root.left = leftRotate(root.left!!)
            return rightRotate(root)
        }
        
        // Right Right Case
        if (balance < -1 && root.right!!.getBalanceFactor() <= 0) {
            return leftRotate(root)
        }
        
        // Right Left Case
        if (balance < -1 && root.right!!.getBalanceFactor() > 0) {
            root.right = rightRotate(root.right!!)
            return leftRotate(root)
        }
        
        return root
    }
    
    private fun findMinAVLNode(node: TreeNode.AVLNode): TreeNode.AVLNode {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }
    
    // ===== AVL TREE SEARCH =====
    fun searchAVL(root: TreeNode.AVLNode?, value: Int): TreeNode.AVLNode? {
        if (root == null || root.value == value) return root
        
        return if (value < root.value) {
            searchAVL(root.left, value)
        } else {
            searchAVL(root.right, value)
        }
    }
    
    // ===== AVL TREE VALIDATION =====
    fun isValidAVL(root: TreeNode.AVLNode?): Boolean {
        return isValidAVLHelper(root) != -1
    }
    
    private fun isValidAVLHelper(node: TreeNode.AVLNode?): Int {
        if (node == null) return 0
        
        val leftHeight = isValidAVLHelper(node.left)
        if (leftHeight == -1) return -1
        
        val rightHeight = isValidAVLHelper(node.right)
        if (rightHeight == -1) return -1
        
        val balance = leftHeight - rightHeight
        if (balance > 1 || balance < -1) return -1
        
        return maxOf(leftHeight, rightHeight) + 1
    }
    
    // ===== AVL TREE TO SORTED ARRAY =====
    fun avlToSortedArray(root: TreeNode.AVLNode?): IntArray {
        val result = mutableListOf<Int>()
        inorderTraversalAVL(root, result)
        return result.toIntArray()
    }
    
    private fun inorderTraversalAVL(node: TreeNode.AVLNode?, result: MutableList<Int>) {
        if (node == null) return
        inorderTraversalAVL(node.left, result)
        result.add(node.value)
        inorderTraversalAVL(node.right, result)
    }
    
    // ===== AVL TREE ITERATOR =====
    class AVLIterator(root: TreeNode.AVLNode?) {
        private val stack = ArrayDeque<TreeNode.AVLNode>()
        
        init {
            pushAllLeft(root)
        }
        
        private fun pushAllLeft(node: TreeNode.AVLNode?) {
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
    
    // ===== AVL TREE RANGE SUM =====
    fun rangeSumAVL(root: TreeNode.AVLNode?, low: Int, high: Int): Int {
        if (root == null) return 0
        
        var sum = 0
        
        if (root.value in low..high) {
            sum += root.value
        }
        
        if (root.value > low) {
            sum += rangeSumAVL(root.left, low, high)
        }
        
        if (root.value < high) {
            sum += rangeSumAVL(root.right, low, high)
        }
        
        return sum
    }
    
    // ===== AVL TREE CLOSEST VALUE =====
    fun closestValueAVL(root: TreeNode.AVLNode?, target: Double): Int {
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
    
    // ===== AVL TREE KTH SMALLEST =====
    fun kthSmallestAVL(root: TreeNode.AVLNode?, k: Int): Int {
        val stack = ArrayDeque<TreeNode.AVLNode>()
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
    
    // ===== AVL TREE SUCCESSOR AND PREDECESSOR =====
    fun inorderSuccessorAVL(root: TreeNode.AVLNode?, p: TreeNode.AVLNode): TreeNode.AVLNode? {
        var successor: TreeNode.AVLNode? = null
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
    
    fun inorderPredecessorAVL(root: TreeNode.AVLNode?, p: TreeNode.AVLNode): TreeNode.AVLNode? {
        var predecessor: TreeNode.AVLNode? = null
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
    
    // ===== AVL TREE BALANCE ANALYSIS =====
    fun getBalanceFactor(node: TreeNode.AVLNode?): Int {
        return node?.getBalanceFactor() ?: 0
    }
    
    fun getHeight(node: TreeNode.AVLNode?): Int {
        return node?.height ?: 0
    }
    
    fun isBalanced(node: TreeNode.AVLNode?): Boolean {
        if (node == null) return true
        
        val balance = node.getBalanceFactor()
        return balance in -1..1 && isBalanced(node.left) && isBalanced(node.right)
    }
    
    // ===== AVL TREE STATISTICS =====
    fun getAVLStatistics(root: TreeNode.AVLNode?): Map<String, Int> {
        val stats = mutableMapOf<String, Int>()
        
        fun calculateStats(node: TreeNode.AVLNode?): Int {
            if (node == null) return 0
            
            val leftHeight = calculateStats(node.left)
            val rightHeight = calculateStats(node.right)
            
            stats["totalNodes"] = stats.getOrDefault("totalNodes", 0) + 1
            stats["maxHeight"] = maxOf(stats.getOrDefault("maxHeight", 0), node.height)
            
            if (node.left == null && node.right == null) {
                stats["leafNodes"] = stats.getOrDefault("leafNodes", 0) + 1
            }
            
            return node.height
        }
        
        calculateStats(root)
        return stats
    }
} 