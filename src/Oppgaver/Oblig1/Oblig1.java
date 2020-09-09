package Oppgaver.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

import static Oppgaver.Oblig1.Oblig1Test.bytt;

/* Medlemmer:
- Adrian Karlsen, S336133
- Iver Haugen, S345866

 */

public class Oblig1 {
    private Oblig1() {}


    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if(a.length == 0) {
            throw new NoSuchElementException(); //kaster avvik viss arrayet er tomt
        }

        for(int i = 1; i < a.length; i++) {//går gjennom alle tallene i tabellen
            if (a[i-1] > a[i]) {// a[i] flyttes mot høgre hvis den er større enn tallet til høgre for seg.
                bytt(a, i, i-1);
            }
        }
        return a[a.length - 1];//returnerer det siste/største tallet i arrayet.
    }

    public static int ombyttinger(int[] a) {
        if(a.length == 0){
            throw new NoSuchElementException();//Kaster avvik viss arrayet er tomt
        }

        int antall = 0;
        for(int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]){
                bytt(a,i-1,i);   //gåt gjennom alle tallene i tabellen og flytter mot venstre i tabellen viss større og teller
                antall++;
            }
        }
        return antall; //returnerer antall ombyttinger
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        if(a.length < 1) {              //Sjekker om arrayet er tomt
            return 0;
        } else if(a.length == 1) {      //Returnerer 1 hvis arrayet har en størrelse på en
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

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
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
            bytt(a,left,right);      // bytter om elementene de forskjellige pekerene tilhører
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
            return;  //metoden avsluttes viss tabellen er tom
        }
        char hjelpeVar = a[a.length - 1]; //lagrer siste tallet i tabellen
        System.arraycopy(a, 0, a, 1, a.length - 1); //flytter alle elementene ett hakk mot høgre.
        a[0] = hjelpeVar; //setter det tallet som tidligere var på siste plass først i tabellen.
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        if(a.length > 1000 || a.length == 0) {
            return;
        }
        int b = k % a.length;// finner ut hvor mange plasser det må roteres for å være mest effektiv
        if(k < 0) {
            b = a.length - (-k % a.length); // samme som over, men viss tallet er negativt
        }
        for(int i = 0; i < b ; i++) {
            rotasjon(a); //roterer k antall ganger
        }
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        //Oppretter en string som verdiene skal flettes sammen i
        StringBuilder ut = new StringBuilder();

        for(int i = 0; i < s.length() + t.length(); i++) {
            if(i < s.length()) {
                ut.append(s.charAt(i));
            }

            if(i < t.length()) {
                ut.append(t.charAt(i));
            }
        }
        return ut.toString();
    }

    /// 7b)
    public static String flett(String... s) {
        StringBuilder ut = new StringBuilder();
        //total lengeden av alle strengene
        int totalLengde = 0;


        //Looper gjennom alle parameterne og finner den totale lengden
        for (String value : s) {
            //Adderer lengden for hver Char
            totalLengde += value.length();
        }

        for (int i=0;i<totalLengde;i++){
            //Går gjennom parameterne i String og fletter det sammen
            for (String value : s) {

                //Sjekker om det er en Char på indexen
                if (i < value.length()) {
                    //Legger til Char fra Stringen fra j til indexen på i
                    ut.append(value.charAt(i));
                }
            }
        }

        return ut.toString();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        int n = a.length;  // tabellens lengde
        if (n == 0) {
            return a;
        }
        int[] b = new int[a.length];
        int minverdi;
        int maksVerdi = Integer.MIN_VALUE;
        int m = 0;
        for (int j = 0; j < n; j++) {
            minverdi = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if(a[i] == minverdi) {
                    b[j] = i;
                    j++;
                }
                if (a[i] < minverdi && a[i] > maksVerdi) {
                    m = i;
                    minverdi = a[m];
                }
                if (i == n - 1) {
                    maksVerdi = minverdi;
                }

            }

            b[j] = m;
        }
        return b;
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        int n = a.length;  // tabellens lengde
        if(n < 3) {
            throw new NoSuchElementException(); // sjekker om tabellen har mindre enn tre elementer og kaster avvik hvis
        }
        int[] b = indekssortering(new int[]{a[0], a[1], a[2]});
        int m = b[0];           // indeks til minste
        int m1 = b[1];          // indeks til nest minste
        int m2 = b[2];          // indeks til tredje minste
        int min = a[m];         // minste verdi
        int min1 = a[m1];       // nest minste verdi
        int min2 = a[m2];       // tredje minste verdi
        for (int i = 3; i < n; i++) {
            if (a[i] < min2) {
                if (a[i] < min1) {
                    if (a[i] < min) {
                        m2 = m1;
                        min2 = min1; // ny tredje minste verdi
                        m1 = m;
                        min1 = min;  // ny nest minste verdi
                        m = i;
                        min = a[i];  // ny tredje minste verdi
                    } else {
                        m2 = m1;
                        min2 = min1;     // ny tredje minste verdi
                        m1 = i;
                        min1 = a[i];     // ny nest minste verdi
                    }
                } else {
                    m2 = i;
                    min2 = a[i];         // ny tredje minste verdi
                }
            }
        }
        return new int[] {m,m1,m2}; // minste i posisjon 0, nest minste i posisjon 1, tredje minste i posisjon 2
    }

}  // Oppgaver.Oblig1.Oblig1
