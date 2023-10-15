// Node class


// Singly-Linked list class
public class SLL implements Benchmarkable {
    // variables
    int size;
    Node head;
    Node tail;

    // Default constructor
    public SLL() {
        head = null;
        tail = null;
        size = 0;
    }

    // Display the contents of the list from head to tail
    @SuppressWarnings("unused")
    public void displayForwards() {
        System.out.print("Displaying Forwards: " + '\n' + "START -> ");
        Node current = head;
        while (current != null) {
            System.out.print(current + "-> ");
            current = current.getNext();    // update head
        }
        System.out.print("END" + '\n');
    }

    // Get Node by index
    private Node getNodeByIndex(int index) {
        // check if index is out of range
        if (index < 0 || index >= size) {
            Node nullNode = new Node(0);
            nullNode.setNull();
            return nullNode;
        }

        if (index == 0)
            return head;
        if (index == size - 1)
            return tail;

        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();    // update head
        }
        return node;
    }

    // Insert Node "As" tail
    public void insertLastOriginal(int val) {
        if (head == null) {    // if head is null then just call insertFirst() and save some time
            insertFirst(val);
        } else {
            Node node = new Node(val);
            node.setNext(null);
            if (tail != null) {    // assume tail is null and follow it up after the IF statement ends (avoids NPE)
                tail.setNext(node);
            }
            tail = node;
            size++;
        }
    }

    @Override
    public void build(int[] integers) {
        // populate linked list with numbers from buffer array
        for (int integer : integers) {
            insertLastOriginal(integer);
        }
    }

    // Insert Node at head
    public void insertFirst(int value) {
        // create new node, set its next as current head and previous as null, then update head
        Node node = new Node(value);
        node.setNext(head);
        if (head == null) {
            tail = node;
        }
        head = node;
        size++;
    }

    // Insert Node between tail and prior node
    public void insertLast(int value) {
        if (head == null) {    // if head is null then just call insertFirst() and save some time
            insertFirst(value);
        } else if (size >= 2) {    // insert new value before the last node if the list has at least two nodes
            Node node = new Node(value);
            node.setNext(tail);
            Node second2LastNode = getNodeByIndex(size - 2);
            second2LastNode.setNext(node);
            size++;
        } else    // insert first if list is too small
            insertFirst(value);
    }

    @Override
    public void insert(int index, int value) {
        // check if index is out of range
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Could not Mutate index: " + index + " in Linked-List of size: " + size + " .");
        }
        if (index == 0) {    // speed optimization
            insertFirst(value);
            return;
        }
        if (index == size - 1) {        // speed optimization
            insertLast(value);
            return;
        }

        // get a temporary node
        Node temp = getNodeByIndex(index - 1);
        if (!temp.isNull()) {
            // wire up the reference pointers to the new node
            Node node = new Node(value, temp.getNext());
            temp.setNext(node);
            size++;
        }
    }

    @Override
    public int read(int index) {
        // check if index is out of range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Could not Access index: " + index + " in Linked-List of size: " + size + " .");
        }

        Node node = head;
        for (long i = 0; i < index; i++) {
            node = node.getNext();    // update head
        }
        return node.getValue();
    }

    static class Node {
        // variables
        private int value;
        private boolean isNull = false;
        private Node next;

        // Default constructor
        public Node(int val) {
            value = val;
        }

        // Overloaded Constructor
        public Node(int val, Node nextNode) {
            value = val;
            next = nextNode;
        }

        // Accessor
        public boolean isNull() {
            return this.isNull;
        }

        // Accessor
        public Node getNext() {
            return next;
        }

        // Mutator
        public void setNext(Node next) {
            this.next = next;
        }

        // Accessor
        public int getValue() {
            return value;
        }

        // Mutator
        @SuppressWarnings("unused")
        public void setValue(int value) {
            this.value = value;
        }

        // Mutator
        public void setNull() {
            this.isNull = true;
        }

        // Overridden toString method
        public String toString() {
            return value + " ";
        }

    }

}
