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
public class OhjelmoinninHarjoitustyo {

    /**
     * @param args the command line arguments
     * pelin aloitus toimenpiteet
     */
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Tervetuloa pelaamaan ristinollaa");
        System.out.println("miten iso ruudukko");
        int koko = lukija.nextInt();
        while (koko < 3) {
            System.out.println("laita nyt isompi ku kaks, miten iso ruudukko");
            koko = lukija.nextInt();
        }
        PeliKentta peli = new PeliKentta(koko);
        peli.pelaaPeli();
    }
}
