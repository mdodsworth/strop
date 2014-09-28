package strop.collect;

public class Queue<T> {
 
  private Node<T> head;
  private Node<T> tail;

  public synchronized Queue<T> add(T value) {
    if (head == null) {
      head = tail = new Node<>(value);
    } else {
      tail.next = new Node<>(value);
      tail = tail.next;
    }

    return this;
  }

  public synchronized T poll() {
    if (head == null) {
      return null;
    } else {
      T currentValue = head.value;
      head = head.next;
      return currentValue;
    }
  }

  public synchronized T peek() {
    if (head == null) {
      return null;
    } else {
      return head.value;
    }
  }

  private static class Node<T> {
    private final T value;
    private Node<T> next;

    private Node(T value) {
      this.value = value;
    }

    private Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
}
