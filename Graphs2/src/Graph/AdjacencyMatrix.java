package Graph;

import java.util.ArrayList;

public class AdjacencyMatrix<T extends Comparable<T>> implements IGraph<T> {
    Double[][] adjacencyMatrix;
    Vertex<T>[] vertices;
    private final boolean digraph;
    private final boolean directed;

    public AdjacencyMatrix(int numOfVertices, boolean digraph, boolean directed){
        adjacencyMatrix = new Double[numOfVertices][numOfVertices];
        vertices = new Vertex[numOfVertices];
        if(digraph) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    if (i != j) adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
                    else adjacencyMatrix[i][j] = 0.0;
                }
            }
        }
        this.digraph = digraph;
        this.directed = directed;
    }

    public AdjacencyMatrix(T[] vertices, Edge<T>[] edges, boolean digraph, boolean directed){
        this(vertices.length,digraph,directed);
        for(int i = 0 ; i < vertices.length ;i++)
            this.vertices[i] = new Vertex<T>(vertices[i]);
        int pos = 0;
        while (pos<vertices.length) this.vertices[pos].setPos(pos++);
        for(int i = 0 ; i < edges.length;i++){
            addEdge(edges[i]);
        }
    }

    public double getWeight(int i , int j){
        return adjacencyMatrix[i][j];
    }


    @Override
    public void addVertex(Vertex<T> begin, Vertex<T> end, double weight) throws IndexOutOfBoundsException{
        addVertex(begin);
        addVertex(end);
        int posB=-1;
        int posE=-1;
        for(int i = 0 ; i < vertices.length ;i++) {
            if (vertices[i].compareTo(begin)==0) posB = i;
            if (vertices[i].compareTo(end)==0) posE = i;
        }
        if(digraph) adjacencyMatrix[posB][posE] = weight;
        else adjacencyMatrix[posB][posE] = 1.0;
    }

    @Override
    public Vertex<T>[] getVertexList() {
        return vertices;
    }

    @Override
    public void increasingArray(int size) {
        if(adjacencyMatrix.length<size) {
            Double[][] newArray = new Double[size][size];
            Vertex[] newVertexArray = new Vertex[size];
            for (int i = 0; i < adjacencyMatrix.length; i++){
                for(int j = 0 ; j < adjacencyMatrix[i].length;j++)
                    newArray[i][j] = adjacencyMatrix[i][j];
            }
            for (int i = 0 ; i < vertices.length;i++) newVertexArray[i] = vertices[i];
            adjacencyMatrix = newArray;
            vertices = newVertexArray;
        }
    }

    public boolean containVertex(T value) {
        for(int i = 0 ; i < vertices.length ;i++) if(vertices[i].getValue().equals(value)) return true;
        return false;
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if(!containVertex(vertex.getValue())){
            increasingArray(adjacencyMatrix.length+1);
            vertex.setPos(vertices.length-1);
            if(isDigraph())
                for(int i = 0 ; i < adjacencyMatrix.length;i++){
                    adjacencyMatrix[adjacencyMatrix.length-1][i] = Double.POSITIVE_INFINITY;
                    adjacencyMatrix[i][adjacencyMatrix.length-1] = Double.POSITIVE_INFINITY;
                }

            vertices[vertices.length-1] = vertex;
        }

    }
    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.getBegin(),edge.getEnd(),edge.getWeight());
        if(!isDirected())addVertex(edge.getEnd(),edge.getBegin(),edge.getWeight());
    }

    public boolean isDigraph() {
        return digraph;
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    public ArrayList<Edge<T>> getEdges() {
        ArrayList<Edge<T>> edges = new ArrayList<>();
        for(int i = 0 ; i < adjacencyMatrix.length;i++){
            for(int j = 0 ; j < adjacencyMatrix[i].length;j++)
                if(adjacencyMatrix[i][j]!= null)
                if(adjacencyMatrix[i][j] !=0.0 && adjacencyMatrix[i][j]<Double.POSITIVE_INFINITY)
                    edges.add(new Edge<T>(vertices[i].getValue(),vertices[j].getValue(),adjacencyMatrix[i][j]));
        }
        return edges;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n").append(this.getClass()).append(" |skierowany: ").append(isDirected()).append(" |wazony : ").append(isDigraph()).append("\n");
        s.append("    ");
        for(int i = 0 ; i < adjacencyMatrix.length;i++)
            s.append(vertices[i].getValue()).append("     ");
        s.append("\n");
        for(int i = 0 ; i < adjacencyMatrix.length ;i++){
            s.append(vertices[i].getValue()).append("| ");
            for(int j = 0 ; j < adjacencyMatrix[i].length; j++){
                s.append(" ");
                if(adjacencyMatrix[j][i] == null)
                    s.append("0.0");
                else if(adjacencyMatrix[j][i]==Double.POSITIVE_INFINITY)
                    s.append("INF");
                else
                    s.append(adjacencyMatrix[j][i]);
                s.append("  ");
            }
            s.append("\n");
        }
        return String.valueOf(s);
    }
}
