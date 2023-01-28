public class Klient {
    int id_klienta;
    String nazwa_klienta;
    String nip_klienta;

    public Klient(int id_klienta, String nazwa_klienta, String nip_klienta) {
        this.id_klienta = id_klienta;
        this.nazwa_klienta = nazwa_klienta;
        this.nip_klienta = nip_klienta;
    }

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    public String getNazwa_klienta() {
        return nazwa_klienta;
    }

    public void setNazwa_klienta(String nazwa_klienta) {
        this.nazwa_klienta = nazwa_klienta;
    }

    public String getNip_klienta() {
        return nip_klienta;
    }

    public void setNip_klienta(String nip_klienta) {
        this.nip_klienta = nip_klienta;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id_klienta=" + id_klienta +
                ", nazwa_klienta='" + nazwa_klienta + '\'' +
                ", nip_klienta='" + nip_klienta + '\'' +
                '}';
    }
}
