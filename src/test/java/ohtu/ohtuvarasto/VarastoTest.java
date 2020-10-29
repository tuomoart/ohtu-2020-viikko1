package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusKonstruktoriTesti() {
        Varasto v = new Varasto(1);

        assertEquals(1, v.getTilavuus(), 0.000001);
    }

    @Test
    public void tilavuusKonstruktoriKunTilavuusNolla() {
        Varasto v = new Varasto(0);

        assertEquals(0, v.getTilavuus(), 0.00001);
    }

    @Test
    public void saldollinenKonstruktoriTesti() {
        Varasto v = new Varasto(1, 0.5);

        assertEquals(1, v.getTilavuus(), 0.00001);
        assertEquals(0.5, v.getSaldo(), 0.000001);
    }

    @Test
    public void saldollinenKonstruktoriLiianPieniTilavuus() {
        Varasto v = new Varasto(0, 1);

        assertEquals(0, v.getTilavuus(), 0.00001);
        assertEquals(0, v.getSaldo(), 0.000001);
    }

    @Test
    public void saldollinenKonstruktoriLiianPieniSaldo() {
        Varasto v = new Varasto(1, -1);

        assertEquals(1, v.getTilavuus(), 0.00001);
        assertEquals(0, v.getSaldo(), 0.000001);
    }

    @Test
    public void saldollinenKonstruktoriLiianIsoSaldo() {
        Varasto v = new Varasto(1, 2);

        assertEquals(1, v.getTilavuus(), 0.00001);
        assertEquals(1, v.getSaldo(), 0.000001);
    }

    @Test
    public void eiLisataLiianPientaMaaraa() {
        double maara = varasto.getSaldo();

        varasto.lisaaVarastoon(-1);

        assertEquals(maara, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiLisataLiikaa() {
        varasto.lisaaVarastoon(100);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiOtetaNegatiivista() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void otetaanYliSaldon() {
        varasto.lisaaVarastoon(1);
        assertEquals(1, varasto.otaVarastosta(100), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTesti() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }



}