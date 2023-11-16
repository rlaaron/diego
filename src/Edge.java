public class Edge {
    private Node source,destination;
    private Double weight;

    public Edge(Node source, Node destination, Double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public double getWeight() {
        return weight;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return String.valueOf(this.destination.getId());
    }

    
}
