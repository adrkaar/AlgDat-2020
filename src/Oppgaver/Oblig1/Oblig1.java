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
        if(a.length < 1) {
            return 0;
        } else if(a.length == 1) {
            return  1;
        }
        if(ombyttinger(a) > 0){
            throw new IllegalStateException();
        }
        int antall = 1;
        for(int i = 1; i < a.length ; i++) {
            if(a[i] != a[i-1]){
                antall += 1;
            }
        }
        return antall;
    }

    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {
        if(a.length == 0) {
            return 0;  //returnerer 0 viss arrayet er tomt.
        }
        int unikeTall = 0;
        boolean unik = true;
        for(int i = 0; i < a.length ; i++) {
            for (int i2 = 0 ; i2 < i; i2++) {
                if (a[i2] == a[i]) {
                    unik = false;
                    break;
                }
            }
            if(unik) {
                unikeTall += 1;
            }
            unik = true;
        }
        return unikeTall;

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
        if(a.length == 0) {
            return;
        }
        char hjelpeVar = a[a.length - 1];
        System.arraycopy(a, 0, a, 1, a.length - 1);
        a[0] = hjelpeVar;
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        if(a.length > 1000 || a.length == 0) {
            return;
        }
        int b = k % a.length;
        if(k < 0) {
            b = a.length - (-k % a.length);
        }
        for(int i = 0; i < b ; i++) {
            rotasjon(a);
        }
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        //Oppretter en string som verdiene skal flettes sammen i
        String ut = "";

        for(int i = 0; i < s.length() + t.length(); i++) {
            if(i < s.length()) {
                ut += s.charAt(i);
            }

            if(i < t.length()) {
                ut += t.charAt(i);
            }
        }
        return ut;
    }

    /// 7b)
    public static String flett(String... s) {
        String ut = "";
        //total lengeden av alle strengene
        int totalLengde = 0;


        //Looper gjennom alle parameterne og finner den totale lengden
        for (int i=0; i<s.length;i++){
            //Adderer lengden for hver Char
            totalLengde +=s[i].length();
        }

        for (int i=0;i<totalLengde;i++){

            //Går gjennom parameterne i String og fletter det sammen
            for (int j=0; j < s.length ;j++){

                //Sjekker om det er en Char på indexen
                if (i < s[j].length()){
                    //Legger til Char fra Stringen fra j til indexen på i
                    ut += s[j].charAt(i);
                }
            }
        }

        return ut;
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        if (a.length == 0) {
            return a;
        }
        int[] b = new int[a.length];
        int minverdi;
        int maksVerdi = Integer.MIN_VALUE;
        int m = 0;
        for (int j = 0; j < a.length; j++) {
            minverdi = Integer.MAX_VALUE;
            for (int i = 0; i < a.length; i++) {
                if(a[i] == minverdi) {
                    b[j] = i;
                    j++;
                }
                if (a[i] < minverdi && a[i] > maksVerdi) {
                    m = i;                // indeks til største verdi oppdateres
                    minverdi = a[m];     // største verdi oppdateres
                }
                if (i == a.length - 1) {
                    maksVerdi = minverdi;
                }

            }

            b[j] = m;
        }
        return b;
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        if(a.length < 3) {
            throw new NoSuchElementException();
        }
        int[] b = indekssortering(new int[]{a[0], a[1], a[2]});
        int m = b[0];
        int m1 = b[1];
        int m2 = b[2];
        int min = a[m];
        int min1 = a[m1];
        int min2 = a[m2];
        for (int i = 3; i < a.length; i++) {
            if (a[i] < min2) {
                if (a[i] < min1) {
                    if (a[i] < min) {
                        min2 = min1;
                        m2 = m1;
                        min1 = min;
                        m1 = m;
                        min = a[i];
                        m = i;
                    } else {
                        m2 = m1;
                        min2 = min1;     // ny nest størst
                        m1 = i;
                        min1 = a[i];
                    }
                } else {
                    m2 = i;
                    min2 = a[i];         // ny nest størst
                }
            }
        }
        return new int[] {m,m1,m2};
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new UnsupportedOperationException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new UnsupportedOperationException();
    }

}  // Oppgaver.Oblig1.Oblig1
