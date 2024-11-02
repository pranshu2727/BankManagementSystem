package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton loginButton, signUp, clear;
    JTextField cardTextField;
    JPasswordField pinPasswordField;
     public Login() {

         // ImageIcon class is used to import an icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));

        // Image class is used to locate the ImageIcon
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        // We can't add object of Image function to Label, so we need to convert
        // Image class into ImageIcon class
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        add(l1);

        JLabel text = new JLabel("Welcome to ATM");
        text.setBounds(200, 40, 400, 40);
        text.setFont(new Font("Osward", Font.BOLD, 38));
        add(text);

        JLabel cardno = new JLabel("Card No.");
        cardno.setBounds(120, 150, 400, 40);
        cardno.setFont(new Font("Raleway", Font.BOLD, 30));
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setBounds(120, 220, 250, 30);
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        add(pin);

        pinPasswordField = new JPasswordField();
        pinPasswordField.setBounds(300, 220, 230, 30);
        pinPasswordField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinPasswordField);

        loginButton = new JButton("Sign In");
        loginButton.setBounds(300, 300, 100, 30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

        clear = new JButton("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("SignUp");
        signUp.setBounds(300, 350, 230, 30);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);

        setLayout(null);
        l1.setBounds(70, 10, 100, 100);
        setSize(800, 480);
        setTitle("Automated Teller Machine");
        setVisible(true);
        setLocation(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // "getContentpane()" function is used to access the overall frame to style it
        getContentPane().setBackground(Color.white);
    }
    public static void main(String[] args) {
        new Login();
    }

    // Object of ActionEvent "e" tells us the source or which button has been clicked
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == clear) {

             // We are letting setText empty so that whenever user clicks on clear but,
             // it will make the TextField empty
             cardTextField.setText("");
             pinPasswordField.setText("");
         } else if (e.getSource() == loginButton) {
             Conn conn = new Conn();
             String cardNumber = cardTextField.getText();
             String pinNumber = pinPasswordField.getText();
             String query = "SELECT * from login where cardNumber = '"+cardNumber+"' and pinNumber = '"+pinNumber+"'";
             try {
                 ResultSet rs = conn.s.executeQuery(query);
                 if (rs.next()) {
                     setVisible(false);
                     new Transaction(pinNumber).setVisible(true);
                 } else {
                     JOptionPane.showMessageDialog(null, "Incorrect Card number or Pin");
                 }
             } catch (Exception ae) {
                 System.out.println(ae);
             }
         } else if (e.getSource() == signUp) {
             setVisible(false);
             new SignUpOne().setVisible(true);
         }
    }
}
