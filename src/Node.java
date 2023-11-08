/**
 * Node
 */
public class Node {
    private int id;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Node node) {
        return this.id == node.getId();
    }

    public String toString() {
        return String.valueOf(this.id);
    }
    
}