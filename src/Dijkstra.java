
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {

    private Graph graph;
    private Node sourceNode;
    private HashMap<Node, Double> distances;
    private HashMap<Node, Node> previousNodes;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.sourceNode = graph.getNodeById(1);
        this.distances = new HashMap<>();
        this.previousNodes = new HashMap<>();
    }

    public Dijkstra(Graph graph, Node sourceNode) {
        this.graph = graph;
        this.sourceNode = sourceNode;
        this.distances = new HashMap<>();
        this.previousNodes = new HashMap<>();
    }

    public void calculate() {
        // Initialize the distances and previous nodes maps
        for (Node node : graph.getNodes()) {
            distances.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
        }

        // Set the distance to the source node to 0
        distances.put(sourceNode, 0.0);

        // Create a priority queue to store the nodes that need to be visited
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((node1, node2) -> Double.compare(distances.get(node1), distances.get(node2)));
        priorityQueue.add(sourceNode);

        // While there are still nodes in the priority queue
        while (!priorityQueue.isEmpty()) {
            // Get the node with the shortest distance from the priority queue
            Node currentNode = priorityQueue.poll();

            // For each neighbor of the current node
            for (Node neighbor : graph.getNeighbors(currentNode)) {
                // Calculate the new distance to the neighbor
                double newDistance = distances.get(currentNode) + graph.getWeight(currentNode, neighbor);

                // If the new distance is shorter than the current distance to the neighbor
                if (newDistance < distances.get(neighbor)) {
                    // Update the distance and previous node of the neighbor
                    distances.put(neighbor, newDistance);
                    // distances.put(neighbor, graph.getWeight(currentNode, neighbor));
                    previousNodes.put(neighbor, currentNode);

                    // If the neighbor is not in the priority queue, add it
                    if (!priorityQueue.contains(neighbor)) {
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
    }

    public void printDistances() {
        for (Node node : distances.keySet()) {
            System.out.println("Distance from " + sourceNode.getId() + " to " + node.getId() + " is " + distances.get(node));
        }
    }

    public void printResults() {
        for (Node node : distances.keySet()) {
            System.out.println("Distance from " + sourceNode.getId() + " to " + node.getId() + " is " + distances.get(node));
            System.out.println("Previous node of " + node.getId() + " is " + previousNodes.get(node).getId());
        }
    }

    public Graph getGraph() {
        // Create a new graph to store the shortest paths
        //Add just the shortest paths
        for (Node node : distances.keySet()) {
            // newGraph.addEdge(sourceNode, node, distances.get(node));
            this.graph.addEdge(sourceNode, node, distances.get(node));
        }

        return this.graph;
    }
}

