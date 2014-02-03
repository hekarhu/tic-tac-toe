/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelmoinninharjoitustyo;

/**
 * @param Peliruutu on pelikentän ruutu joka pitää tiedon onko ruutu jo pelattu,
 * risti vai nolla
 * @author hekarhu
 */
public class PeliRuutu {

    private static final char tyhja = ' ';
    private static final char risti = 'x';
    private static final char nolla = 'o';
    private char ristiVaiNolla = tyhja;

    /**
     * palauttaa tiedon ruudulta, onko se pelattu ruutu eli tyhjä vai risti tai
     * nolla
     *
     * @return
     */
    public char getRistiVaiNolla() {
        if (ristiVaiNolla == risti) {
            return risti;
        } else if (ristiVaiNolla == nolla) {
            return nolla;
        } else {
            return tyhja;
        }
    }

    /**
     * metodilla asetetaan peliruutu pelatuksi, palauttaa false jos ruutu on jo
     * pelattu
     *
     * @param merkki ottaa vastaan tiedon kummaksi se on pelattu
     * @return
     */
    private boolean setRistiVaiNolla(char merkki) {
        if (this.ristiVaiNolla == tyhja) {
            this.ristiVaiNolla = merkki;
            return true;
        } else {
            return false;
        }
    }

    /**
     * jos ruutu on pelattu nollaksi kutsutaan tätä metodia joka tarkistaa että
     * kaikki on sallittua
     *
     * @return
     */
    public boolean setNolla() {
        return setRistiVaiNolla(nolla);
    }

    /**
     * jos ruutu on pelattu ristiksi kutsutaan tätä metodia joka tarkistaa että
     * kaikki on sallittua
     *
     * @return
     */
    public boolean setRisti() {
        return setRistiVaiNolla(risti);
    }
}
