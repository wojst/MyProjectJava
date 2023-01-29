package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private String numberString = "";
    private double doZaplaty = 0;
    private String kupujacy;

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton dodajKlientaButton;
    private JTable tabelaProduktow;
    private JTextField numberTextField;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JTable tabelaKlientow;
    private JButton a0Button;
    private JButton OKButton;
    private JButton cButton;
    private JTable tabelaKlientow2;
    private JButton zatwierdzButton;
    private JTable tabelaProduktow2;
    private JList koszykList;
    private JLabel kupujacyLabel;
    private JLabel doZaplatyLabel;
    private JButton wyczyscButton;
    private JTable tabelaFaktur;
    private JTextField dodawanaNazwaKlientaTextField;
    private JTextField dodawanyNipKlientaTextField;
    private JTextField dodawanaNazwaProduktuTextField;
    private JComboBox kategorieComboBox;
    private JTextField dodawanaCenaProduktuTextField;
    private JButton dodajProduktButton;
    private JButton usunProduktButton;
    private JButton usunKlientaButton;
    private JTable tabelaUzytkownikow;
    private JTextField loginTextField1;
    private JTextField hasloTextField2;
    private JButton dodajUzytkownikaButton;
    private JButton usunUzytkownikaButton;
    private JButton usunFaktureButton;
    private JButton brakButton;

    public static void main(String[] args) {
        AdminGUI p1 = new AdminGUI();
        p1.setVisible(true);
    }

    public AdminGUI() {
        super("AdminGUI");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        DefaultTableModel model1 = createProductList();
        DefaultTableModel model2 = createClientList();
        DefaultTableModel model3 = createInvoiceList();
        DefaultTableModel model4 = createUsersList();

        DefaultListModel listModel = new DefaultListModel<>();
        koszykList.setModel(listModel);

        doZaplatyLabel.setText("Do zapłaty:");

        ButtonListener listener = new ButtonListener();
        a1Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a7Button.addActionListener(listener);
        a8Button.addActionListener(listener);
        a9Button.addActionListener(listener);
        a0Button.addActionListener(listener);
        cButton.addActionListener(listener);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kod = numberTextField.getText();

                for (int i = 0; i < tabelaProduktow2.getRowCount(); i++) {
                    if (kod.equals(String.valueOf(tabelaProduktow2.getValueAt(i,1)))) {
                        listModel.addElement(tabelaProduktow2.getValueAt(i,0));
                        doZaplaty += Double.parseDouble(String.valueOf(tabelaProduktow2.getValueAt(i, 3)));
                    }
                }
                doZaplatyLabel.setText("Do zapłaty: " + String.valueOf(doZaplaty) + "zł");
            }
        });

        wyczyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                doZaplatyLabel.setText("Do zapłaty:");
            }
        });

        tabelaKlientow2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kupujacy = tabelaKlientow2.getValueAt(tabelaKlientow2.getSelectedRow(),1).toString();
                kupujacyLabel.setText("Kupujący: " + kupujacy);
            }
        });

        brakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelaKlientow2.clearSelection();
                kupujacyLabel.setText("Kupujacy:");
            }
        });

        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwaKlienta;
                if (!tabelaKlientow2.isRowSelected(tabelaKlientow2.getSelectedRow())) {
                    nazwaKlienta = "";
                }
                else nazwaKlienta = tabelaKlientow2.getValueAt(tabelaKlientow2.getSelectedRow(),1).toString();
                String[] listaZakupow = new String[koszykList.getModel().getSize()];

                for (int i = 0; i < koszykList.getModel().getSize(); i++) {
                    listaZakupow[i] = String.valueOf(koszykList.getModel().getElementAt(i));
                }
                String koszyk = convertStrArraytoString(listaZakupow, ",");
                double kwota = Double.valueOf(doZaplaty);

                updateInvoices(nazwaKlienta, koszyk, kwota);
                createInvoiceList();
                listModel.removeAllElements();
                JOptionPane.showMessageDialog(null,"Transakcja przebiegła pomyślnie!");
            }
        });


        dodajKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dodawanaNazwaKlienta = dodawanaNazwaKlientaTextField.getText();
                String dodawanyNipKlienta = dodawanyNipKlientaTextField.getText();

                updateClients(dodawanaNazwaKlienta, dodawanyNipKlienta);
                createClientList();
                JOptionPane.showMessageDialog(null,"Dodano nowego klienta!");
            }
        });

        dodajProduktButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dodawanaNazwaProduktu = dodawanaNazwaProduktuTextField.getText();
                String dodawanaKategoriaProduktu = kategorieComboBox.getSelectedItem().toString();
                double dodawanaCenaProduktu = Double.valueOf(dodawanaCenaProduktuTextField.getText());

                updateProducts(dodawanaNazwaProduktu, dodawanaKategoriaProduktu, dodawanaCenaProduktu);
                createProductList();
                JOptionPane.showMessageDialog(null,"Dodano nowy produkt!");
            }
        });

        dodajUzytkownikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField1.getText();
                String haslo = hasloTextField2.getText();

                updateUsers(login, haslo);
                createUsersList();
                JOptionPane.showMessageDialog(null, "Dodano nowego użytkownika!");
            }
        });

        usunProduktButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybranyWiersz = tabelaProduktow.getSelectedRow();
                String usuwanyKod = String.valueOf(tabelaProduktow.getModel().getValueAt(wybranyWiersz, 1));

                deleteProduct(usuwanyKod);
                createProductList();
                JOptionPane.showMessageDialog(null,"Usunięto produkt!");
            }
        });

        usunKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybranyWiersz = tabelaKlientow.getSelectedRow();
                String usuwaneId = String.valueOf(tabelaKlientow.getModel().getValueAt(wybranyWiersz,0));

                deleteClient(usuwaneId);
                createClientList();
                JOptionPane.showMessageDialog(null,"Usunięto klienta!");
            }
        });

        usunFaktureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybranyWiersz = tabelaFaktur.getSelectedRow();
                String usuwanyNr = String.valueOf(tabelaFaktur.getModel().getValueAt(wybranyWiersz,0));

                deleteInvoice(usuwanyNr);
                createInvoiceList();
                JOptionPane.showMessageDialog(null, "Usunięto fakturę!");
            }
        });

        usunUzytkownikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wybranyWiersz = tabelaUzytkownikow.getSelectedRow();
                String usuwaneID = String.valueOf(tabelaUzytkownikow.getModel().getValueAt(wybranyWiersz,0));

                deleteUser(usuwaneID);
                createUsersList();
                JOptionPane.showMessageDialog(null, "Usunięto użytkownika!");
            }
        });
    }

    public static String convertStrArraytoString(String[] strArr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) sb.append(str).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == a1Button) {
                numberString += "1";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a2Button) {
                numberString += "2";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a3Button) {
                numberString += "3";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a4Button) {
                numberString += "4";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a5Button) {
                numberString += "5";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a6Button) {
                numberString += "6";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a7Button) {
                numberString += "7";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a8Button) {
                numberString += "8";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a9Button) {
                numberString += "9";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == a0Button) {
                numberString += "0";
                numberTextField.setText(numberString);
            }
            else if (e.getSource() == cButton) {
                numberString = "";
                numberTextField.setText(numberString);
            }
        }
    }

    public DefaultTableModel createProductList() {
        BazaDanych baza = new BazaDanych("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
        ArrayList<Produkt> bazaProduktow = baza.getProductList();

        String[] col = new String[4];
        col[0] = "Nazwa";
        col[1] = "Kod";
        col[2] = "Kategoria";
        col[3] = "Cena";

        String[][] rowData = new String[bazaProduktow.size()][4];
        for (int i = 0; i < bazaProduktow.size(); i++) {
            rowData[i][0] = bazaProduktow.get(i).getNazwa();
            rowData[i][1] = String.valueOf(bazaProduktow.get(i).getKod());
            rowData[i][2] = bazaProduktow.get(i).getKategoria();
            rowData[i][3] = String.valueOf(bazaProduktow.get(i).getCena());
        }

        DefaultTableModel tableModel = new DefaultTableModel(rowData, col);
        tabelaProduktow.setModel(tableModel);
        tabelaProduktow2.setModel(tableModel);
        return tableModel;
    }

    public DefaultTableModel createClientList() {
        BazaDanych baza = new BazaDanych("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
        ArrayList<Klient> bazaKlientow = baza.getClientsList();

        String[] col = new String[3];
        col[0] = "ID klienta";
        col[1] = "Nazwa";
        col[2] = "NIP";

        String[][] rowData = new String[bazaKlientow.size()][3];
        for (int i = 0; i < bazaKlientow.size(); i++) {
            rowData[i][0] = String.valueOf(bazaKlientow.get(i).getId_klienta());
            rowData[i][1] = bazaKlientow.get(i).getNazwa_klienta();
            rowData[i][2] = bazaKlientow.get(i).getNip_klienta();
        }

        DefaultTableModel tableModel = new DefaultTableModel(rowData, col);
        tabelaKlientow.setModel(tableModel);
        tabelaKlientow2.setModel(tableModel);
        return tableModel;
    }

    public DefaultTableModel createInvoiceList() {
        BazaDanych baza = new BazaDanych("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
        ArrayList<Faktura> bazaFaktur = baza.getInvoicesList();

        String[] col = new String[4];
        col[0] = "Nr faktury";
        col[1] = "Klient";
        col[2] = "Koszyk";
        col[3] = "Kwota";

        String[][] rowData = new String[bazaFaktur.size()][4];
        for (int i = 0; i < bazaFaktur.size(); i++) {
            rowData[i][0] = String.valueOf(bazaFaktur.get(i).getNr_faktury());
            rowData[i][1] = bazaFaktur.get(i).getKlient();
            rowData[i][2] = bazaFaktur.get(i).getKoszykFaktura();
            rowData[i][3] = String.valueOf(bazaFaktur.get(i).getNaleznosc());
        }

        DefaultTableModel tableModel = new DefaultTableModel(rowData, col);
        tabelaFaktur.setModel(tableModel);
        return tableModel;
    }

    public DefaultTableModel createUsersList() {
        BazaDanych baza = new BazaDanych("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
        ArrayList<Uzytkownik> bazaUzytkownikow = baza.getUsersList();

        String[] col = new String[3];
        col[0] = "ID uzytkownika";
        col[1] = "Login";
        col[2] = "Hasło";

        String[][] rowData = new String[bazaUzytkownikow.size()][3];
        for (int i = 0; i < bazaUzytkownikow.size(); i++) {
            rowData[i][0] = String.valueOf(bazaUzytkownikow.get(i).getId_uzytkownika());
            rowData[i][1] = bazaUzytkownikow.get(i).getLogin();
            rowData[i][2] = bazaUzytkownikow.get(i).getHaslo();
        }

        DefaultTableModel tableModel = new DefaultTableModel(rowData, col);
        tabelaUzytkownikow.setModel(tableModel);
        return tableModel;
    }

    public void updateInvoices(String strNazwaKlienta, String strKoszykFaktura, double kwota) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "insert into faktury" + " (`nazwa_klienta`,`koszyk`, `kwota`)"
                    + "values('" +strNazwaKlienta+ "', '" +strKoszykFaktura+ "', '" +kwota+"')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateClients(String strNazwa, String strNip) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "insert into klienci" + " (`nazwa_klienta`,`nip_klienta`)"
                    + "values('" +strNazwa+ "', '" +strNip+ "')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateProducts(String strNazwa, String strKategoria, double cena) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "insert into produkty" + " (`nazwa`,`kategoria`, `cena`)"
                    + "values('" +strNazwa+ "', '" +strKategoria+ "', '" +cena+"')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUsers(String strLogin, String strHaslo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "insert into uzytkownicy" + " (`nazwa_uzytkownika`,`haslo_uzytkownika`)"
                    + "values('" +strLogin+ "', '" +strHaslo+ "')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteProduct(String kod) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "delete from produkty where kod = " + kod;
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteClient(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "delete from klienci where id_klienta = " + id;
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteInvoice(String nr) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "delete from faktury where nr_faktury = " + nr;
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteUser(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "delete from uzytkownicy where id_uzytkownika = " + id;
            statement.execute(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
