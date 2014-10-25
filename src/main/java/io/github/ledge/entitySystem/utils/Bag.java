package io.github.ledge.entitySystem.utils;

import java.util.Iterator;

public class Bag<E> implements Iterable<E> {

    private static final int DEFAULT_SIZE = 64;

    private E[] bag;
    private int size;

    public Bag() {
        this(DEFAULT_SIZE);
    }

    public Bag(int size) {
        this.bag = (E[]) new Object[size];
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void grow() {
        this.grow((this.size * 3) / 2 + 1);
    }

    private void grow(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot resize by a negative value!");

        E[] oldBag = this.bag;
        this.bag = (E[]) new Object[amount];
        System.arraycopy(oldBag, 0, this.bag, 0, oldBag.length);
    }

    public void add(E element) {
        if (this.size <= this.bag.length) {
            grow();
        }

        this.bag[this.size++] = element;
    }

    public void set(int index, E element) {
        if (index >= this.bag.length) {
            this.grow(index * 2);
        } else if (index >= this.size) {
            this.size = index + 1;
        }

        this.bag[index] = element;
    }

    public E get(int index) {
        if (index < 0 || index > this.size)
            throw new IllegalArgumentException("Illegal index!");

        return this.bag[index];
    }

    public E remove(int index) {
        E object = get(index);

        if (this.size > 0) {
            this.bag[index] = this.bag[this.size--];
        }

        this.bag[this.size] = null;

        return object;
    }

    public boolean remove(E element) {
        for (int i = 0; i < this.size; i++) {
            E object = this.bag[i];

            if (object == element) {
                this.bag[i] = this.bag[--this.size];
                this.bag[this.size] = null;

                return true;
            }
        }

        return false;
    }

    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.bag[i] == element)
                return true;
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.bag[i] = null;
        }

        this.size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < Bag.this.size;
            }

            @Override
            public E next() {
                return Bag.this.get(this.index++);
            }

            @Override
            public void remove() {
                Bag.this.remove(this.index--);
            }
        };
    }
}
