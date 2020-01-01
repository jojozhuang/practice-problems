package johnny.problem;

import java.util.HashMap;

public class LRU {
    public class Node {
        public String key;
        public byte[] value;
        public Node prev;
        public Node next;

        public Node(String key, byte[] value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity; // maximum size
    private int size;     // actual size
    private int quantity; // maximum number of images
    private int count;    // actual number of images
    private HashMap<String, Node> map; // key, node
    private Node head;                 // The latest accessed element
    private Node tail;                 // The least recently used element

    public LRU(int capacity, int quantity) {
        this.capacity = capacity;
        this.size = 0;
        this.quantity = quantity;
        this.count = 0;
        this.map = new HashMap<>();
        this.head = new Node("head", new byte[]{});
        this.tail = new Node("tail", new byte[]{});
        head.next = tail;
        tail.prev = head;
    }

    public void add(String key, byte[] value) {
        if (map.containsKey(key)) {
            return;
        }

        if (value.length > capacity) {
            return;
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);

        // move new node to head
        moveToHead(newNode);

        count++;
        size += value.length;
        // check size and count
        while (size > capacity || count > quantity) {
            if (size > capacity) {
                System.out.println("Current size: " + size + " exceeds the maximum capacity: " + capacity + ", delete the least recently used image:" + tail.prev.key);
            } else {
                System.out.println("Current count: " + count + " exceeds the maximum quantity: " + quantity + ", delete the least recently used image:" + tail.prev.key);
            }
            size -= tail.prev.value.length;
            count--;
            map.remove(tail.prev.key);
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
        }
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public byte[] get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }

        // remove current
        Node current = map.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current node to head
        moveToHead(current);

        return map.get(key).value;
    }

    public int size() {
        return this.size;
    }

    public int count() {
        return this.map.size();
    }

    public String toString() {
        return "capacity=" + capacity + ", size=" + size + ", quantity=" + quantity + ", count=" + count;
    }

    private void moveToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }
}