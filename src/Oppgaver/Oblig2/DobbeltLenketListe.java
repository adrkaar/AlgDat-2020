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


    private Node<T> finnNode(int indeks)
    {
        Node<T> current;
        if(indeks < antall/2) {
            current = hode;
            for(int i = 0; i<indeks;i++) {
                current = current.neste;
            }
        } else {
            current = hale;
            for(int i = 0;i < antall-indeks-1;i++) {
                current = current.forrige;
            }
        }
    return current;
    }


    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a);
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

        if(hale == null || a.length == 1) {
            hale = hode;
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
            hale.forrige = start;
            start.neste = hale;


    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException 
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException 
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall, fra, til);
        Liste<T> ut = new DobbeltLenketListe<>();
        Node<T> current = hode;
        for(int i = 0; i < fra; i++) {
            current = current.neste;
        }
        for(int i = 0; i < til-fra; i++) {
            ut.leggInn(current.verdi);
            current = current.neste;
        }
    return ut;
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
        Node<T> a = new Node<>(verdi);
        if(tom()) {
            endringer++;
            antall++;
            hode = a;
            hale = hode;
            return true;
        }
        antall++;
        hale.neste = a;
        a.forrige = hale;
        hale = a;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks,true);
        Objects.requireNonNull(verdi);
        if(indeks == antall || tom()) {
            leggInn(verdi);
            return;
        }
        Node<T> ny;
        if(indeks == 0) {
             ny = new Node<>(verdi,null,hode);
             hode.forrige = ny;
             ny.neste = hode;
             hode = ny;
             antall++;
             endringer++;
             return;
        }
        ny = new Node<>(verdi,finnNode(indeks-1),finnNode(indeks));
        endringer++;
        antall++;
        ny.neste.forrige = ny;
        ny.forrige.neste = ny;
    }

    @Override
    public boolean inneholder(T verdi) {
        int i = indeksTil(verdi);
        return i >= 0;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;
        Node<T> current = hode;
        for(int i = 0; i < antall; i++) {
            if(verdi.equals(current.verdi)) {
                return i;
            }
            current = current.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        Objects.requireNonNull(nyverdi);
        T ut = finnNode(indeks).verdi;
        finnNode(indeks).verdi = nyverdi;
        endringer++;
        return ut;

    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null) {
            return false;
        }
        if(verdi.equals(hode.verdi)) {
            if(hode.equals(hale)) {
                hale = null;
                hode = null;
            } else {
                hode.neste.forrige = null;
                hode = hode.neste;
            }
            antall--;
            endringer++;
            return true;
        }
        if (verdi.equals(hale.verdi)) {
            hale.forrige.neste = null;
            hale = hale.forrige;
            antall--;
            endringer++;
            return true;
        }
        Node<T> current = hode.neste;
        for(int i = 0; i < antall-1; i++) {
            if(current.verdi.equals(verdi)) {
                current.neste.forrige = current.forrige;
                current.forrige.neste = current.neste;
                antall--;
                endringer++;
                return true;
            }
            current = current.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
    indeksKontroll(indeks,false);
    endringer++;
    if(indeks == 0) {
        if (hode == hale) {
            hale = null;
        } else {
            hode.neste.forrige = null;
        }
        Node<T> temp = hode;
        hode = hode.neste;
        antall--;
        return temp.verdi;
    }else if (indeks == antall-1) {
        hale.forrige.neste = null;
        Node<T> temp = hale;
        hale = hale.forrige;
        antall--;
        return temp.verdi;
    }else {
        Node<T> temp = finnNode(indeks);
        temp.neste.forrige = temp.forrige;
        temp.forrige.neste = temp.neste;
        antall--;
        return temp.verdi;
    }
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
