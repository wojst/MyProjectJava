import java.util.Arrays;
import java.util.List;

public class Faktura {
    private int nr_faktury;
    private String klient;
    private String koszykFaktura;
    private double naleznosc;

    public Faktura(int nr_faktury, String klient, String koszykFaktura, double naleznosc) {
        this.nr_faktury = nr_faktury;
        this.klient = klient;
        this.koszykFaktura = koszykFaktura;
        this.naleznosc = naleznosc;
    }

    public int getNr_faktury() {
        return nr_faktury;
    }

    public void setNr_faktury(int nr_faktury) {
        this.nr_faktury = nr_faktury;
    }

    public String getKlient() {
        return klient;
    }

    public void setKlient(String klient) {
        this.klient = klient;
    }

    public String getKoszykFaktura() {
        return koszykFaktura;
    }

    public void setKoszykFaktura(String koszykFaktura) {
        this.koszykFaktura = koszykFaktura;
    }

    public double getNaleznosc() {
        return naleznosc;
    }

    public void setNaleznosc(double naleznosc) {
        this.naleznosc = naleznosc;
    }

    @Override
    public String toString() {
        return "Faktura{" +
                "nr_faktury=" + nr_faktury +
                ", klient='" + klient + '\'' +
                ", koszykFaktura='" + koszykFaktura + '\'' +
                ", naleznosc=" + naleznosc +
                '}';
    }
}
