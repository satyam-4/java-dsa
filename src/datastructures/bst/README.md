# Java Binary Search Tree

This is a straightforward Binary Search Tree (BST) implementation in Java. Building this was a great refresher on tree traversal, but more importantly, a solid lesson in how Java handles object references under the hood.

## Supported Operations

- **`insert(value)`**: Iterative. Averages O(log n) time, but can degrade to O(n) worst-case if the tree becomes completely unbalanced.
- **`contains(value)`**: Iterative, same performance as `insert`.
- **`remove(value)`**: Recursive. Safely handles deleting leaf nodes, nodes with one child, and nodes with two children.
- **`findMin()`**: Returns the smallest value currently in the tree.
- **`inorderTraverse()`**: Prints the tree's values in sorted order.

## Lessons Learned & Bug Fixes

### The Core Bug: Reassigning vs. Mutating

I spent a good chunk of time stuck on a bug in my `remove()` method. My first attempt was iterative. When I found the node to delete, I simply wrote `currentNode = null`.

The problem is that this does absolutely nothing to the actual tree. It just repoints a local variable to null. It is like erasing an address on a sticky note and thinking you have demolished the actual house. The parent node in the tree still held a hard reference to the object.

I fixed this by rewriting the deletion logic to be recursive. Now, as the call stack unwinds, every level explicitly updates its own pointers: `node.left = remove(node.left, value)`. This writes directly to the parent's field, properly rewiring the tree. (Ironically, my `insert()` method worked perfectly from the start because I was doing `currentNode.left = newNode`, which correctly dereferences and writes to the tree rather than a local variable).

### The Two-Child Deletion Headache

You cannot just delete a node that has two children, or you will orphan an entire subtree. The solution is to:

1. Find the in-order successor (the smallest value in the right subtree).
2. Copy that successor's value into the node you are trying to remove.
3. Recursively remove the original successor.

Since the successor is always guaranteed to be a leaf or a node with just one child, the recursive cleanup call is a simple case.

### The Double-Decrement Bug

That two-child deletion logic led to a subtle bug: my size counter was shrinking too fast. I was decrementing `size` in the main two-child branch, and the recursive call to remove the successor was decrementing it again, counting a single physical deletion twice. I fixed this by ensuring `size` is only decremented at the exact moment a node is physically discarded from the tree.

## Java-Specific Implementation Notes

- **Comparisons**: Unlike C++, Java does not support operator overloading for `<` or `>`. To figure out where nodes belong, the values have to implement `Comparable<T>` so I can use `.compareTo()`.
- **Recursive Returns**: Designing the recursive methods to return a `Node` makes the implementation much cleaner. It allows child nodes to be reassigned directly by the parent as the recursion unwinds, completely removing the need to track separate `parentNode` references as you traverse down.