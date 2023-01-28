import java.util.Arrays;
import java.util.List;

public class Faktura {
    private int nr_faktury;
    private String klient;
    private String[] koszykFaktura;
    private double naleznosc;

    static int licznik = 0;

    public Faktura(String klient, String[] koszykFaktura, double naleznosc) {
        this.nr_faktury = ++licznik;
        this.klient = klient;
        this.koszykFaktura = koszykFaktura;
        this.naleznosc = naleznosc;
    }

    @Override
    public String toString() {
        return "Faktura{" +
                "nr_faktury=" + nr_faktury +
                ", klient='" + klient + '\'' +
                ", koszykFaktura=" + Arrays.toString(koszykFaktura) +
                ", naleznosc=" + naleznosc +
                '}';
    }
}
