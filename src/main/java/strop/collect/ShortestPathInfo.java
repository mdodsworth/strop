package strop.collect;

import com.google.common.base.MoreObjects;

import java.util.Arrays;
import java.util.List;

/**
 * Immutable container for all information regarding the shortest path between a node and all other supplied nodes.
 */
public class ShortestPathInfo<T> {

    private final Node<T>[] nodes;
    private final int[] pathLengths;
    private final int[] adjacentNodeIndexes;

    public ShortestPathInfo(Node<T>[] nodes, int[] pathLengths, int[] adjacentNodeIndexes) {
        this.nodes = nodes;
        this.pathLengths = pathLengths;
        this.adjacentNodeIndexes = adjacentNodeIndexes;
    }

    public Node<T>[] getNodes() {
        return nodes;
    }

    public int[] getPathLengths() {
        return pathLengths;
    }

    public List<Node<T>> getShortestPathFrom(Node<T> node) {
        // check that the given node is contained within 'nodes'
        return null;
    }

    public int getShortestPathLengthFrom(Node<T> node) {
        // check that the given node is contained within 'nodes'
        return -1;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nodes", Arrays.toString(nodes))
                .add("pathLengths", Arrays.toString(pathLengths))
                .add("adjacentNodeIndexes", Arrays.toString(adjacentNodeIndexes))
                .toString();
    }
}
