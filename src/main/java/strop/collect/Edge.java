package strop.collect;

public class Edge<T> {

    private final int length;
    private final Node<T> node;

    public Edge(int length, Node<T> node) {
        this.length = length;
        this.node = node;
    }

    public Node<T> getNode() {
        return node;
    }

    public int getLength() {
        return length;
    }
}
