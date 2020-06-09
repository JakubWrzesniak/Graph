package Graph;

public class Edge<T extends Comparable<T>> {
    private Vertex<T> begin;
    private Vertex<T> end;
    private double weight;

    public Edge(T begin, T end, double weight) {
        this.begin = new Vertex<> (begin);
        this.end = new Vertex<>(end);
        this.weight = weight;
    }

    public Edge(T begin, T end) {
        this.begin = new Vertex<>(begin);
        this.end = new Vertex<>(end);
        weight =Double.POSITIVE_INFINITY;
    }

    public Vertex<T> getBegin() {
        return begin;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(").append(begin).append("' ").append(end+" : ").append(weight).append(")");
        return String.valueOf(s);
    }
}
