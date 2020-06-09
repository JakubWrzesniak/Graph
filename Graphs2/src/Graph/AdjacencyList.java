package Graph;

import java.util.ArrayList;

public class AdjacencyList<T extends Comparable<T>> implements IGraph<T>{

    private Vertex<T>[] adjacencyList;
    private final boolean digraph;
    private final boolean directed;

    public AdjacencyList(int size, boolean digraph, boolean directed){
        adjacencyList = new Vertex[size];
        this.digraph = digraph;
        this.directed = directed;
    }
    public AdjacencyList(T[] vertices, Edge<T>[] edges, boolean digraph, boolean directed){
        this(vertices.length,digraph,directed);
        for(int i = 0 ; i < vertices.length;i++) {
            Vertex<T> temp =  new Vertex<T>(vertices[i]);
            temp.pos = i;
            adjacencyList[i] =  temp;
        }
        for(int i = 0 ; i < edges.length ; i++)
            addEdge(edges[i]);
    }

    @Override
    public void addVertex(Vertex<T> begin, Vertex<T> end, double weight){
        if(digraph)  begin.setWeight(weight);
        addVertex(begin);
        addVertex(end);
        int pos = containVertex(begin);
        Vertex<T> adjacent = adjacencyList[pos];
        while (adjacent.getAdjacent() != null) adjacent = adjacent.getAdjacent();
        Vertex<T> temp = new Vertex<>(end.getValue());
        for(int i = 0 ; i < adjacencyList.length;i++)
            if(adjacencyList[i].compareTo(temp)==0)
                temp.pos=i;
        adjacent.setAdjacent(temp);
        if(digraph) adjacent.weight = weight;
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if(containVertex(vertex)==-1) {
            increasingArray(adjacencyList.length + 1);
            vertex.pos = adjacencyList.length-1;
            adjacencyList[adjacencyList.length-1] = vertex;
        }
    }

    @Override
    public ArrayList<Edge<T>> getEdges() {
        ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();
        for(int i = 0;i<adjacencyList.length;i++){
            Vertex<T> actVertex = adjacencyList[i];
            while (actVertex.getAdjacent() != null) {
                edges.add(new Edge<T>(adjacencyList[i].getValue(), actVertex.getAdjacent().getValue(), actVertex.getWeight()));
                actVertex = actVertex.getAdjacent();
            }
        }
        return edges;
    }

    @Override
    public void increasingArray(int size){
        if(adjacencyList.length<size) {
            Vertex[] newArray = new Vertex[size];
            for (int i = 0; i < adjacencyList.length; i++)
                newArray[i] = adjacencyList[i];
            adjacencyList = newArray;
        }
    }

    public int containVertex(Vertex<T> value){
        for(int i = 0 ; i < adjacencyList.length ;i++) {
            if (adjacencyList[i].compareTo(value) == 0)
                return i;
        }
        return -1;
    }
    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.getBegin(),edge.getEnd(),edge.getWeight());
        if(!isDirected()) addVertex(edge.getEnd(),edge.getBegin(),edge.getWeight());
    }
    @Override
    public Vertex<T>[] getVertexList() {
        return adjacencyList;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isDigraph() {
        return digraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n").append(this.getClass()).append(" |skierowany: ").append(isDirected()).append(" |wazony : ").append(isDigraph()).append("\n");
        for(int i = 0 ; i < adjacencyList.length ; i++){
            Vertex<T> adjacent = adjacencyList[i];
            while (adjacent!= null){
                s.append(adjacent + " ");
                if(adjacent.weight != 0)
                    s.append("("+adjacent.weight+") ");
                adjacent = adjacent.getAdjacent();
            }
            s.append("\n");
        }
        return String.valueOf(s);
    }
}
