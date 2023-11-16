import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal {
    // Set<Edge> q;
    Graph q;
    Set<Node> vs;
    PriorityQueue<Edge> r;
    Graph g;
    Set<Node> w1;
    Set<Node> w2;

    public Kruskal(Graph g) {
        this.g = g;
        this.q = new Graph();
        this.vs = new HashSet<>();
        this.r = new PriorityQueue<>();
        this.w1 = new HashSet<>();
        this.w2 = new HashSet<>();
    }

    private void initPriorityQueue() {
        List<Edge> edges = g.getEdges();
        // Assuming Edge has a 'getWeight' method, adjust this based on your actual class
        Comparator<Edge> edgeComparator = Comparator.comparingDouble(Edge::getWeight);
        
        Collections.sort(edges, edgeComparator);
        
        for (Edge e : edges) {
            this.r.add(e);
        }
    }

    public Graph run(){
        initPriorityQueue();
        while(!r.isEmpty()){
            Edge e = r.poll();
            Node u = e.getSource();
            Node v = e.getDestination();
            if(!w1.contains(u) && !w2.contains(v)){
                q.addNode(u);
                q.addNode(v);
                q.addEdge(u, v, e.getWeight());
                w1.add(u);
                w2.add(v);
            }

        }
        return q;
    }



}
