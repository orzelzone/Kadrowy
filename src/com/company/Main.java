package com.company;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Kadry kadry1 = new Kadry();
        //kadry1.dodajPracownikaInteraktywnie();
        kadry1.imprtujZPlikuTekstowego("pracownicy.txt");
        System.out.println(kadry1);
        System.out.println(kadry1.sredniZarobek());
        System.out.println(kadry1.sredniZarobek(4));
        System.out.println(Arrays.toString(kadry1.dajDzialy()));
    }
}
