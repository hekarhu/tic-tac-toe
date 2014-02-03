/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelmoinninharjoitustyo;

/**
 *
 * @author hekarhu
 */
public class PeliLogiikka {

    /**
     * määrittää meille "muuttujat" joita käytetään kuvaamaan mihin suuntaan
     * peliruudukkoa tarkistetaan
     */
    public enum Suunta {

        /**
         * suunta, jossa liikutaan ruudukossa kasvattaen sarakkeen indeksiä
         */
        VAAKA,
        /**
         * suunta, jossa liikutaan ruudukossa kasvattaen sekä rivin, että
         * sarakkeen indeksiä
         */
        ETUKENO,
        /**
         * suunta, jossa liikutaan ruudukossa kasvattaen rivin indeksiä
         */
        PYSTY,
        /**
         * suunta, jossa liikutaan ruudukossa kasvattaen rivin indeksiä ja
         * pienentäen sarakkeen indeksiä
         */
        TAKAKENO
    }
    int kentankoko;
    PeliRuutu[][] peliruudut;
    int voittosuora;

    /**
     *
     * @param kentankoko konstruktori joka ottaa vastaan tiedon kentän koosta,
     * joka vaikuttaa pelin sääntöihin
     * @param
     */
    public PeliLogiikka(int kentankoko) {
        this.kentankoko = kentankoko;
        if (this.kentankoko < 6) {
            this.voittosuora = 3;
        } else {
            this.voittosuora = 5;
        }
    }

    /**
     *
     * @param tarskistaTilanne metodi jolle annetaan sen hetkinen peliruudukon
     * tilanne sekä viimeisin laitettu merkki. Tarkistaa voittiko jompikumpi
     * pelin. Käyttää metodia tarkistaNaapurit syöttäen for-each silmukassa
     * kaikki suunnat metodille
     * @param peliRuudut tieto peliruudukon sen hetkisestä tilanteesta
     * @param vaakaRivi koordinaatti(indeksi) vaakariville
     * @param sarake koordinaatti(indeksi) sarakkeelle
     * @return
     */
    public boolean tarkistaTilanne(PeliRuutu[][] peliRuudut, int vaakaRivi, int sarake) {
        this.peliruudut = peliRuudut;
        for (Suunta suunta : Suunta.values()) {
            if (tarkistaNaapurit(1, vaakaRivi, sarake, suunta, true)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tarkistaNaapurit tarkistaa juuri laitetun merkin naapurit
     * rekursiivisesti, mahdollisten voittosuorien varalta, tarkalleen tarkistaa
     * juuri laitetusta ruudusta joko vaaka, pysty, etukenoon, tai takakeen niin
     * kauan kuin löytää saman merkin, sen jälkeen vaihtaa suunnan ja tarkistaa
     * viimeisestä löydetyst takaperin, näin saadaan voitto tarkistettua jos
     * pelattu ruutu on kahden samanmerkkisen välissä
     * @param montakosuorassa kertoo aina montako samanmerkkistä on jo luettu
     * suoraan
     * @param vaakaRivi tieto millä rivillä toimitaan
     * @param sarake tieto millä sarakkeella toimitaan
     * @param suunta tietotyyppi jolla on tieto mihin suuntaan tarkistetaan
     * @param eteen muuttuja joka on true kun tarkistetaan peliruudukkoa
     * ikäänkuin eteenpäin ja false jos tarkistetaan toisinpäin
     * @return palauttaa false jos naapuri ei ollut sama
     */
    private boolean tarkistaNaapurit(int montakosuorassa, int vaakaRivi, int sarake, Suunta suunta, boolean eteen) {
        int seuraavaVaakarivi = laskeVaakarivi(suunta, eteen, vaakaRivi);
        int seuraavaSarake = laskeSarake(suunta, eteen, sarake);
        boolean naapuriSama = false;
        if (onkoPeliKentalla(seuraavaVaakarivi, seuraavaSarake)) {
            naapuriSama = onkoSamatNaapurit(vaakaRivi, sarake, seuraavaVaakarivi, seuraavaSarake);

        }

        if (naapuriSama) {
            montakosuorassa++;
            if (montakosuorassa == this.voittosuora) {
                return true;
            }
            return tarkistaNaapurit(montakosuorassa, seuraavaVaakarivi, seuraavaSarake, suunta, eteen);
        } else if (eteen) {
            return tarkistaNaapurit(1, vaakaRivi, sarake, suunta, false);
        }
        return false;
    }

    /**
     * @param onkoPeliKentalla on tarkistaNaapurit()- metodin apumetodi joka
     * palauttaa true/false riippuen onko tarkistus vielä pelikentällä
     * @param seuraavaVaakarivi
     * @param seuraavaSarake
     * @return
     */
    private boolean onkoPeliKentalla(int seuraavaVaakarivi, int seuraavaSarake) {
        return (onkoSisalla(seuraavaVaakarivi) && onkoSisalla(seuraavaSarake));
    }

    /**
     * @param tarkistaa että tarkistettava indeksi on vielä pelikentän sisällä
     * @param indeksi koordinaatin toinen osa peliruudukossa
     * @return
     */
    private boolean onkoSisalla(int indeksi) {
        return indeksi >= 0 && indeksi < kentankoko;
    }

    /**
     * @param palauttaa totuustarvon riippuen onko vertailtavat kaksi ruutua
     * toistensa kaltaisia
     * @param vaakaRivi
     * @param sarake
     * @param seuraavaVaakarivi
     * @param seuraavaSarake
     * @return
     */
    private boolean onkoSamatNaapurit(int vaakaRivi, int sarake, int seuraavaVaakarivi, int seuraavaSarake) {
        return peliruudut[vaakaRivi][sarake].getRistiVaiNolla() == peliruudut[seuraavaVaakarivi][seuraavaSarake].getRistiVaiNolla();
    }

    /**
     * laskee seuraavaksi tarkistettavan ruudun vaakaindeksin
     *
     * @param suunta suunta kertoo mihin suuntaan ollaan menossa
     * @param eteen jatkaa tarkistussuuntaa "eteenpäin", jos true, muuten
     * vaihtaa suunnan
     * @param vaakaRivi tieto nykyisestä vaakarivistä ja palauttaa seuraavan
     * vaakarivin
     * @return
     */
    private static int laskeVaakarivi(Suunta suunta, boolean eteen, int vaakaRivi) {
        switch (suunta) {
            case ETUKENO:
            case PYSTY:
                return vaakaRivi + (eteen ? 1 : -1);
            case TAKAKENO:
                return vaakaRivi + (eteen ? 1 : -1);
            case VAAKA:
            default:
                return vaakaRivi;
        }
    }

    /**
     * laskee seuraavaksi tarkistettavan ruudun sarakeindeksin
     *
     * @param suunta suunta kertoo mihin suuntaan ollaan menossa
     * @param eteen jatkaa tarkistussuuntaa "eteenpäin", jos true, muuten
     * vaihtaa suunnan
     * @param vaakaRivi tieto nykyisestä sarakkeesta ja palauttaa seuraavan
     * sarakkeen
     * @return
     */
    private static int laskeSarake(Suunta suunta, boolean eteen, int sarake) {
        switch (suunta) {
            case ETUKENO:
            case VAAKA:
                return sarake + (eteen ? 1 : -1);
            case TAKAKENO:
                return sarake + (eteen ? -1 : 1);
            case PYSTY:
            default:
                return sarake;

        }
    }
}
