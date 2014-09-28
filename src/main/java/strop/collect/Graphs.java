package strop.collect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Graphs {

    public static <T> ShortestPathInfo<T> calculateShortestPath(int rootNodeIndex, Node<T>[] graph) {
        return dijkstraShortestPath(rootNodeIndex, graph);
    }

    static <T> ShortestPathInfo<T> dijkstraShortestPath(int rootNodeIndex, Node<T>[] graph) {
        int[] distancesFromRoot = new int[graph.length];
        int[] adjacentNodeIndexes = new int[graph.length];
        List<Integer> uncheckedIndexes = IntStream.range(0, graph.length).boxed().collect(Collectors.toList());


        // set the initial distances to be Integer.MAX_VALUE (in place of infinity)
        Arrays.fill(distancesFromRoot, Integer.MAX_VALUE);

        // initialization
        distancesFromRoot[rootNodeIndex] = 0;

        while (true) {
            // sort the unchecked indexes
            Collections.sort(uncheckedIndexes, (thisNode, thatNode) -> Integer.compare(distancesFromRoot[thisNode], distancesFromRoot[thatNode]));

            // if the least element in the unchecked list is Integer.MAX_VALUE, we've explored all nodes connected to the specified root
            if (uncheckedIndexes.get(0) == Integer.MAX_VALUE) { break; }

            // for each adjacent node, potentially update their distance from root
            Node<T> currentNode = graph[uncheckedIndexes.get(0)];
            Edge<T>[] edges = currentNode.getEdges();
            if (edges != null) {
                for (Edge<T> edge : edges) {
                    Node<T> neighbourNode = edge.getNode();
                    // determine the given node's index in the graph -- this can be WAY more efficient
                    int index = findNodeIndexInGraph(neighbourNode, graph);

                    // if the current length + edge length is < the current shortest path from the root node, update the distance
                    int distanceThroughCurrentNode = distancesFromRoot[uncheckedIndexes.get(0)] + edge.getLength();
                    if (distanceThroughCurrentNode < distancesFromRoot[index]) {
                        distancesFromRoot[index] = distanceThroughCurrentNode;
                    }
                }
            }

            // remove the node that we've just processed and check to see if we're done
            uncheckedIndexes.remove(0);
            if (uncheckedIndexes.size() == 0) { break; }
        }

        return new ShortestPathInfo<>(graph, distancesFromRoot, adjacentNodeIndexes);
    }

    static <T> int findNodeIndexInGraph(Node<T> node, Node<T>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].equals(node)) {
                return i;
            }
        }

        // FIXME - mdodsworth: there isn't a useful toString() for Node, so this information isn't that useful
        throw new IllegalArgumentException(String.format("unable to find node: %s in given graph %s", node, Arrays.toString(graph)));
    }
}
