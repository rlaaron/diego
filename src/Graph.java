import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    int numberOfNodes;
    HashMap<Node, HashMap<Node, Double>> adjacencyList;

    public Graph() {
        this.numberOfNodes = 0;
        this.adjacencyList = new HashMap<>();
    }


    public void addNode(Node node) {
        this.adjacencyList.put(node, new HashMap<>());
        this.numberOfNodes++;
    }

    public void addEdge(Node node1, Node node2, double weight) {
        this.adjacencyList.get(node1).put(node2, weight);
    }

    public void printGraph() {
        for (Node node : this.adjacencyList.keySet()) {
            System.out.print(node.getId() + " -> ");
            for (Node neighbor : this.adjacencyList.get(node).keySet()) {
                System.out.print(neighbor.getId() + " " + this.adjacencyList.get(node).values());
            }

            System.out.println();
        }
    }

    public int[][] createAdjacentMatrtix() {
        int[][] matrix = new int[this.numberOfNodes + 1][this.numberOfNodes + 1];
        for (Node node : this.adjacencyList.keySet()) {
            for (Node neighbor : this.adjacencyList.get(node).keySet()) {
                matrix[node.getId()][neighbor.getId()] = 1;
            }
        }
        return matrix;
    }

    public Object[][] createObjectAdjacentMatrix() {
        Object[][] matrix = new Object[numberOfNodes + 1][numberOfNodes + 1];
        for (Node node : adjacencyList.keySet()) {
            for (Node neighbor : adjacencyList.get(node).keySet()) {
                matrix[node.getId()][neighbor.getId()] = 1;
            }
        }

        // Initialize all empty spaces to 0
        for (int i = 0; i <= numberOfNodes; i++) {
            for (int j = 0; j <= numberOfNodes; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    public int[][] createWeightedAdjacentMatrtix() {
        int[][] matrix = new int[this.numberOfNodes + 1][this.numberOfNodes + 1];
        for (Node node : this.adjacencyList.keySet()) {
            for (Node neighbor : this.adjacencyList.get(node).keySet()) {
                matrix[node.getId()][neighbor.getId()] = this.adjacencyList.get(node).get(neighbor).intValue();

            }
        }
        return matrix;
    }

    public Object[][] createObjectWeightedAdjacentMatrix() {
        Object[][] matrix = new Object[numberOfNodes + 1][numberOfNodes + 1];
        for (Node node : adjacencyList.keySet()) {
            for (Node neighbor : adjacencyList.get(node).keySet()) {
                matrix[node.getId()][neighbor.getId()] = adjacencyList.get(node).get(neighbor);
            }
        }
        // Initialize all empty spaces to 0
        for (int i = 0; i <= numberOfNodes; i++) {
            for (int j = 0; j <= numberOfNodes; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = "0";
                }
            }
        }

        // add the head of the row
        for (int i = 0; i <= numberOfNodes; i++) {
            matrix[i][0] = i;
        }
        return matrix;
    }

    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public HashMap<Node, HashMap<Node, Double>> getAdjacencyList() {
        return this.adjacencyList;
    }

    public ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (Node neighbor : this.adjacencyList.get(node).keySet()) {
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : this.adjacencyList.keySet()) {
            nodes.add(node);
        }
        return nodes;
    }

    public String[] createColumnNames() {
        String[] columnNames = new String[this.numberOfNodes + 1];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = String.valueOf(i);
        }
        return columnNames;
    }

    public double getWeight(Node node1, Node node2) {
        return this.adjacencyList.get(node1).get(node2);
    }

    public Graph calculateDijkstra() {
        Dijkstra dijkstra = new Dijkstra(this);
        dijkstra.calculate();
        // dijkstra.printDistances();
        // dijkstra.printResults();
        return dijkstra.getGraph();

    }

    public Node getNodeById(int id) {
        for (Node node : this.adjacencyList.keySet()) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    public void updateGraphByIntMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Node node1 = getNodeById(i);
            for (int j = 0; j < matrix.length; j++) {
                Node node2 = getNodeById(j);
                if (matrix[i][j] != 0 && node1 != null && node2 != null) {
                    Double weight = (double) matrix[i][j];
                        this.addEdge(node1, node2, weight);
                        // System.out.println("node1: " + node1.getId() + " node2: " + node2.getId() + " weight: " + weight);
                }
            }
        }
    }

    public int[][] convertObjectMatrixToIntMatrix(Object[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix.length; j++) {
                newMatrix[i][j] = (int) matrix[i][j];
            }
        }
        return newMatrix;
    }

    public int getNumberOfVertices() {
        return this.numberOfNodes;
    }

    // public Graph calculateFloyd(){
    // Floyd floyd = new Floyd(this);
    // return floyd.floydWarshall();
    // }

    // public void calculateFloyd() {
    //     Floyd floyd = new Floyd(this);
    //     int[][] matrix = floyd.floydWarshall();
    //     this.updateGraphByIntMatrix(matrix);
    //     // System.out.println("Weighted Adjacency Matrix by floyd");
    //     // System.out.println("Length33: " + matrix.length);
    //     // for (int i = 0; i < matrix.length; i++) {
    //     //     System.out.print(i + " -> ");
    //     //     for (int j = 0; j < matrix.length; j++) {
    //     //         System.out.print(matrix[i][j] + " " + " |");

    //     //     }
    //     //     System.out.println();
    //     // }
    // }

   

    public void saveGraph(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        int[][] matrix = this.createWeightedAdjacentMatrtix();

        // nodes: {
        //     0: [10,10],  //cords
        //     1: [10,10],  //cords
        //     2: [10,10],  //cords
        //     3: [10,10],  //cords
        //     4: [10,10]  //cords
        // }


        for (int i = 0; i < matrix.length; i++) {
            writer.write(i + " -> ");
            for (int j = 0; j < matrix.length; j++) {
                writer.write(matrix[i][j] + " ");
            }
            writer.write("\n");
        }

        writer.close();
    }

    public void readAndUpdateGraph(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int[][] matrix = new int[0][0];
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(" -> ");
            int node = Integer.parseInt(parts[0]);
            String[] neighbors = parts[1].split(" ");
            if (matrix.length == 0) {
                matrix = new int[neighbors.length][neighbors.length];
            }
            for (int i = 0; i < neighbors.length; i++) {
                matrix[node][i] = Integer.parseInt(neighbors[i]);
            }
        }
        bufferedReader.close();
        this.updateGraphByIntMatrix(matrix);
    }

    // public Graph BFS(){
    //     BFS bfs = new BFS();
    //     return bfs.run(this);
    // }

    // public Boolean checkNegativeCycles(){
        
    // }




}
//TODO: LARIOS
//CREATE A LARIOS I===J
//IN FLOYD CREATE A TIMER 
//AND COMPARE FLOYD AND LARIOS AND SHOW WHAT IT'S MORRE FASTER