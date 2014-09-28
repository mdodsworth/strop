package strop.collect;

import com.google.common.base.MoreObjects;

public class Node<T> {

    private final T value;
    private final Edge<T>[] edges;

    public Node(T value, Edge<T>[] edges) {
        this.value = value;
        this.edges = edges;
    }

    public T getValue() {
        return value;
    }

    public Edge<T>[] getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("edges", edges)
                .toString();
    }
}
