package com.company;

import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // write your code here
        Kadry kadry1 = new Kadry();
        //kadry1.dodajPracownikaInteraktywnie();
        kadry1.imprtujZPlikuTekstowego("pracownicy.txt");
        /*System.out.println(kadry1);
        System.out.println("Srednie wynagrodzenie w firmie wynosi: " + kadry1.sredniZarobek()+" PLN");
        System.out.println("Srednie wynagrodzenie w dizale 4 wynosi: "+kadry1.sredniZarobek(4)+ " PLN");
        System.out.println(Arrays.toString(kadry1.dajDzialy()));*/

        //Kadry kadry2 = new Kadry();
        kadry1.imprtujZPlikuTekstowego("pracownicy1.txt");
        //Kadry.zapiszDoPliku(kadry2, "plik_kadr2.dat");
        //System.out.println(kadry2);
        //Kadry kadry3 = new Kadry().odczytajZPliku("plik_kadr.dat");
        /*System.out.println(kadry1);
        System.out.println("Srednie wynagrodzenie w firmie wynosi: " + kadry1.sredniZarobek()+" PLN");
        System.out.println("Srednie wynagrodzenie w dizale 4 wynosi: "+kadry1.sredniZarobek(4)+ " PLN");*/
        kadry1.piszDane();
        kadry1.zapiszDoPliku("Kadrowy.dat");


    }
}
