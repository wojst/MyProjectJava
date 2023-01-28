import java.sql.*;
import java.util.ArrayList;

public class BazaDanych {


    private String url = "jdbc:mysql://localhost:3306/jdbcdatabase";
    private String username = "root";
    private String password = "";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public BazaDanych(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ArrayList<Produkt> getProductList() {

        ArrayList<Produkt> listaProduktow = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from produkty");

            while (resultSet.next()) {
                String nazwa = resultSet.getString("nazwa");
                int kod = resultSet.getInt("kod");
                String kategoria = resultSet.getString("kategoria");
                Double cena = resultSet.getDouble("cena");

                listaProduktow.add(new Produkt(nazwa, kod, kategoria, cena));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaProduktow;
    }

    public ArrayList<Klient> getClientsList() {
        ArrayList<Klient> listaKlientow = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from klienci");

            while (resultSet.next()) {
                int id_klienta = resultSet.getInt("id_klienta");
                String nazwa_klienta = resultSet.getString("nazwa_klienta");
                String nip_klienta = resultSet.getString("nip_klienta");


                listaKlientow.add(new Klient(id_klienta, nazwa_klienta, nip_klienta));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaKlientow;
    }

    public ArrayList<Uzytkownik> getUsersList() {
        ArrayList<Uzytkownik> listaUzytkownikow = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from uzytkownicy");

            while (resultSet.next()) {
                int id_uzytkownika = resultSet.getInt("id_uzytkownika");
                String nazwa_uzytkownika = resultSet.getString("nazwa_uzytkownika");
                String haslo_uzytkownika = resultSet.getString("haslo_uzytkownika");


                listaUzytkownikow.add(new Uzytkownik(id_uzytkownika, nazwa_uzytkownika, haslo_uzytkownika));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaUzytkownikow;
    }

    public ArrayList<Faktura> getInvoicesList() {
        ArrayList<Faktura> listaFaktur = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from faktury");

            while (resultSet.next()) {
                int nr_faktury = resultSet.getInt("nr_faktury");
                String nazwa_klienta= resultSet.getString("nazwa_klienta");
                String koszyk = resultSet.getString("koszyk");
                double kwota = resultSet.getDouble("kwota");


                listaFaktur.add(new Faktura(nr_faktury, nazwa_klienta, koszyk, kwota));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaFaktur;
    }

}
