# Singly Linked List
Implemented a singly linked list in Java from scratch.

## Operations
* **addFirst(value)** and **addLast(value)** - O(1)
* **contains(value)** - O(n)
* **remove(value)** - removes the first occurrence, O(n)
* **size()**
* **isEmpty()**

## Things I ran into
* **Removing the only element:** This caused a NullPointerException. I was checking head.next after changing head, instead of checking if head itself was null.
* **Infinite loop in remove():** When the value wasn't the next node, the loop never moved forward because I forgot to update current.

## Java notes
* Use `.equals()` to compare objects instead of `==`. `==` compares object references.
* Keeping a tail reference makes `addLast()` O(1). Without it, the list has to be traversed to find the last node.