package Oppgaver.Oblig2;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        if(a == null) {
            antall = 0;
            throw new NullPointerException();
        }
        if(a.length == 0) {
            antall = 0;
            return;
        }
        int s = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] != null) {
                hode = new Node<>(a[i]);
                s = i+1;
                antall += 1;
                break;
            }
            if(i == a.length-1) {
                antall = 0;
                return;
            }
        }
        int k = 0;
        for(int i = a.length-1; i >= s; i--) {
            if(a[i] != null) {
                hale = new Node<>(a[i]);
                k = i;
                antall +=1;
                break;
            }
        }
        if(hale == hode || a.length == 1) {
            antall = 1;
            return;
        }
        Node<T> start = hode;
        for(int i = s; i < k; i++) {
            if(a[i] == null) {
                continue;
            }
            Node<T> c = new Node<>(a[i],start,null);
            c.forrige.neste = c;
            start = c;
            this.antall += 1;
        }
        if(antall < 1) {
            hale.forrige = hode;
            hode.neste = hale;
        }
        hale.forrige = start;
        start.neste = hale;



    }

    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);
        endringer++;
        Node<T> a = new Node<>(verdi);
        if(tom()) {
            antall++;
            hode = a;
            hale = hode;
            return true;
        }
        antall++;
        hale.neste = a;
        a.forrige = hale;
        hale = a;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if(tom()) {
            return "[]";
        }
        StringBuilder ut = new StringBuilder();
        ut.append("[").append(hode.verdi);
        int i = 1;
        Node<T> a = hode.neste;
        while(i < antall) {
            ut.append(", ").append(a.verdi);
            a = a.neste;
            i++;
        }
        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        if(tom()) {
            return "[]";
        }
        StringBuilder ut = new StringBuilder();
        ut.append("[").append(hale.verdi);
        int i = antall-2;
        Node<T> a = hale.forrige;
        while(i >= 0) {
            ut.append(", ").append(a.verdi);
            a = a.forrige;
            i--;
        }
        ut.append("]");
        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe
