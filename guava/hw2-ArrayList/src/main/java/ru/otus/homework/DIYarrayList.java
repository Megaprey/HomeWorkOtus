package ru.otus.homework;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private T mass[];
    private int size;
    public static void main(String[] args) {
        DIYarrayList<String> diYarrayList1 = new DIYarrayList<>();
        Collections.addAll(diYarrayList1,  "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa",
                "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa",
                "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa");
    }

    public DIYarrayList(){
        mass = (T[]) new Object[16];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmptyFlg = true;
        for (T element: mass) {
            if(element != null){
                isEmptyFlg = false;
            }
        }
        return isEmptyFlg;
    }

    @Override
    public boolean contains(Object o) {
        boolean containsFlg = false;
        for (T element: mass) {
            if(element.equals(o)){
                containsFlg = true;
            }
        }

        return containsFlg;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    @Override
    public Object[] toArray() {
        return mass;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        mass[mass.length - 1] = t;

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return mass[index];
    }

    @Override
    public T set(int index, T element) {
        T elementMass = mass[index];
        mass[index] = element;
        return elementMass;
    }

    @Override
    public void add(int index, T element) {
        mass[index] = element;
    }

    @Override
    public T remove(int index) {
        T element = mass[index];
        mass[index] = null;

        return element;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
    private class Iter implements Iterator<T>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }
}
