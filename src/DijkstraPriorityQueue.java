// Java implementation of Dijkstra's Algorithm using Priority Queue

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraPriorityQueue {
    int[] dist;
    private final Set<Integer> settled = new HashSet<>();
    private final PriorityQueue<Node> pq;
    private final int V; // Number of vertices
    List<List<Node>> adj;

    public DijkstraPriorityQueue(int V) {
        this.V = V;
        dist = new int[V];
        pq = new PriorityQueue<>(V, new Node());
    }

    /**
     * Function for Dijkstra's Algorithm
     * <p>
     * sets the distance to the start node to zero;
     * sets the distance to all other vertices to an infinite value;
     * selects an unmarked vertex of the graph, which is at the smallest distance from the initial node, and marks it;
     * <p>
     * the algorithm follows this scenario until all vertices are marked.
     *
     * @param adj Map with all vertices and nodes
     * @param src Source node
     */
    public void runAlgorithm(List<List<Node>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {
            // when the priority queue is empty, return
            if (pq.isEmpty())
                return;
            // remove the minimum distance node from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is finalized
            settled.add(u);

            e_Neighbours(u);
        }
    }

    /**
     * Function to process all the neighbours of the passed node
     *
     * calculates the distance to adjacent vertices, choosing the smallest distance for each estimate;
     * marks the next node;
     *
     * @param u index of passed node
     */
    private void e_Neighbours(int u) {
        int edgeDistance;
        int newDistance;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }
}

