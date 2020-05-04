package ru.otus.homework;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private T[] mass;
    private int size;
    private int capacity = 16;
    public static void main(String[] args) {
        DIYarrayList<String> diYarrayList1 = new DIYarrayList<>();
        Collections.addAll(diYarrayList1,  "sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa",
                "sadsa", "sadsa", "sadsa", "sadsa", "abc", "sadsa", "sadsa", "sadsa", "sadsa", "sadsa",
                "sadsa", "sadsa", "sadsa", "sadsa", "bca", "sadsa", "sadsa");
        System.out.println(diYarrayList1);
        DIYarrayList<String> diYarrayList2 = new DIYarrayList<>();
        Collections.addAll(diYarrayList2,  "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
                "17", "18", "19", "20", "21", "22", "23");
        Collections.copy(diYarrayList2, diYarrayList1);
        System.out.println(diYarrayList2);
        Collections.sort(diYarrayList2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(diYarrayList2);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] massResult = new Object[size];
        System.arraycopy(mass,0, massResult, 0, size);
        return massResult;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        size++;
        if(size > capacity){
            capacity = capacity * 2;
            T[] massTmp = (T[]) new Object[capacity];
            for(int i = 0; i < mass.length; i++){
                massTmp[i] = mass[i];
            }
            mass = massTmp;
        }
        mass[size - 1] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter() ;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class ListIter implements ListIterator<T>{
        int cursor;
        int cursorForThisIter;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size || cursor < 0) {
                System.err.println("нет такого элемента");
            }
            cursorForThisIter = cursor;
            cursor++;

            return (T) DIYarrayList.this.mass[cursorForThisIter];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(T t) {
            DIYarrayList.this.mass[cursorForThisIter] = t;
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        return "DIYarrayList{" +
                "mass=" + Arrays.toString(mass) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
