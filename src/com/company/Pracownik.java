package com.company;

import java.io.Serializable;

/**
 * Created by Sławomir on 04.04.2016.
 */
public class Pracownik implements Serializable {
    private String imie;
    private String nazwisko;
    private double placa;
    private char plec;
    private int dzial;

    public Pracownik(String imie, String nazwisko, double placa, char plec, int dzial) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa = placa;
        this.plec = plec;
        this.dzial = dzial;
    }

    public int getDzial() {
        return dzial;
    }

    public double getPlaca() {

        return placa;
    }

    @Override
    public String toString() {
        return String.format("%n %-20s %-20s %3c %4d %10.2f zł ",imie,nazwisko,plec,dzial,placa);
    }

    public boolean czyPracujeWDziale(Pracownik pracownik, int dzial) {
        if (pracownik.dzial == dzial) return true;
        else return false;


    }
}
