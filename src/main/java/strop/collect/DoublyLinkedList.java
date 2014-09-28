package strop.collect;

public class DoublyLinkedList<T> {
  
  private Node<T> first;
  private Node<T> last;
  private int length;

  public synchronized void add(T value) {
  }

  public synchronized void add(int index, T value) {
  }

  public synchronized T get(int index) {
    return null;
  }

  private final class Node<T> {
    private final T value;
    private Node<T> next;
    private Node<T> prev;

    private Node(T value, Node<T> prev, Node<T> next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }
}
