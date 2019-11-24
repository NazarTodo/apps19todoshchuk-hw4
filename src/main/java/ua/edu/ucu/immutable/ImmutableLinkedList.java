package ua.edu.ucu.immutable;


public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private int len;

    public ImmutableLinkedList() {
        this.head = null;
        this.len = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.len) {

            throw new IndexOutOfBoundsException();

        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {

        ImmutableLinkedList newLst = copyLinkedList();

        if (!isEmpty()) {
            Node curr = newLst.head;
            while (curr.next != null) {

                curr = curr.next;
            }
            curr.next = new Node(e, null);

        } else {

            newLst.head = new Node(e, null);
        }
        newLst.len += 1;

        return newLst;
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        checkIndex(index);
        ImmutableLinkedList newLst = copyLinkedList();

        Node curr = newLst.head;
//        at which position we are now
        int counter = 1;
//        while to reach the position to insert in
        while (counter != index) {
            curr = curr.next;
            counter += 1;
        }

        Node prev = curr.next;

        curr.next = new Node(e, null);
        curr.next.next = prev;

        newLst.len += 1;

        return newLst;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList newLst = copyLinkedList();
        int ind = 0;
        if (isEmpty()) {

            newLst.head = new Node(c[0], null);
            ind = 1;
            newLst.len += 1;
        }
        Node curr = newLst.head;
        while (curr.next != null) {
            curr = curr.next;
        }

        for (int i = ind; i < c.length; i++) {
            curr.next = new Node(c[i], null);
            curr = curr.next;
            newLst.len += 1;
        }
        return newLst;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkIndex(index);
        ImmutableLinkedList newLst = copyLinkedList();
        if (index == 0) {
            Node prev = newLst.head;
            newLst.head = new Node(c[0], null);
            Node curr = newLst.head;
            newLst.len += 1;
            for (int i = 1; i < c.length; i++) {

                curr.next = new Node(c[i], null);
                curr = curr.next;
                newLst.len += 1;
            }
            curr.next = prev;
        } else {
            Node curr = newLst.head;
//        at which position we are now
            int counter = 1;
//        while to reach the position to insert in
            while (counter < index) {
                curr = curr.next;
                counter += 1;
            }
//        remembering the rest part of linked list
            Node prev = curr.next;

//        inserting elements 1 by 1

            for (int i = 0; i < c.length; i++) {

                curr.next = new Node(c[i], null);
                curr = curr.next;
                newLst.len += 1;
            }
            //        joining the rest part of linked list
            curr.next = prev;
        }


        return newLst;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        ImmutableLinkedList newLst = copyLinkedList();
        Node curr = newLst.head;
//        at which position we are now
        int counter = 0;
//        while to reach the position to insert in
        while (counter != index) {
            curr = curr.next;
            counter += 1;
        }
        return curr.data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        checkIndex(index);
        ImmutableLinkedList newLst = copyLinkedList();
        Node curr = newLst.head;
//        at which position we are now
        int counter = 0;
//        while to reach the position to remove from
        while (counter < index - 1) {
            curr = curr.next;
            counter += 1;
        }
        if (index == 1) {
            counter += 1;
        }
        if (counter == 0) {
            newLst.head = curr.next;
        } else {
            curr.next = curr.next.next;
        }
        newLst.len -= 1;
        return newLst;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        checkIndex(index);
        ImmutableLinkedList newLst = copyLinkedList();
        Node curr = newLst.head;
//        at which position we are now
        int counter = 0;
//        while to reach the position to insert in
        while (counter < index) {
            curr = curr.next;
            counter += 1;
        }

        curr.data = e;
        return newLst;
    }

    @Override
    public int indexOf(Object e) {
        Node curr = this.head;
        int ind = 0;
        while (curr != null) {
            if (curr.data.equals(e)) {
                return ind;
            }
            ind++;
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.len;
    }

    @Override
    public ImmutableLinkedList clear() {
        ImmutableLinkedList newLst = copyLinkedList();
        newLst.head = null;
        newLst.len = 0;
        return newLst;
    }

    @Override
    public boolean isEmpty() {

        return this.len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[this.len];
        int ind = 0;
        if (!isEmpty()) {
            Node curr = this.head;
            while (curr != null) {

                newArray[ind] = curr.data;

                ind++;

                curr = curr.next;
            }
        }

        return newArray;
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList newLst = copyLinkedList();
        if (!isEmpty()) {
            Node prev = newLst.head;
            newLst.head = new Node(e, null);
            newLst.head.next = prev;

        } else {
            newLst.head = new Node(e, null);
        }
        newLst.len += 1;
        return newLst;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newLst = copyLinkedList();
        if (!isEmpty()) {
            Node curr = newLst.head;
            while (curr.next != null) {

                curr = curr.next;
            }
            curr.next = new Node(e, null);

        } else {

            newLst.head = new Node(e, null);
        }
        newLst.len += 1;


        return newLst;
    }

    public Object getFirst() {
        if (!isEmpty()) {
            return this.head.data;
        }

        return -1;

    }

    public Object getLast() {
        if (!isEmpty()) {
            Node curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            return curr.data;
        }
        return -1;

    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList newLst = copyLinkedList();
        if (!isEmpty()) {
            newLst.head = newLst.head.next;
            newLst.len -= 1;
        }
        return newLst;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList newLst = copyLinkedList();
        if (newLst.len == 1) {
            newLst.head = null;
        } else if (!isEmpty()) {
            Node curr = newLst.head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;
            newLst.len -= 1;
        }
        return newLst;
    }

    public ImmutableLinkedList copyLinkedList() {
        ImmutableLinkedList newLst = new ImmutableLinkedList();
//        method to copy the existing list
        if (!isEmpty()) {

            Object[] lst = this.toArray();

            newLst.head = new Node(lst[0], null);
            newLst.len += 1;
            Node curr = newLst.head;

            for (int i = 1; i < lst.length; i++) {
                curr.next = new Node(lst[i], null);
                curr = curr.next;
                newLst.len += 1;
            }
        }
        return newLst;

    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (!isEmpty()) {
            Node curr = this.head;

            int i = 0;
            while (curr != null) {
                if (i != size() - 1) {
                    buf.append(curr.data);
                    buf.append(", ");
                } else {
                    buf.append(curr.data);
                }
                curr = curr.next;
                i++;
            }

        }

        return buf.toString();
    }

    private static class Node {
        private Node next;
        private Object data;

        private Node(Object el, Node next) {
            this.data = el;
            this.next = next;
        }
    }

}

