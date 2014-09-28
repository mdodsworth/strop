package strop.collect;

import java.util.concurrent.atomic.AtomicReference;

public class Stack<T> {

  private final AtomicReference<Node<T>> top = new AtomicReference<>();

  public Stack<T> push(T value) {
    Node<T> currentTop;
    do {
      currentTop = top.get();
    } while (!top.compareAndSet(currentTop, new Node<>(value, currentTop)));

    return this;
  }
      
  public T pop() {
    Node<T> currentTop;
    Node<T> next;
    do {
      currentTop = top.get();
      if (currentTop == null) {
        return null;
      }
      next = currentTop.next;
    } while (!top.compareAndSet(currentTop, next));

    return currentTop.value;
  }

  private static final class Node<T> {
    private final T value;
    private final Node<T> next;

    private Node(T value) {
      this.value = value;
      this.next = null;
    }

    private Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
}
