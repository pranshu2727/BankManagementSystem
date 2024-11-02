package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin;
    JTextField rePin;
    JButton change, back;

    String pinNumber;
    PinChange(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("SYSTEM", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pinText = new JLabel("NEW PIN: ");
        pinText.setForeground(Color.WHITE);
        pinText.setFont(new Font("SYSTEM", Font.BOLD, 16));
        pinText.setBounds(165, 320, 180, 25);
        image.add(pinText);

        pin = new JPasswordField();
        pin.setFont(new Font("RALEWAY", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);

        JLabel rePinText = new JLabel("Re-Enter New PIN: ");
        rePinText.setForeground(Color.WHITE);
        rePinText.setFont(new Font("SYSTEM", Font.BOLD, 16));
        rePinText.setBounds(165, 360, 180, 25);
        image.add(rePinText);

        rePin = new JPasswordField();
        rePin.setFont(new Font("RALEWAY", Font.BOLD, 25));
        rePin.setBounds(330, 360, 180, 25);
        image.add(rePin);


        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            try {
                String nPin = pin.getText();
                String rPin = rePin.getText();
                if (!nPin.equals(rPin)) {
                    JOptionPane.showMessageDialog(null, "Entered pin doesn't match");
                    return;
                }
                if (nPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                if (rPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new pin");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "UPDATE bank set pinNumber = '"+rPin+"' where pinNumber = '"+pinNumber+"'";
                String query2 = "UPDATE login set pinNumber = '"+rPin+"' where pinNumber = '"+pinNumber+"'";
                String query3 = "UPDATE signupthree set pinNumber = '"+rPin+"' where pinNumber = '"+pinNumber+"'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transaction(rPin).setVisible(true);
            } catch (Exception ae) {
                System.out.println(ae);
            }
        } else {
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
