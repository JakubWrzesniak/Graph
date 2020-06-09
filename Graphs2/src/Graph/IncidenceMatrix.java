package Graph;

import java.util.ArrayList;

public class IncidenceMatrix<T extends Comparable<T>> implements IGraph<T> {

    private Integer[][] incidenceMatrix;
    private Double [] weight;
    private Vertex<T>[] vertices;
    private boolean digraph;
    private boolean directed;


    public IncidenceMatrix(T[] vertices, Edge<T>[] edges, boolean digraph, boolean directed){
        this.vertices = new Vertex[vertices.length];
        this.digraph = digraph;
        this.directed = directed;
        for(int i = 0 ; i < vertices.length ;i++) this.vertices[i] = new Vertex<>(vertices[i]);
        incidenceMatrix = new Integer[vertices.length][edges.length];
        if(digraph) weight = new Double[edges.length];
        int pos = 0;
        for(Vertex<T> vertex : this.vertices) vertex.setPos(pos++);
        initEdges(edges);


    }

    @Override
    public void addEdge(Edge<T> edge) {
        increasingArrayEdge(edge.getWeight());
        addVertex(edge.getBegin(),edge.getEnd(),edge.getWeight());
    }

    private void initEdges(Edge<T>[] edges){
        for(int i = 0 ; i < edges.length ;i++) {
            addVertex(edges[i].getBegin());
            addVertex(edges[i].getEnd());
            for (Vertex<T> vertex1 : vertices) {
                if (edges[i].getBegin().compareTo(vertex1) == 0)
                    if (isDirected()) incidenceMatrix[vertex1.pos][i] = -1;
                    else incidenceMatrix[vertex1.pos][i] = 1;
                else if (edges[i].getEnd().compareTo(vertex1) == 0) {
                    incidenceMatrix[vertex1.pos][i] = 1;
                }
                if (isDigraph()) {
                    weight[i] = edges[i].getWeight();
                }
            }
        }
    }

    protected void increasingArrayEdge(double newWeight){
        Integer[][] newArray = new Integer[incidenceMatrix.length][incidenceMatrix[0].length+1];

        if(isDigraph()){
            Double[] newWeightArray = new Double[incidenceMatrix[0].length+1];
            System.arraycopy(weight, 0, newWeightArray, 0, incidenceMatrix[0].length);
            weight = newWeightArray;
            newWeightArray[newWeightArray.length-1] = newWeight;
        }
        for(int i = 0 ; i < incidenceMatrix.length ; i++){
            for(int j = 0 ; j <incidenceMatrix[i].length;j++)
                newArray[i][j] = incidenceMatrix[i][j];
        }
        incidenceMatrix = newArray;


    }

    @Override
    public void increasingArray(int size){
        Integer[][] newArray = new Integer[size][incidenceMatrix[0].length];
        Vertex<T>[] newVertices = new Vertex[size];
        if(incidenceMatrix.length<size) {
            for(int i = 0 ; i < incidenceMatrix.length ; i++) {
                for (int j = 0; j < incidenceMatrix[i].length; j++)
                    newArray[i][j] = incidenceMatrix[i][j];
                newVertices[i] = vertices[i];
            }
        }
        incidenceMatrix = newArray;
        vertices=newVertices;
    }

    @Override
    public void addVertex(Vertex<T> vertex){
        for(Vertex<T> vertex1 : vertices)
            if(vertex.compareTo(vertex1) == 0)
                return;
            increasingArray(incidenceMatrix.length+1);
            vertex.setPos(vertices.length-1);
            vertices[vertices.length-1]=vertex;


    }

    @Override
    public void addVertex(Vertex<T> begin, Vertex<T> end, double weight) {
        addVertex(begin);
        addVertex(end);
        for(Vertex<T> vertex : vertices) {
            if (vertex.compareTo(end) == 0)
                incidenceMatrix[vertex.pos][incidenceMatrix[vertex.pos].length - 1] = 1;
            else if (vertex.compareTo(begin) == 0) {
                if (isDirected())incidenceMatrix[vertex.pos][incidenceMatrix[vertex.pos].length - 1] = -1;
                else incidenceMatrix[vertex.pos][incidenceMatrix[vertex.pos].length - 1] = 1;
            }
            if(isDigraph()) this.weight[this.weight.length -1] = weight;
        }
    }

    @Override
    public Vertex<T>[] getVertexList() {
        return vertices;
    }

    @Override
    public ArrayList<Edge<T>> getEdges() {
        ArrayList<Edge<T>> edges = new ArrayList<>();
        for(int i = 0 ; i < incidenceMatrix[0].length ;i++){
            T begin = null,end=null;
            for(int j = 0 ; j < incidenceMatrix.length;j++){
                if(isDirected() && incidenceMatrix[j][i]!=null){
                    if(incidenceMatrix[j][i] == -1) begin = vertices[j].value;
                    if(incidenceMatrix[j][i] == 1) end = vertices[j].value;
                }else{
                    if(incidenceMatrix[j][i] != null && begin==null ) begin = vertices[j].value;
                    else if(incidenceMatrix[j][i] !=null ) end = vertices[j].value;
                }
            }
            if(isDigraph()){
                edges.add(new Edge<>(begin,end,weight[i]));
                if(!isDirected()) edges.add(new Edge<>(end,begin,weight[i]));
            }
            else{
                edges.add(new Edge<T>(begin,end));
                if(!isDirected()) edges.add(new Edge<>(end,begin));
            }
        }
        return edges;
    }

    public boolean isDigraph() {
        return digraph;
    }

    public boolean isDirected() {
        return directed;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n").append(this.getClass()).append(" |skierowany: ").append(isDirected()).append(" |wazony : ").append(isDigraph()).append("\n");
        s.append("    ");
        for(int i = 0 ; i < incidenceMatrix[0].length;i++)
            if(isDigraph()) s.append(i+1).append("(").append(weight[i]).append(") ");
            else s.append(i+1).append("      ");
        s.append("\n");
        for(int i = 0 ; i < incidenceMatrix.length ;i++){
            s.append(vertices[i].value).append("| ");
            for(int j = 0 ; j < incidenceMatrix[i].length; j++){
                s.append("  ");
                if(incidenceMatrix[i][j]!=null)
                    s.append(incidenceMatrix[i][j]);
                else
                    s.append(0);
                s.append("    ");
            }
            s.append("\n");
        }
        return String.valueOf(s);
    }
}
