package org.bukkit.craftbukkit.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class LazyHashSet<E> implements Set<E> {

    private Set<E> reference = null;

    public Set<E> getReference() {
        if (reference == null) {
            reference = makeReference();
        }
        return reference;
    }

    protected abstract Set<E> makeReference();

    @Override public int size() { return getReference().size(); }
    @Override public boolean isEmpty() { return getReference().isEmpty(); }
    @Override public boolean contains(Object o) { return getReference().contains(o); }
    @Override public Iterator<E> iterator() { return getReference().iterator(); }
    @Override public Object[] toArray() { return getReference().toArray(); }
    @Override public <T> T[] toArray(T[] a) { return getReference().toArray(a); }
    @Override public boolean add(E e) { return getReference().add(e); }
    @Override public boolean remove(Object o) { return getReference().remove(o); }
    @Override public boolean containsAll(Collection<?> c) { return getReference().containsAll(c); }
    @Override public boolean addAll(Collection<? extends E> c) { return getReference().addAll(c); }
    @Override public boolean retainAll(Collection<?> c) { return getReference().retainAll(c); }
    @Override public boolean removeAll(Collection<?> c) { return getReference().removeAll(c); }
    @Override public void clear() { getReference().clear(); }
    @Override public int hashCode() { return getReference().hashCode(); }
    @Override public boolean equals(Object o) { return getReference().equals(o); }
}
