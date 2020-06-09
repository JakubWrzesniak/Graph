package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra<T extends Comparable<T>> {
    private AdjacencyList<T> graph;
    private int numOfVertices;
    private Vertex<T> startVertex;
    private Double[] way;
    private Vertex<T>[]pre;

    public Dijkstra(AdjacencyList<T> graph, Vertex<T> startVertex) {
        if(!graph.isDigraph()) throw new UnsupportedOperationException();
        this.graph = graph;
        this.numOfVertices = graph.getVertexList().length;
        for(Vertex<T> vert : graph.getVertexList())
            if(vert.compareTo(startVertex)==0){
                this.startVertex = vert;
            }
        way = new Double[numOfVertices];
        for(int i = 0 ; i <  numOfVertices ;i++) way[i] = Double.POSITIVE_INFINITY;
        way[this.startVertex.pos] = 0.0;
        pre = new Vertex[numOfVertices];
    }

    public void run(){
        Set<Vertex<T>> Q = new Set<>();
        Set<Vertex<T>> S = new Set<>();
        for(Vertex<T> v : graph.getVertexList()) Q.add(v);

        while (Q.size()>0){
            double min = Double.POSITIVE_INFINITY;
            Vertex<T> localMin = null;
            for(Vertex<T> v : Q){
                if(way[v.pos]<min) {
                    min = way[v.pos];
                    localMin = v;
                }
            }
            S.add(localMin);
            Q.remove(localMin);
            Vertex<T> adjacent = localMin;
            if(localMin == null) return;
            while (adjacent.getAdjacent() != null) {
                if (Q.contains(adjacent.getAdjacent())) {
                    if (way[adjacent.getAdjacent().pos] > (way[localMin.pos] + adjacent.getWeight())) {
                        way[adjacent.getAdjacent().pos] = way[localMin.pos] + adjacent.getWeight();
                        pre[adjacent.getAdjacent().pos] = localMin;
                    }
                }
                adjacent = adjacent.getAdjacent();
            }
        }
    }

    public Double[] getWay() {
        return way;
    }

    public Vertex<T>[] getPre() {
        return pre;
    }
}
