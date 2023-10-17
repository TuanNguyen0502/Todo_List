package Model;

public class DoublyLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public DoublyLinkedList() {

    }

    public void addHead(T item)
    {
        Node<T> p = new Node<T>(item);

        if (head == null) {
            head = tail = p;
            head.prev = null;
            tail.next = null;
        }
        else {
            p.next = head;
            p.prev = null;
            head.prev = p;
            head = p;
        }
    }

    // add tail
    public void addTail(T item)
    {
        Node<T> p = new Node<T>(item);

        if (head == null)
        {
            head = tail = p;
            head.prev = null;
            tail.next = null;
        }
        else
        {
            tail.next = p;
            p.prev = tail;
            tail = p;
            tail.next = null;
        }
    }

    public void addAfter(T item, Node<T> q)
    {
        if (head == null)
        {
            addHead(item);
        }
        else {
            Node<T> p = new Node<>(item);
            p.prev = q;
            p.next = q.next;
            q.next.prev = p;
            q.next = p;
            if (tail == q)
            {
                tail = p;
            }
        }
    }

    public void deleteHead()
    {
        if (head == null)
        {
            return;
        }
        if (head == tail)
        {
            head = null;
            tail = null;
            return;
        }

        Node<T> temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null;
    }

    public void deleteTail()
    {
        if (head == null)
            return;
        if (head == tail)
        {
            head = null;
            tail = null;
            return;
        }

        Node<T> temp = tail;
        tail = tail.prev;
        tail.next = null;
        temp.prev = null;
    }

    public void deleteAfter(Node<T> q)
    {
        if (head == null)
            return;
        if (q == head)
        {
            deleteHead();
            return;
        }
        if (q == tail) {
            deleteTail();
        }

        Node<T> current = head;
        while (current != null && current != q)
        {
            current = current.next;
        }
        if (current == null)
        {
            System.out.println("There is no Tab you need to delete.");
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.prev = null;
        current.next = null;
    }

    public void printList()
    {
        if (head == null)
        {
            System.out.println("List is empty.");
            return;
        }
        Node<T> p = head;
        while (p != null)
        {
            System.out.println(p.data.toString());
            System.out.println();
            p = p.next;
        }
    }

    // Find Node X
    public Node<T> findNode(T target)
    {
        for (Node<T> p = head; p != null; p = p.next)
        {
            if (p == target)
            {
                return p;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return (head == null);
    }
}
