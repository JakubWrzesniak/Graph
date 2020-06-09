package Graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Set<T extends Comparable<T>>implements Comparable<Set<T>>,Iterable<T>  {
    private ArrayList<T> set;
    public Set(){
        set = new ArrayList<>();
    }
    public Set(T elem){
        this();
        set.add(elem);
    }
    public Set<T> FindSet(T key){
        return set.contains(key)? this : null;
    }

    public void add(T elem){
        set.add(elem);
    }

    public void addAll(Set<T> a){
        Iterator<T> iterator = a.iterator();
        while (iterator.hasNext())
            set.add(iterator.next());
    }
    public Iterator<T> iterator(){
        return set.iterator();
    }

    public boolean contains(T value){
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext())
            if(iterator.next().compareTo(value)==0) return true;
            return false;
    }

    private void clear(){
        set.clear();
    }
    public static<T extends Comparable<T>> void Union(Set<T> a, Set<T> b){
        a.addAll(b);
        b.clear();
    }

    public int size(){
        return set.size();
    }

    public boolean remove(T val){
        return set.remove(val);
    }


    @Override
    public int compareTo(Set<T> o) {
        Iterator<T> iterator1 = o.iterator();
        Iterator<T> iterator2 = set.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()){
            if(!iterator1.next().equals(iterator2.next()))
                return -1;
        }
        if(iterator1.hasNext()||iterator2.hasNext())
            return 1;
        else
            return 0;

    }
}
