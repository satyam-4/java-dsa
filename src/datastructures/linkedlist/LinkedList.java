package datastructures.linkedlist;

public class LinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // add to the end of the list
    public void addLast(T value) {
        Node newNode = new Node(value);

        // if the list is empty
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;  // previously it was pointing to null
            tail = newNode;  // the newely added note is the tail now
        }
        size++;
    }

    public void addFirst(T value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // search by value
    public boolean contains(T value) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // delete first occurence of a value
    public boolean remove(T value) {
        // if list is empty
        if (head == null) {
            return false;
        }

        // if the value to be removed is head
        if (head.data.equals(value)) {
            head = head.next;
            if (head == null) tail = null;  // if prev head was pointing to null, that means, there was only one element in the list, and now the list is empty
            size--;
            return true;
        }

        Node current = head;
        // this loop only runs when list have atleast 2 elements
        while (current.next != null) {
            if (current.next.data.equals(value)) {
                current.next = current.next.next;
                if (current.next == null) tail = current;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println(" null ]");
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.printList();

        list.addFirst(1);  // [1 -> null]

        list.printList();

        list.addFirst(2);  // [2 -> 1 -> null]
        list.addLast(3);  // [2 -> 1 -> 3 -> null]
        list.addFirst(4);  // [4 -> 2 -> 1 -> 3 -> null]

        list.printList();

        System.out.println(list.contains(0));
        System.out.println(list.contains(3));

        list.remove(1);  // [4 -> 2 -> 3 -> null]

        list.printList();

    }
}