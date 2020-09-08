package Oppgaver.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1() {}

    //Hjelpemetode
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if(a.length == 0) {
            throw new NoSuchElementException();
        }

        for(int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]) {
                bytt(a, i, i-1);
            }
        }
        return a[a.length - 1];
    }

    public static int ombyttinger(int[] a) {
        if(a.length == 0){
            throw new NoSuchElementException();
        }

        int antall = 0;
        for(int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i+1]){
                int b = a[i];
                a[i] = a[i+1];
                a[i+1] = b;
                antall++;
            }
        }
        return antall;
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 4 //////////////////////////////////////

    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) Oblig1Test.bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        Oblig1Test.bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        Oblig1Test.bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    public static void quicksort(int[] a, int start, int end) {

        if(start < end) {
            int k = sParter0(a,start,end,(end + start) / 2);
            quicksort(a,start,k-1);
            quicksort(a,k + 1,end);

        }
    }

    public static void delsortering(int[] a) {
        if (a.length == 0) {
            return;  //Avbryter metoden viss arrayet er tomt.
        }
        boolean odds = true; // Lager boolean for å sjekke om arrayet bare inneholder oddetall
        int left = 0;
        int right = a.length - 1;
        while(right>left) {

            while (a[left] % 2 != 0 && right > left) { //Flytter venstre peker til høgre viss det er et oddetall
                left++;
            }
            while (a[right] % 2 == 0 && right > left) { //Flytter høgre peker til venstre viss det er et partall
                right--;
                odds = false; // Arrayet inneholder ikke bare oddetall
            }
            Oblig1Test.bytt(a,left,right);      // bytter om elementene de forskjellige pekerene tilhører
        }
        int end = left-1;
        if(odds) {
            end = left;
        }
        quicksort(a,0,end); //Sorterer oddetallene
        if(!odds) {
            quicksort(a, left, a.length - 1); //sorterer partallene
        }
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        throw new UnsupportedOperationException();
    }

    /// 7b)
    public static String flett(String... s) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new UnsupportedOperationException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new UnsupportedOperationException();
    }

}  // Oppgaver.Oblig1.Oblig1
