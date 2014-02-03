/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelmoinninharjoitustyo;

import java.util.Scanner;

/**
 *
 * @author hekarhu
 */
public class PeliKentta {

    Scanner lukija = new Scanner(System.in);
    final int kentankoko;

    /**
     * Luokan konstruktori 
     *
     * @param koko käyttäjältä saatu tieto halutusta kentän koosta
     */
    public PeliKentta(int koko) {
        this.kentankoko = koko;
    }

    /**
     * @param tämä metodi saattaa pelin alkuun, eli luo halutun kokoisen kentän
     * ja sijoittaa peliruudut joka ruutuun. Tallentaa tiedot peliRuudut[][]-
     * nimiseen taulukkoon. Tämä taulukko on pelin tärkein osa, koska siinä on
     * aina tieto pelin sen hetkisestä tilanteesta.
     */
    public void pelaaPeli() {
        PeliRuutu[][] peliRuudut = new PeliRuutu[kentankoko][kentankoko];
        for (int i = 0; i < kentankoko + 1; i++) {
            if (i != 0) {
                System.out.print(i + " ");

            } else {
                System.out.print("_|");
            }
        }
        System.out.println("");
        for (int i = 0; i < kentankoko; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < kentankoko; j++) {
                peliRuudut[i][j] = new PeliRuutu();
                System.out.print(" |");
            }
            System.out.println("");
        }
        pelaamaan(peliRuudut);
    }

    /**
     * tätä metodia kutsuttaessa peli lähtee lopullisesti käyntiin eli käyttäjä
     * saa alkaa syöttämään ruutuihin ristejä ja nollia.
     *
     * @param peliRuudut
     */
    private void pelaamaan(PeliRuutu[][] peliRuudut) {
        PeliLogiikka logiikka = new PeliLogiikka(kentankoko);
        int pelivuoro = 0;
        int maksimiVuorot = (kentankoko * kentankoko);
        for (int i = 0; i <= maksimiVuorot; i++) {
            int vaakaPaikka = kysyVaaka();
            int pystyPaikka = kysyPysty();
            if (pelivuoro++ % 2 == 0) {
                while (!peliRuudut[vaakaPaikka][pystyPaikka].setRisti()) {
                    System.out.println("ruudussa on jo merkki, anna uudestaan merkki");
                    vaakaPaikka = kysyVaaka();
                    pystyPaikka = kysyPysty();
                }
            } else {
                while (!peliRuudut[vaakaPaikka][pystyPaikka].setNolla()) {
                    System.out.println("ruudussa on jo merkki, anna uudestaan merkki");
                    vaakaPaikka = kysyVaaka();
                    pystyPaikka = kysyPysty();
                }
            }
            piirraNykyHetki(peliRuudut);
            if (logiikka.tarkistaTilanne(peliRuudut, vaakaPaikka, pystyPaikka)) {
                System.out.println("Pelin voitti " + peliRuudut[vaakaPaikka][pystyPaikka].getRistiVaiNolla());
                return;
            }
        }
    }
    
    /**
     * kysyy käyttäjältä halutun rivin
     * @return 
     */

    private int kysyVaaka() {
        System.out.println("mille riville:");
        int rivi = lukija.nextInt();
        if (rivi > 0 && rivi <= kentankoko) {
            return rivi - 1;
        } else {
            System.out.println("ei ole riittävän iso kenttä, anna uudestaan rivi");
            kysyVaaka();
        }
        return 0;
    }

    /**
     * kysyy käyttäjältä halutun sarakkeen
     * @return 
     */
    private int kysyPysty() {
        System.out.println("mille sarakkeelle:");
        int sarake = lukija.nextInt();
        if (sarake > 0 && sarake <= kentankoko) {
            return sarake - 1;
        } else {
            System.out.println("ei ole riittävän iso kenttä, anna uudestaan sarake");
            kysyPysty();
        }
        return 0;
    }

    /**
     * piirtää jokaisen siirron jälkeen nykyhetken
     * @param peliRuudut 
     */
    private void piirraNykyHetki(PeliRuutu[][] peliRuudut) {
        for (int i = 0; i < kentankoko + 1; i++) {
            if (i != 0) {
                System.out.print(i + " ");

            } else {
                System.out.print("_|");
            }
        }
        System.out.println("");
        for (int i = 0; i < kentankoko; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < kentankoko; j++) {
                System.out.print(peliRuudut[i][j].getRistiVaiNolla());
                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
