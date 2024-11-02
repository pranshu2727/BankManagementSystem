package bankmanagementsystem;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton amount1, amount2, amount3, amount4, amount5, amount6, back;
    String pinNumber;
    FastCash(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);


        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(220, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount1 = new JButton("Rs 100");
        amount1.setBounds(170, 415, 150, 30);
        amount1.addActionListener(this);
        image.add(amount1);

        amount2 = new JButton("Rs 500");
        amount2.setBounds(355, 415, 150, 30);
        amount2.addActionListener(this);
        image.add(amount2);

        amount3 = new JButton("Rs 1000");
        amount3.setBounds(170, 450, 150, 30);
        amount3.addActionListener(this);
        image.add(amount3);

        amount4 = new JButton("Rs 2000");
        amount4.setBounds(355, 450, 150, 30);
        amount4.addActionListener(this);
        image.add(amount4);

        amount5 = new JButton("Rs 5000");
        amount5.setBounds(170, 485, 150, 30);
        amount5.addActionListener(this);
        image.add(amount5);

        amount6 = new JButton("Rs 10000");
        amount6.setBounds(355, 485, 150, 30);
        amount6.addActionListener(this);
        image.add(amount6);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        } else {
            String amount = ((JButton)e.getSource()).getText().substring(3);
            Conn conn = new Conn();
            try {
                ResultSet rs = conn.s.executeQuery("SELECT * from bank where pin = '"+pinNumber+"'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance = balance + Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance = balance - Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (e.getSource() != back && balance > Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "INSERT into bank VALUES('"+pinNumber+"', '"+date+"', 'Withdrawl', '"+amount+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+amount+" debited successfully");

                setVisible(false);
                new Transaction(pinNumber ).setVisible(true);
            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
    }

    public static void main(String[] args) {

        new FastCash("");
    }
}
