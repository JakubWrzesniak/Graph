package Graph;

import Queue.Queue;

import java.util.ArrayList;

public abstract class Search {

    public static<T extends Comparable<T>> ArrayList<Edge<T>> BFS (Vertex<T> vertex, AdjacencyList<T> graph){
        Vertex<T>[] vertices = graph.getVertexList();
        ArrayList<Edge<T>> path = new ArrayList<>();
        Queue<Vertex<T>> queue = new Queue<Vertex<T>>();


        for (int i = 0 ; i < vertices.length; i++) {
            vertices[i].setWhite(true);
            if (vertices[i].compareTo(vertex) == 0) {
                vertices[i].setWhite(false);
                queue.enqueue(vertices[i]);
            }
        }

        while (!queue.isEmpty()){
            Vertex<T> actVertex = queue.dequeue();
            Vertex<T> previousVertex = actVertex;

            while (actVertex.getAdjacent()!=null){
                for(int i = 0 ; i < vertices.length;i++ )
                    if(vertices[i].compareTo(actVertex.getAdjacent())==0){
                        if(vertices[i].isWhite()){
                            path.add(new Edge<T>(previousVertex.getValue(),vertices[i].getValue(),actVertex.getWeight()));
                            vertices[i].setWhite(false);
                            queue.enqueue(vertices[i]);
                        }
                        break;
                    }
                actVertex = actVertex.getAdjacent();
            }
        }
        return path;
    }

    public static <T extends Comparable<T>> ArrayList DFS(AdjacencyMatrix<T> graph){
        Vertex<T>[] vertices = graph.getVertexList();
        ArrayList edges = new ArrayList();

        for(Vertex<T> vertex1 : vertices) vertex1.setWhite(true);

        for(Vertex<T> vertex1 : vertices)
            if (vertex1.isWhite())
                DFS_Visit(vertex1, graph,edges);
            return edges;
    }

    public static<T extends Comparable<T>> void DFS_Visit(Vertex<T> vertex,AdjacencyMatrix<T> graph,ArrayList edges){
        Vertex<T>[] vertices = graph.getVertexList();
        vertex.setWhite(false);
        for(int i = 0 ; i < vertices.length;i++){
                if(graph.adjacencyMatrix[vertex.pos][i]!=null && graph.adjacencyMatrix[vertex.pos][i] < Double.POSITIVE_INFINITY){
                    if(vertices[i].isWhite()){
                        edges.add(new Edge<>(vertex.getValue(),vertices[i].getValue(),graph.adjacencyMatrix[vertex.pos][i]));
                        DFS_Visit(vertices[i],graph,edges);
                    }
                }

        }

    }
}
