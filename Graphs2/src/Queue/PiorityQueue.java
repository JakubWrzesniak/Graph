package Queue;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

public class PiorityQueue<E,T extends Comparable<T>> {
    private Element<E,T> head;
    public PiorityQueue(){
        clear();
    }

    private class Element<E,T extends Comparable<T>> implements Comparable<Element<E,T>>{
        private E value;
        private T priority;
        private Element<E,T> nextElem;

        public Element(E value, T priority) {
            this.value = value;
            this.priority = priority;
            nextElem = null;
        }

        Element<E,T> getNextElem() {
            return nextElem;
        }

        void setNextElem(Element<E,T> nextElem) {
            this.nextElem = nextElem;
        }

        E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        T getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "{" +
                    "value=" + value +
                    ", priority=" + priority +
                    '}';
        }


        @Override
        public int compareTo(Element<E,T> o) {
            return getPriority().compareTo(o.getPriority());
        }
    }

    public void clear(){
        head = null;
    }

    public int size(){
        if (head == null) return 0;
        int counter = 1;
        Element<E,T> tail = head;
        while(tail.getNextElem()!= null){
            tail = tail.getNextElem();
            counter++;
        }
        return counter;
    }

    public void enqueue(E value, T piority){
        Element<E,T> newElem = new Element<E,T>(value,piority);
        if (head == null) head = newElem;
        else{
            if(newElem.compareTo(head) < 0){
                newElem.setNextElem(head);
                head= newElem;
            }else {
                Element<E,T> tail = head;
                while (tail.getNextElem() != null) {
                    if (newElem.compareTo(tail.getNextElem()) < 0) {
                        Element<E,T> temp = tail.getNextElem();
                        tail.setNextElem(newElem);
                        newElem.setNextElem(temp);
                        return;
                    }
                    tail = tail.getNextElem();
                }
                tail.setNextElem(newElem);
            }
        }
    }
    public void enqueueAll(List<E> lista,List<T> piority){
        Iterator<E> iterator = lista.iterator();
        Iterator<T> piorityIterator = piority.iterator();
        while (iterator.hasNext()) throw new IndexOutOfBoundsException();
            enqueue(iterator.next(),piorityIterator.next());
    }

    public E Dequeue(){
        if(head==null) throw new EmptyStackException();
        E returnVlaue = (E) head.getValue();
        head = head.getNextElem();
        return returnVlaue;
    }

}
