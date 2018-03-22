package com.mycompany.unicafe;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(400);
    }

    @Test
    public void rahaJaMyydytOikein() {
        Boolean oikein = false;
        if (kassa.kassassaRahaa() == 1000) {
            oikein = true;
        }
        if (kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty() == 0) {
            oikein = true;
        } else {
            oikein = false;
        }
        assertTrue(oikein);
    }
    
    @Test
    public void kateinenEdullisestiRiittaa() {
        int saldo = kassa.kassassaRahaa();
        assertTrue("Ei oikein", kassa.syoEdullisesti(240) == 0);
        assertTrue("kassan raha väärin", kassa.kassassaRahaa() == (saldo + 240));
    }
    
    @Test
    public void kateinenMaukkaastiRiittaa() {
        int saldo = kassa.kassassaRahaa();
        assertTrue("Ei palautettu oikein", kassa.syoMaukkaasti(400) == 0);
        assertTrue("Ei päivittynyt kassa", kassa.kassassaRahaa() == (saldo + 400));
    }
    
    @Test
    public void kateinenEiRiitaEdullisesti() {
        int saldo = kassa.kassassaRahaa();
        assertTrue("Palautettiin väärä määrä!", kassa.syoEdullisesti(50) == 50);
        assertTrue("Kassan raha muuttui!", kassa.kassassaRahaa() == saldo);
        assertTrue("Lounaiden määrä nousi!", kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kateinenEiRiitaMaukkaasti() {
        int saldo = kassa.kassassaRahaa();
        assertTrue("Palautettiin väärä määrä", kassa.syoMaukkaasti(50) == 50);
        assertTrue("Kassan raha muuttui!", kassa.kassassaRahaa() == saldo);
        assertTrue("Lounaiden määrä nousi!", kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void korttiMaukkaastiEiRiitaJaEiMuutaKassaa() {
        kortti.otaRahaa(200);
        assertTrue("Veloitettiin vaikka ei pitäisi riittää!", kassa.syoMaukkaasti(kortti) == false);
        assertTrue("Myytyjen lounaiden määrä kasvoi", kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void korttiMaukkaasti() {
        kassa = new Kassapaate();
        assertTrue("Ei veloitettu oikein maukasta, pitäisi riittää!", kassa.syoMaukkaasti(kortti) == true);
    }

    @Test
    public void kassaMaukkaita() {
        kassa.syoMaukkaasti(kortti);
        assertTrue("Myytyjen lounaiden määrä ei kasvanut", kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void korttiEdullisestiEiRiitaJaEiMuutaKassaa() {
        kortti.otaRahaa(200);
        assertTrue("Veloitettiin vaikka ei pitäisi riittää!", kassa.syoEdullisesti(kortti) == false);
        assertTrue("Myytyjen lounaiden määrä kasvoi", kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void korttiEdullisestiRiittaa() {
        kortti.lataaRahaa(240);
        assertTrue("Ei veloitettu oikein edullista, pitäisi riittää!", kassa.syoEdullisesti(kortti) == true);
    }

    @Test
    public void kassaEdullisia() {
        kassa.syoEdullisesti(kortti);
        assertTrue("Myytyjen lounaiden määrä ei kasvanut", kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void kortilleLataus() {
        int saldo = kortti.saldo();
        int raha = kassa.kassassaRahaa();
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue("Kortin saldo ei muuttunut!", kortti.saldo() == saldo + 10);
        assertTrue("Kassan rahamäärä ei kasvanut!", kassa.kassassaRahaa() == (raha + 10));
    }
    @Test
    public void kortilleLatausNegatiivinen() {
        int saldo = kortti.saldo();
        int raha = kassa.kassassaRahaa();
        kassa.lataaRahaaKortille(kortti, -10);
        assertTrue("Ladattiin kortille negatiivinen!", kortti.saldo() == saldo);
        assertTrue("Kassan rahamäärä laski negatiivisesti!", kassa.kassassaRahaa() == raha);
    }

}
