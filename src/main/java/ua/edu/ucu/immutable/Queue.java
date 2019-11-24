package ua.edu.ucu.immutable;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return this.queue.getFirst();
    }

    public Object dequeue() {
        if (!this.queue.isEmpty()) {
            Object first = this.queue.getFirst();
            this.queue = this.queue.removeFirst();
            return first;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.queue.size() == 0;
    }

    public void enqueue(Object e) {
        this.queue = this.queue.addLast(e);
    }

    @Override
    public String toString() {
        return this.queue.toString();
    }

}
