# DATS2300 Algoritmer og Datastrukturer

- Oblig1 ligger under src/Oppgaver/Oblig1 
- Oblig2 ligger under src/Oppgaver/Oblig2

Medlemmer: 
- Adrian Karlsen, S336133, S336133@oslomet.no
- Iver Haugen, S345866, S345866@oslomet.no

# Oblig 2 
###### Oppgave 1 
* 1a. int antall() returner antall og boolean tom() returnerer true om antall er null
###### Oppgave 2
* 2a.Bruker en while look for å legge til alle elementene I listen.

* 2b.Sjekker om lista er tom. Viss det stemmer så lagrer den verdien i hodet og halen. Viss ikke blir elementet lagt til etter halen.
###### Oppgave 3
* 3a.Sjekker om indeks er større eller mindre enn antall/2 for å finne ut hva ende søkinga skal starte i. Bruker deretter en for løkke for å bla gjennom lista antall ganger det er mellom start og indeks.

* 3b. Sjekker først at input dataen ikke kaster exceptions. Lager ny tom liste og lagrer hode i den gamle. Looper gjennom lista en gang for hvert element som skal kopieres over og bruker leggInn metoden for det. 
Returnerer deretter den nye lista.
###### Oppgave 4
* 4a. Sjekker at verdien til index ikke returnerer null. Bruker så en loop for å gå gjennom alle elementene og en og returnerer index om plassen er det samme, hvis ikke returnerer den -1. 
 * boolean inneholder(T verdi) sjekker og returnerer True om verdien er 0 eller større. 
###### Oppgave 5
* Sjekker først at objektet ikke kaster NullPointException. Kaller så på metoden boolean tom() for hvis har antall 0, legger inn nodene om den ikke er det. 
###### Oppgave 6
* Sjekker om indeksen er lovlig og registrerer antall endringer. Sjekker om indeks har plassering 0, og returnerer verdien til plassering på hode. 
Hvis plasseringen/indeks har samme verdi som antall, returneres plasseringen til hale.
###### Oppgave 7
* Bruker en løkke som går gjennom alle elementene og fjerner de med metoden fjern()
###### Oppgave 8
* 8a. Sjekker om det er en CurrentModificationException om det ikke er gjort noene endringer og NoSuchElementException om det ikke finnes et neste element. Går videre til neste neste node om det ikke kastes noe Exception. 
* 8b. Returner en ny instans av DobbeltLenketListeIterator
* 8c. Peker til "denne" noden
* 8d. Sjekker om indeksen er lovlig og returner instansen av iretatorklassen. 

######Oppgave 9
######Oppgave 10