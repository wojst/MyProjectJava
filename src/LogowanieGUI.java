import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogowanieGUI extends JFrame {
    private JPanel panel1;
    private JTextField loginField;
    private JButton zalogujButton;
    private JPasswordField passwordField;


    public static void main(String[] args) {
        LogowanieGUI l1 = new LogowanieGUI();
        l1.setVisible(true);
    }


public LogowanieGUI() {
        super("Panel logowania");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,300);


    zalogujButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "");
                Statement statement = connection.createStatement();

                String login = loginField.getText();
                String haslo = passwordField.getText();

                String sql = "select * from uzytkownicy where nazwa_uzytkownika='"+ login +"' and haslo_uzytkownika='"+haslo+"'";
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    dispose();
                    ProjectGUI p1 = new ProjectGUI();
                    p1.show();
                }
                else {
                    JOptionPane.showMessageDialog(panel1, "Nieprawidlowe haslo lub login");
                    loginField.setText("");
                    passwordField.setText("");
                }

            }
            catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    });
}



}
