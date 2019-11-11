package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    Kassapaate paate;
    Maksukortti kortti;
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void alussaMyydytEdullisetLounaat(){
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    
    @Test
    public void alussaMyydytMaukkaatLounaat(){
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void salodAlussaOikein(){
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void saldoKasvaaEdullisenOstostaOikein(){
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void saldoKasvaaMaukkaanOstostaOikein(){
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtoRahanMaaraOikeinMaukas(){
        int vaihtoRaha = paate.syoMaukkaasti(500);
        assertEquals(100, vaihtoRaha);
    }
    
    @Test
    public void vaihtoRahanMaaraOikeinEdullinen(){
        int vaihtoRaha = paate.syoEdullisesti(500);
        assertEquals(260, vaihtoRaha);
    }
    
    @Test
    public void saldoEiKasvaMaukkaanOstostaVaarin(){
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void saldoEiKasvaEdullisenOstostaVaarin(){
        paate.syoEdullisesti(50);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void lounaidenMaaraKasvaaOikeinMaukas(){
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void lounaidenMaaraKasvaaOikeinEdullinen(){
        paate.syoEdullisesti(400);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void palauttaaTrueJosKorttiOstoOnnistuiEdullinen(){
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palauttaaTrueJosKorttiOstoOnnistuiMaukas(){
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void otetaanRahaaKortiltaOikeinEdullinen(){
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void otetaanRahaaKortiltaOikeinMaukas(){
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void maukkaatLounaatKasvaaOikeinKortilla(){
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void edullisetLounaatKasvaaOikeinKortilla(){
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void palauttaaFalseJosKorttiOstoEiOnnistuMaukas(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    @Test
    public void palauttaaFalseJosKorttiOstoEiOnnistuEdullinen(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaudeinLounaidenMaaraEiMuutuJosKorttiostoEiOnnistu(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
     
    @Test
    public void edullistenLounaidenMaaraEiMuutuJosKorttiostoEiOnnistu(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortinRahaEiMuutuJosSaldoEiRiitaMaukas(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        int saldoEnnen = kortti.saldo();
        paate.syoMaukkaasti(kortti);
        assertEquals(saldoEnnen, kortti.saldo());
    }
    
    @Test
    public void kortinRahaEiMuutuJosSaldoEiRiitaEdullinen(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        int saldoEnnen = kortti.saldo();
        paate.syoEdullisesti(kortti);
        assertEquals(saldoEnnen, kortti.saldo());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa(){
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    
    @Test
    public void kortinRahaKasvaaLadattaessa(){
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void kassanRahaKasvaaKorttiaLadattaessa(){
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
    }
    
    @Test
    public void eiVoiLadataNegatiivistaSummaaKortilleKassa(){
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void eiVoiLadataNegatiivistaSummaaKortilleKortti(){
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
}
