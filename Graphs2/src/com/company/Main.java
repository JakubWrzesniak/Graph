package com.company;

import Graph.*;
import Graph.MST.Kruskal;

import java.util.ArrayList;
import java.util.Arrays;

import static Graph.MST.Kruskal.mst;

public class Main {

    public static void main(String[] args) {
        ArrayList<IGraph<String>> graphs = new ArrayList<>();
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] vertices2 = {"A", "B", "C", "D", "E", "F"};
        Edge[] edges = new Edge[]{new Edge<>("A", "B", 4.0), new Edge<>("A", "D", 2.0),
                new Edge<>("A", "E", 3), new Edge<>("B", "C", 2),
                new Edge<>("B", "E", 3), new Edge<>("B", "F", 8),
                new Edge<>("B", "H", 4), new Edge<>("C", "F", 9),
                new Edge<>("D", "G", 5), new Edge<>("E", "G", 5),
                new Edge<>("E", "H", 1), new Edge<>("F", "H", 7),
                new Edge<>("G", "H", 6)};
        Edge[] edges2 = new Edge[]{new Edge<>("A", "B", 3), new Edge<>("A", "C", 1),
                new Edge<>("A", "E", 3), new Edge<>("C", "D", 5),
                new Edge<>("D", "F", 1), new Edge<>("E", "F", 2),
                new Edge<>("B", "D", 2)};
        graphs.add(new AdjacencyList<String>(vertices, edges, true, true));
        graphs.add(new AdjacencyList<String>(vertices, edges, true, false));
        graphs.add(new AdjacencyList<String>(vertices, edges, false, true));
        graphs.add(new AdjacencyList<String>(vertices, edges, false, false));

        graphs.add(new AdjacencyMatrix<String>(vertices, edges, true, true));
        graphs.add(new AdjacencyMatrix<String>(vertices, edges, true, false));
        graphs.add(new AdjacencyMatrix<String>(vertices, edges, false, true));
        graphs.add(new AdjacencyMatrix<String>(vertices, edges, false, false));

        graphs.add(new IncidenceMatrix<String>(vertices, edges, true, true));
        graphs.add(new IncidenceMatrix<String>(vertices, edges, true, false));
        graphs.add(new IncidenceMatrix<String>(vertices, edges, false, true));
        graphs.add(new IncidenceMatrix<String>(vertices, edges, false, false));

        for (IGraph graph : graphs)
            System.out.print(graph);
        for (IGraph graph : graphs) {
            graph.addEdge(new Edge<>("W", "Z", 12.2));
            graph.addVertex(new Vertex("I"));
        }
        for (IGraph graph : graphs)
            System.out.print(graph);
        System.out.println("\n*******Kruskal******\n");
        for (IGraph graph : graphs) {
            try {
                System.out.println(mst(graph));
            } catch (UnsupportedOperationException e) {
                System.out.println("Graf musi być grafem wazonym.");
            }
        }
        System.out.println("\n*******BFS******\n");
        System.out.println(Search.BFS(new Vertex<>("E"), (AdjacencyList) graphs.get(0)));
        System.out.println(Search.BFS(new Vertex<>("E"), (AdjacencyList) graphs.get(1)));
        System.out.println(Search.BFS(new Vertex<>("W"), (AdjacencyList) graphs.get(2)));
        System.out.println(Search.BFS(new Vertex<>("I"), (AdjacencyList) graphs.get(3)));

        System.out.println("\n*******DFS******\n");

        System.out.println(Search.DFS((AdjacencyMatrix) graphs.get(4)));
        System.out.println(Search.DFS((AdjacencyMatrix) graphs.get(5)));

        System.out.println("\n*******Dijkstra******\n");
        Dijkstra<String> di1 = new Dijkstra<String>((AdjacencyList) graphs.get(1), new Vertex<>("D"));
        try {
            di1.run();
        }catch (UnsupportedOperationException e){
            System.out.println("Graf musi być skierowany.");
        }
        for(Vertex<String> ver : graphs.get(1).getVertexList())
            System.out.print(ver+" , ");
        System.out.println("\n"+Arrays.toString(di1.getPre()));
        System.out.println(Arrays.toString(di1.getWay()));

    }
}
