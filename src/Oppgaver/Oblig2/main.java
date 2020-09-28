package Oppgaver.Oblig2;

public class main {
    public static void main(String[] args) {

        Integer[] a = {1,null,2,null,4,5,6,7,8};
        DobbeltLenketListe<Integer> b = new DobbeltLenketListe<>(a);
        System.out.println(b.toString());

    }
}
