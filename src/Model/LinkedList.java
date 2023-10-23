package Model;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> lastNode1 = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getLastNode1() {
        return lastNode1;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public void addHead(T item) {
        Node<T> p = new Node<T>(item);

        if (this.head == null) {
            this.tail = p;
            this.lastNode1 = p;
        }
        p.next = head;
        this.head = p;
        this.size++;
    }

    // add tail
    public void addTail(T item) {
        Node<T> p = new Node<T>(item);
        if (this.head == null) {
            addHead(item);
        } else {
            this.tail.next = p;
            this.tail = p;
            this.size++;
        }
    }

    public void addAfter(T item, Node<T> q) {
        if (head == null) {
            addHead(item);
        } else {
            Node<T> p = new Node<>(item);
            p.next = q.next;
            q.next = p;
            if (tail == q) {
                tail = p;
            }
            this.size++;
        }
    }

    public void deleteHead() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
            this.size--;
            return;
        }

        Node<T> temp = head;
        head = head.next;
        temp.next = null;
        temp.data = null;
        this.size--;
    }

    public void deleteTail() {
        if (head == null)
            return;
        if (head == tail) {
            head = null;
            tail = null;
            this.size--;
            return;
        }

        for (Node<T> p = head; p != null; p = p.next)
        {
            if (p.next == tail)
            {
                tail = p;
                tail.next = null;
                this.size--;
                return;
            }
        }
    }

    public void deleteItem(T item) {

        if (head == null)
            return;
        while (item == head.data) {
            deleteHead();
        }
        while (item == tail.data) {
            deleteTail();
        }

        Node<T> k = null;
        Node<T> p = this.head;
        while (p != null)
        {
            if (p.data == item)
            {
                k.next = p.next;
                p = k.next;
                this.size--;
            }
            else
            {
                k = p;
                p = p.next;
            }
        }
    }

    public Node<T> findNode(T target) {
        for (Node<T> p = head; p != null; p = p.next) {
            if (p.data == target) {
                return p;
            }
        }
        return null;
    }

    public Node<T> findNodeBefore(T target) {
        Node<T> k = null;
        for (Node<T> p = head; p != null; p = p.next) {
            if (p == target) {
                return k;
            }
            k = p;
        }
        return null;
    }

    public void printList() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<T> p = head;
        while (p != null) {
            System.out.println(p.data.toString());
            System.out.println();
            p = p.next;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
    public void set(int index, T newData) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.data = newData;
    }


    public boolean isEmpty() {
        return (head == null);
    }
}
