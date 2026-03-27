package org.bukkit.craftbukkit.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WeakCollection<T> implements Collection<T> {

    private final Collection<WeakReference<T>> collection;

    public WeakCollection() {
        collection = new ArrayList<>();
    }

    @Override
    public int size() {
        removeStale();
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        removeStale();
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        for (WeakReference<T> ref : collection) {
            T val = ref.get();
            if (val != null && val.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<WeakReference<T>> it = collection.iterator();
            T next = null;

            @Override
            public boolean hasNext() {
                while (next == null && it.hasNext()) {
                    next = it.next().get();
                    if (next == null) it.remove();
                }
                return next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T n = next;
                next = null;
                return n;
            }

            @Override
            public void remove() {
                it.remove();
            }
        };
    }

    @Override
    public Object[] toArray() {
        removeStale();
        ArrayList<T> list = new ArrayList<>();
        for (WeakReference<T> ref : collection) {
            T val = ref.get();
            if (val != null) list.add(val);
        }
        return list.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        ArrayList<T> list = new ArrayList<>();
        for (WeakReference<T> ref : collection) {
            T val = ref.get();
            if (val != null) list.add(val);
        }
        return list.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return collection.add(new WeakReference<>(t));
    }

    @Override
    public boolean remove(Object o) {
        Iterator<WeakReference<T>> it = collection.iterator();
        while (it.hasNext()) {
            T val = it.next().get();
            if (val == null || val.equals(o)) {
                it.remove();
                return val != null;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T t : c) changed |= add(t);
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) changed |= remove(o);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Iterator<WeakReference<T>> it = collection.iterator();
        while (it.hasNext()) {
            T val = it.next().get();
            if (val == null || !c.contains(val)) {
                it.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        collection.clear();
    }

    private void removeStale() {
        collection.removeIf(ref -> ref.get() == null);
    }
}
