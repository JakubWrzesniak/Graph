package Graph.MST;


import Graph.*;

import Queue.PiorityQueue;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Kruskal {


    public static <T extends Comparable<T>> ArrayList<Edge<T>> mst(IGraph<T> graph){
        if(!graph.isDigraph()) throw new UnsupportedOperationException();
        ArrayList<Edge<T>> edges = graph.getEdges();
        Iterator<Edge<T>> iterator = edges.iterator();
        PiorityQueue<Edge<T>, Double> queue = new PiorityQueue<Edge<T>,Double>();
        Set<Set<Vertex<T>>> vertexSet = new Set<>();
        Vertex<T>[] verties = graph.getVertexList();

        //Utworzenie zbiorow jednoelementowych posiadających jeden wierzchołek.
        for(int i = 0 ;i < verties.length;i++)
            vertexSet.add(new Set<>(verties[i]));

        //Dodanie krawędzi na kolejkę piorytetową
        while (iterator.hasNext()){
            Edge<T> edge = iterator.next();
            queue.enqueue(edge,edge.getWeight());
        }
        edges.clear();
        while (queue.size()>0){
            Edge<T> edge = queue.Dequeue();
            Set<Vertex<T>> p = findPos(vertexSet,edge.getBegin());
            Set<Vertex<T>> q = findPos(vertexSet,edge.getEnd());
            if(p!= null && !p.equals(q)) {
                Set.Union(p, q);
                edges.add(edge);
           }
       }
        return edges;
    }

    public static <T extends Comparable<T>> Set<Vertex<T>> findPos(Set<Set<Vertex<T>>> set, Vertex<T> vertex){
        Iterator<Set<Vertex<T>>> iterator = set.iterator();
        while (iterator.hasNext()){
            Set<Vertex<T>> tempSet = iterator.next();
            if(tempSet.contains(vertex))return tempSet;
        }
        return null;
    }
}
