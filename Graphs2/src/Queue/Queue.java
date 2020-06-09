package Queue;

import java.io.IOException;
import java.util.EmptyStackException;

public class Queue<E>  {
    private Element head;

    private class Element{
        private E value;
        private Element next;

        public E getValue() {
            return value;
        }

        public Element getNext() {
            return next;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNext(Element next) {
            this.next = next;
        }
        Element (E data){
            this.value = data;
        }
    }


    public void enqueue(E value) {
        Element newElem = new Element(value);
        if(head == null){
            head = newElem;
        }else{
            Element tail = head;
            while (tail.getNext() != null)
                tail = tail.getNext();
            tail.setNext(newElem);
        }
    }


    public E dequeue() throws EmptyStackException {
        if(head == null) throw new EmptyStackException();
        Element reVal = head;
        head = head.getNext();
        return reVal.getValue();
    }


    public void clear() {
        head = null;
    }


    public int size() {
        if(head == null)
            return 0;
        Element actElem = head;
        int i = 1;
        while (actElem.getNext()!= null){
            i++;
            actElem = actElem.getNext();
        }
        return i;
    }


    public boolean isEmpty() {
        return head == null;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[\n");
        Element e = head;
        while (e != null) {
            buf.append(e.getValue());
            buf.append("\n");
            e = e.getNext();
        }
        buf.append("]\n");
        return buf.toString();
    }

    public void print(){
        System.out.println(this);
        try{
            System.in.read();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
