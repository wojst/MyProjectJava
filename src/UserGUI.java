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

public class UserGUI extends JFrame {
    private String numberString = "";
    private double doZaplaty = 0;
    String kupujacy;
    private JTextField numberTextField;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a5Button;
    private JButton a0Button;
    private JButton cButton;
    private JButton OKButton;
    private JTable tabelaKlientow2;
    private JTable tabelaProduktow2;
    private JButton zatwierdzButton;
    private JLabel kupujacyLabel;
    private JList koszykList;
    private JLabel doZaplatyLabel;
    private JButton wyczyscButton;
    private JPanel mainUserGUIPanel;

    public static void main(String[] args) {
        UserGUI u1 = new UserGUI();
        u1.setVisible(true);
    }

    public UserGUI() {
        super("UserGUI");
        this.setContentPane(this.mainUserGUIPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        DefaultTableModel model1 = createProductList();
        DefaultTableModel model2 = createClientList();
        DefaultTableModel model3 = createInvoiceList();

        DefaultListModel listModel = new DefaultListModel<>();
        koszykList.setModel(listModel);

        doZaplatyLabel.setText("Do zapłaty:");

        UserGUI.ButtonListener listener = new UserGUI.ButtonListener();
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

        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwaKlienta = tabelaKlientow2.getValueAt(tabelaKlientow2.getSelectedRow(),1).toString();
                String[] listaZakupow = new String[koszykList.getModel().getSize()];

                for (int i = 0; i < koszykList.getModel().getSize(); i++) {
                    listaZakupow[i] = String.valueOf(koszykList.getModel().getElementAt(i));
                }
                String koszyk = convertStrArraytoString(listaZakupow, ",");
                double kwota = Double.valueOf(doZaplaty);

                updateInvoices(nazwaKlienta, koszyk, kwota);
                createInvoiceList();
                listModel.removeAllElements();
                JOptionPane.showMessageDialog(null,"Transakcja przebiegła pomyślnie");
            }
        });
    }

    private static String convertStrArraytoString(String[] strArr, String delimiter) {
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
}
