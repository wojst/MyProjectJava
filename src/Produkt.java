public class Produkt {
    String nazwa;
    double cena;
    int kod;
    String kategoria;
//    int ilosc;

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

//    public int getIlosc() {
//        return ilosc;
//    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", kod=" + kod +
                ", kategoria='" + kategoria + '\'' +
//                ", ilosc=" + ilosc +
                '}' + "\n";
    }
}
