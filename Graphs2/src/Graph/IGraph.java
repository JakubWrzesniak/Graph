package Graph;

import java.util.ArrayList;

public interface IGraph<T extends Comparable<T>> {
    void addEdge(Edge<T> edge);
    void increasingArray(int size);
    void addVertex(Vertex<T> vertex);
    void addVertex(Vertex<T> begin, Vertex<T> end, double weight);
    boolean isDirected();
    boolean isDigraph();
    Vertex<T>[] getVertexList();
    ArrayList<Edge<T>> getEdges();
}
