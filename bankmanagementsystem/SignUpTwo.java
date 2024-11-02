package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpTwo extends JFrame implements ActionListener{
    JTextField pan, aadhar;
    JButton button;
    JRadioButton syes, sno, eyes, eno;

    JComboBox religion, category, income, education, occupation;

    String formno;
    SignUpTwo(String formno) {

        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel Religion = new JLabel("Religion: ");
        Religion.setFont(new Font("Raleway", Font.BOLD, 20));
        Religion.setBounds(100, 140, 100, 30);
        add(Religion);

        String []valReligion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel Category = new JLabel("Category: ");
        Category.setFont(new Font("Raleway", Font.BOLD, 20));
        Category.setBounds(100, 190, 200, 30);
        add(Category);

        String []varCategory = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(varCategory);
        category.setBounds(300, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        JLabel Income = new JLabel("Income: ");
        Income.setFont(new Font("Raleway", Font.BOLD, 20));
        Income.setBounds(100, 240, 200, 30);
        add(Income);

        String []varIncome = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000"};
        income = new JComboBox(varIncome);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel Education = new JLabel("Education: ");
        Education.setFont(new Font("Raleway", Font.BOLD, 20));
        Education.setBounds(100, 290, 200, 30);
        add(Education);

        JLabel Qualification = new JLabel("Qualification: ");
        Qualification.setFont(new Font("Raleway", Font.BOLD, 20));
        Qualification.setBounds(100, 315, 200, 30);
        add(Qualification);

        String []educationValues = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Other"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel Occupation = new JLabel("Occupation: ");
        Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        Occupation.setBounds(100, 390, 200, 30);
        add(Occupation);

        String []occupationalValues = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Other"};
        occupation = new JComboBox(occupationalValues);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel PANNumber = new JLabel("PAN Number: ");
        PANNumber.setFont(new Font("Raleway", Font.BOLD, 20));
        PANNumber.setBounds(100, 440, 200, 30);
        add(PANNumber);

        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD, 14));
        pan.setBounds(300, 440, 400, 30);
        add(pan);

        JLabel Aadhar = new JLabel("Aadhar Number: ");
        Aadhar.setFont(new Font("Raleway", Font.BOLD, 20));
        Aadhar.setBounds(100, 490, 200, 30);
        add(Aadhar);

        aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway", Font.BOLD, 14));
        aadhar.setBounds(300, 490, 400, 30);
        add(aadhar);

        JLabel state = new JLabel("Senior Citizen: ");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 540, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450, 540, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(syes);
        buttonGroup.add(sno);

        JLabel ExistingAccount = new JLabel("Existing Account: ");
        ExistingAccount.setFont(new Font("Raleway", Font.BOLD, 20));
        ExistingAccount .setBounds(100, 590, 200, 30);
        add(ExistingAccount);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 590, 100, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(450, 590, 100, 30);
        eno.setBackground(Color.WHITE);
        add(eno);

        button = new JButton("Next");
        button.setBounds(620, 660, 80, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Raleway", Font.BOLD, 14));
        button.addActionListener(this);
        add(button);

        setLayout(null);
        getContentPane().setBackground(Color.lightGray);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {

        String sReligion = "" + religion.getSelectedItem();
        String sCategory = "" + category.getSelectedItem();
        String sIncome = "" + income.getSelectedItem();
        String sEducation = "" + education.getSelectedItem();
        String sOccupation = "" + occupation.getSelectedItem();
        String seniorCitizen = null;
        if (syes.isSelected()) {
            seniorCitizen = "Yes";
        } else if (sno.isSelected()){
            seniorCitizen = "No";
        }

        String existingAccount = null;
        if (eyes.isSelected()) {
            existingAccount = "Yes";
        } else if (eno.isSelected()){
            existingAccount = "No";
        }

        String span = pan.getText();
        String saadhar = aadhar.getText();

        try {
            if (saadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name is required");
            } else {

                Conn conn = new Conn();

                // Fourth step - Writing mysql query
                String query = "INSERT INTO signuptwo VALUES ('" + formno + "', '" + sReligion + "', '" + sCategory + "', '" + sIncome + "', '" + sEducation  + "', '" + sOccupation + "', '" + span + "', '" + saadhar + "', '" + existingAccount + "', " + "'" + seniorCitizen + "')";
                // Fifth step - Executing query
                conn.s.executeUpdate(query);

            }
            setVisible(false);
            new SignUpThree(formno).setVisible(true);
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
    public static void main(String[] args) {

        new SignUpTwo("");
    }
}
