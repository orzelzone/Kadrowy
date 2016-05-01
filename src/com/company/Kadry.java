package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Sławomir on 04.04.2016.
 */
public class Kadry implements Serializable {
    private Pracownik[] pracownicy_;
    private int zatrudnienie_;

    public Kadry() {
        pracownicy_ = new Pracownik[100];
        zatrudnienie_ = 0;

    }

    private void dodajPracownika(Pracownik pracownik) {
        if (zatrudnienie_ < 100) {
            pracownicy_[zatrudnienie_] = pracownik;
            zatrudnienie_++;
        } else
            System.out.println("Brak możliwości dodania tego pracownika z powodów polityki firmy o 100 zatrudnionych osobach");

    }

    public void dodajPracownikaInteraktywnie() {
        System.out.println("Wprowadzanie danych nowego pracownika.");
        String imie = czytajString("Podaj imię nowego pracownika");
        String nazwisko = czytajString("Podaj nazwisko nowego pracownika");
        double placa = czytajDouble("Podaj wysokość wynagrodzenia dla nowego pracownika");
        char plec = czytajChar("Podaj płeć Pracownika: M-mężczyzna, K-Kobieta");
        int dzial = czytajInt("Podaj numer działu w którym nowy pracownik zozstaje zatrudniony");
        Pracownik pracownik = new Pracownik(imie, nazwisko, placa, plec, dzial);
        dodajPracownika(pracownik);
    }

    @Override
    public String toString() {
        String wyswietl = "Kadry{";
        for (int i = 0; i < zatrudnienie_; i++) {
            wyswietl = wyswietl + pracownicy_[i].toString();
        }
        wyswietl = wyswietl + "%n}";
        return String.format(wyswietl);


    }

    public void imprtujZPlikuTekstowego(String nazwaPliku) throws FileNotFoundException {
        String[] danePracownika = new String[5];
       int licznik =0;
        Scanner linijka = new Scanner(new File(nazwaPliku)).useDelimiter("/n/r");

        while (linijka.hasNext()) {
            //linijkaTekstu= linijka.next();
            Scanner slowo = new Scanner(linijka.nextLine());
            for (int i = 0; i < 5; i++) {
                danePracownika[i] = slowo.next();
            }
            char znak;
            char[] myChar;
            myChar = danePracownika[3].toCharArray();
            znak = myChar[0];
            Pracownik pracownik = new Pracownik(danePracownika[0], danePracownika[1], Double.valueOf(danePracownika[2]),
                    znak, Integer.valueOf(danePracownika[4]));
            dodajPracownika(pracownik);
            slowo.close();
            licznik++;
        }
        linijka.close();
        System.out.println("Prawidłowo dokonano importu "+ licznik+ " pracowników z pliku: " + nazwaPliku);

    }

    protected double sredniZarobek() {
        double wynik = 0;
        for (int i = 0; i < zatrudnienie_; i++)
            wynik = wynik + pracownicy_[i].getPlaca();
        return wynik / zatrudnienie_;

    }

    protected double sredniZarobek(int dzial) {
        double wynik = 0;
        int ilosc = 0;
        for (int i = 0; i < zatrudnienie_; i++) {
            if (pracownicy_[i].getDzial() == dzial) {
                wynik = wynik + pracownicy_[i].getPlaca();
                ilosc++;
            }
        }
        return wynik / ilosc;
    }

