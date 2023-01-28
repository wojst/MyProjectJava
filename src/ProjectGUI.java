import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProjectGUI extends JFrame {

    private String numberString = "";

    private double doZaplaty = 0;

    String kupujacy;



    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton dodajKlientaButton;
    private JButton usunKlientaButton;
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
    private JTable tableFaktur;
    private JLabel kupujacyLabel;
    private JLabel doZaplatyLabel;
    private JButton wyczyscButton;

    public static void main(String[] args) {
        ProjectGUI p1 = new ProjectGUI();
        p1.setVisible(true);
    }

    public ProjectGUI() {
        super("Project");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        DefaultTableModel model1 = createProductList();
        DefaultTableModel model2 = createClientList();

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

        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] koszykArray = new String[koszykList.getModel().getSize()];
                for (int i = 0; i < koszykList.getModel().getSize(); i++) {
                    koszykArray[i] = String.valueOf(koszykList.getModel().getElementAt(i));
                }
                Faktura faktura = new Faktura(kupujacy, koszykArray, doZaplaty);
            }
        });
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
//        col[4] = "Ilosc";

        String[][] rowData = new String[bazaProduktow.size()][4];

        for (int i = 0; i < bazaProduktow.size(); i++) {
            rowData[i][0] = bazaProduktow.get(i).getNazwa();
            rowData[i][1] = String.valueOf(bazaProduktow.get(i).getKod());
            rowData[i][2] = bazaProduktow.get(i).getKategoria();
            rowData[i][3] = String.valueOf(bazaProduktow.get(i).getCena());
//            rowData[i][4] = String.valueOf(bazaProduktow.get(i).getIlosc());
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

        String[][] rowData = new String[bazaKlientow.size()][5];

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

//    public DefaultTableModel createInvoiceList() {
//        String[] col = new String[4];
//        col[0] = "Nr faktury";
//        col[1] = "Klient";
//        col[2] = "Koszyk";
//        col[3] = "Kwota";
//
//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableFaktur.setModel(tableModel);
//        return tableModel;
//    }










}
