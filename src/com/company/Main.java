package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class Main implements Serializable{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	// write your code here
        Kadry kadry1 = new Kadry();
        //kadry1.dodajPracownikaInteraktywnie();
        kadry1.imprtujZPlikuTekstowego("pracownicy.txt");
        System.out.println(kadry1);
        System.out.println(kadry1.sredniZarobek());
        System.out.println(kadry1.sredniZarobek(4));
        System.out.println(Arrays.toString(kadry1.dajDzialy()));
        Kadry.zapiszDoPliku(kadry1, "plik_kadr.dat");
        Kadry kadry2 = new Kadry();
        kadry2.imprtujZPlikuTekstowego("pracownicy1.txt");
        Kadry.zapiszDoPliku(kadry2, "plik_kadr2.dat");
        System.out.println(kadry2);
        Kadry kadry3 = new Kadry().odczytajZPliku("plik_kadr.dat");
        System.out.println(kadry3);



    }
}
