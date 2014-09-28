package strop.collect;

public class BST<T extends Comparable<T>> {

  private Node<T> root;

  public synchronized void put(T value) {
    if (root == null) {
      root = new Node<>(value, null, null);
    } else {
      Node<T> parentNode = findNodeOrParent(root, value);
      if (value.compareTo(parentNode.value) < 0) {
        parentNode.left = new Node<>(value, null, null);
      } else if (value.compareTo(parentNode.value) > 0) {
        parentNode.right = new Node<>(value, null, null);
      }
    }
  }

  public synchronized boolean contains(T value) {
    if (root == null) {
      return false;
    }

    Node<T> node = findNodeOrParent(root, value);
    return node.value.equals(value);
  }

  public synchronized void deleteMinValue() {
    if (root == null) {
      return;
    }

    Node<T> currentNode = root;
    while(currentNode.left != null) {
      currentNode = currentNode.left;
    }

    // if 
  }

  private Node<T> findNodeOrParent(Node<T> parent, T value) {
    if (value.equals(parent.value)) {
      return parent;
    }

    if (value.compareTo(parent.value) < 0) {
      if (parent.left == null) {
        return parent;
      } else {
        return findNodeOrParent(parent.left, value);
      }
    } else {
      if (parent.right == null) {
        return parent;
      } else {
        return findNodeOrParent(parent.right, value);
      }
    }
  }

  private static class Node<T> {
    private final T value;
    private Node<T> left;
    private Node<T> right;

    private Node(T value,Node<T> left, Node<T> right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }
}
