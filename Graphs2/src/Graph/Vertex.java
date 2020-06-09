package Graph;

import java.awt.*;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>{
    Vertex<T> adjacent;
    double weight;
    T value;
    int pos;
    boolean white = true;

    public Vertex(T value){
        this.value = value;
        adjacent = null;
    }
    Vertex(T value, double weight){
        this.value = value;
        this.weight = weight;
        adjacent = null;
    }
    public Vertex<T> getAdjacent() {
        return adjacent;
    }
    void setAdjacent(Vertex adjacent) {
        this.adjacent = adjacent;
    }

    public Double getWeight() {
        return weight;
    }

    public T getValue() {
        return value;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return value.compareTo(o.getValue());
    }

    public void setPos(int pos){
        this.pos=pos;
    }

    public int getPos() {
        return pos;
    }

}