    protected int[] dajDzialy() {
        int[] tempTablica = new int[zatrudnienie_]; //tablica tymczasowa
        int licznik = 0;
        boolean jest = false;
        for (int i = 0; i < zatrudnienie_; i++) {//pętla  która idzie po każdym zatrudnoionym
            jest = false;
            for (int j = 0; j < licznik; j++) {// pętla kóra idzie już po zapisanych danych w tablicy tymczasowej
                if (jest == false) {
                    if (tempTablica[j] == pracownicy_[i].getDzial()) { //sprawdzenie czy dział w którym pracuje pracownik jest już w tablicy
                        jest = true;// jeśli jest to true
                    /*} else {
                        jest = false; //jeśli nie to false*/
                    }
                }
            }
            if (jest == false) { // jeśli dział niezapisany w tablicy to zapisuje do tablicy
                tempTablica[licznik] = pracownicy_[i].getDzial();
                licznik++;
            }
        }
        //return tempTablica;
        int k = 0;
        while (tempTablica[k] != 0) { //sprawdzenie ile pól w tabeli zapisanych zostało
            k++;
        }
        int[] tablicaDzialy = new int[k];
        for (int l = 0; l < k; l++) { //przepisanie do mniejszej tabeli
            tablicaDzialy[l] = tempTablica[l];
        }
        return tablicaDzialy; //zwrócenie tablicy
    }
    public void piszDane(){
        System.out.println(this);
        System.out.printf("Srednie Wynagrodzenie w firmie wynosi: %8.2f zł %n", this.sredniZarobek());
        for(int i= 0; i< this.dajDzialy().length; i++){
            System.out.printf("Srednie wynagrodzenie w dziale " +dajDzialy()[i]+ " wynosi: %8.2f zł %n", sredniZarobek(dajDzialy()[i]));
        }
    }

    public void zapiszDoPliku( String nazwaPliku) throws IOException, ClassNotFoundException {
        ObjectOutputStream plikKadry = new ObjectOutputStream(new FileOutputStream(nazwaPliku));
        plikKadry.writeObject(this);
    }

    public Kadry odczytajZPliku(String nazwaPliku) throws IOException, ClassNotFoundException {
        ObjectInputStream plikKadry = new ObjectInputStream(new FileInputStream(nazwaPliku));
        return (Kadry) plikKadry.readObject();
    }


    //////////////////////////////////
    //metody pomocnicze po za zadaniem

    private static int czytajInt(String komunikat) {
        int liczba = 0;
        boolean ok = false;
        System.out.println(komunikat);
        BufferedReader odczyt = new BufferedReader(new InputStreamReader(System.in));
        while (!ok) {
            try {
                liczba = Integer.parseInt(odczyt.readLine());
                ok = true;
            } catch (NumberFormatException e) { //gdy wprowadzone dane nie są liczbą
                System.err.println("Wprowadziles  zle znaki. WPROWADZI JESZCZE RAZ");
                System.out.println(komunikat);
                ok = false;
            } catch (IOException e) {
                System.err.println("błąd danych");
                ok = false;
            }
        }
        return liczba;
    }

    private static double czytajDouble(String komunikat) {
        double liczba = 0;
        boolean ok = false;
        System.out.println(komunikat);
        BufferedReader odczyt = new BufferedReader(new InputStreamReader(System.in));
        while (!ok) {
            try {
                liczba = Double.parseDouble(odczyt.readLine());
                ok = true;
                //odczyt.close();
            } catch (NumberFormatException e) { //gdy wprowadzone dane nie są liczbą
                System.err.println("Wprowadziles  zle znaki. WPROWADZI JESZCZE RAZ");
                System.out.println(komunikat);
                ok = false;
            } catch (IOException e) {
                System.err.println("błąd danych");
                ok = false;
            }

        }
        // odczyt.close();

        return liczba;
    }

    private static String czytajString(String komunikat) {
        System.out.println(komunikat);
        Scanner klawiatura = new Scanner(System.in);
        //klawiatura.close();
        String temp;
        temp = klawiatura.next();
        System.out.println("Wprowadzone dane to: " + temp);
        //klawiatura.close();
        return temp;


    }

    private static char czytajChar(String komunikat) {
        char znak = 'a';
        String temp;
        boolean poprawny = false;
        Scanner klawiatura = new Scanner(System.in);

        while (poprawny == false) {
            try {
                System.out.println(komunikat);
                temp = klawiatura.nextLine();
                char[] myChar;
                myChar = temp.toCharArray();
                znak = myChar[0];
                if (znak == 'm' || znak == 'k' || znak == 'M' || znak == 'K') {
                    poprawny = true;
                    return znak;
                } else {
                    poprawny = false;
                    System.out.println("Wprowadziłeś nieprawidłowe oznaczenie płci");
                }

            } catch (Exception e) {
                System.out.println("Porszę podać poprawny znak");
                poprawny = false;
            }
        }
        klawiatura.close();

        return znak;

    }
}
