import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Edge>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addNode(Node node) {
        graph.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node destination, double weight) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new IllegalArgumentException("Nodes must be in the graph before adding an edge.");
        }

        // Edge edge = new Edge(destination, weight);
        Edge edge = new Edge(source, destination, weight);
        graph.get(source).add(edge);
    }

    public List<Edge> getEdges(Node node) {
        return graph.get(node);
    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        for (Node node : graph.keySet()) {
            nodes.add(node);
        }
        return nodes;
    }



    public List<Edge> getEdges(){
        List<Edge> edges = new ArrayList<>();
        for (Node node : graph.keySet()) {
            // for (Edge edge : graph.get(node)) {
            //     edges.add(edge);
            // }
            edges.addAll(graph.get(node));
        }
        return edges;
    }

    public void printGraph() {
        for (Node node : graph.keySet()) {
            System.out.print(node.toString() + ": ");
            for (Edge edge : graph.get(node)) {
                System.out.print(edge.toString() + " ");
            }
            System.out.println();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph.keySet()) {
            sb.append(node.toString() + ": ");
            for (Edge edge : graph.get(node)) {
                sb.append(edge.toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
