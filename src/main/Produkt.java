package main;

public class Produkt {
    String nazwa;
    double cena;
    int kod;
    String kategoria;

    public Produkt(String nazwa, int kod, String kategoria, double cena/*, int ilosc*/) {
        this.nazwa = nazwa;
        this.kod = kod;
        this.kategoria = kategoria;
        this.cena = cena;
//        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getKod() {
        return kod;
    }

    public String getKategoria() {
        return kategoria;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", kod=" + kod +
                ", kategoria='" + kategoria + '\'' +
                '}' + "\n";
    }
}
