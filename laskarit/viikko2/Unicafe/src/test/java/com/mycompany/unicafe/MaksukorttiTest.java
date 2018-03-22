package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void saldonMuutos() {
        kortti.otaRahaa(1000000);
        assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void trueTaiFalse() {
        Boolean oikein = false;
        Boolean totta = kortti.otaRahaa(5);
        if (totta == true) {
            oikein = true;
        }
        if (oikein) {
            totta = kortti.otaRahaa(1000000);
            if (totta == true) {
                oikein = false;
            }
        }

        assertTrue(oikein);
    }
    
    @Test
    public void ladattuOikein() {
        int ladataan = 10;
        int saldo = kortti.saldo();
        kortti.lataaRahaa(ladataan);
        assertTrue(kortti.saldo() == (saldo+ladataan));
    }
    
    @Test
    public void StringgiTest() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
}
