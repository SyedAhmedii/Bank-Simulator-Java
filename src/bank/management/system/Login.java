package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener { // Action Listener se functionality lagti hai or JFrame se frame ke library hai

    JButton login, signup, clear; // globally definde kara hai taake constructor ke bahar bhi used hosake
    JTextField cardTextField;  // globally definde kara hai taake constructor ke bahar bhi used hosake
    JPasswordField pinTextField;

    Login() {
        setTitle("ATM MACHINE"); // title set karne ke liye

        setLayout(null); //custom layout banane ke liye default layout ko null kardiya 

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));// classLoader aik class hai jo system ke ander se static method ke zariye images utha kar lata hai
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); // image ki height width set karne ke liye
        ImageIcon i3 = new ImageIcon(i2); // image ko dobara image Icon mai change kiya hai 
        JLabel label = new JLabel(i3); // Jlabel ke object mai icon ko pass kardiya
        label.setBounds(70, 10, 100, 100); // har taraf se border dediya
        add(label); // add function frame ke ander icon place karega object ander daal ke 

        JLabel text = new JLabel("Welcome To Our Bank"); // Jlabel ke object mai heding bhi daal sakte hai
        text.setFont(new Font("Osward", Font.BOLD, 38)); // font set karne ke liye 
        text.setBounds(200, 40, 500, 40);// heading ko lagane ke liye kyuke layout default null hai
        add(text);

        JLabel cardno = new JLabel("CARD No:"); // Jlabel ke object mai heding bhi daal sakte hai
        cardno.setFont(new Font("Raleway", Font.BOLD, 28)); // font set karne ke liye 
        cardno.setBounds(120, 150, 200, 30);// heading ko lagane ke liye kyuke layout default null hai
        add(cardno);

        cardTextField = new JTextField(); // textField banane ke liye 
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN CODE:"); // Jlabel ke object mai heding bhi daal sakte hai
        pin.setFont(new Font("Raleway", Font.BOLD, 28)); // font set karne ke liye 
        pin.setBounds(120, 220, 500, 30);// heading ko lagane ke liye kyuke layout default null hai
        add(pin);

        pinTextField = new JPasswordField(); // text field banane ke liye
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("LOGIN"); // login button ka object locally defined
        login.setBounds(300, 300, 100, 30); // button ki left right bottom top set karne ke liye
        login.setBackground(Color.PINK); // button ka color set karne ke liye
        login.setForeground(Color.BLACK); // font ka color set karne liye
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR"); // clear button ka object locally defined
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.PINK);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP"); // signup button ka object locally defined
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.PINK);
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE); // poore frame ko uthane ke liye
        setSize(800, 480); // length or breath
        setVisible(true); // visible karwane ke liye
        setLocation(320, 200); // location set kari frame ki 350 left se or 200 from top

    }

    public void actionPerformed(ActionEvent ae) { // action event class batati hai konsa action perfromed hua hai
        if (ae.getSource() == clear) { // get source aik fuction hai jo action ka source batata hai konsa button click hua hai
            cardTextField.setText(""); // isko aap empty choro gy string ko tou text field empty hojayegi
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'"; // data database se retrieve karne ki command
            try {
                // query database mai match hogi tou data return bhi hoga usko catch karwa liya result set ke object mai
                ResultSet rs = conn.s.executeQuery(query); //Kyuki DDL command hai data retrieve horaha hai tou execute query ka istemaal hoga
                if (rs.next()) {
                    setVisible(false); //Login frame band hogaya
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == signup) {
            // agar current frame ko close karna hai tou setVisible false karde
            setVisible(false);
            // Signup class ka frame kholne ke liye signup class ka object banana parega usme mai setVisible ko true karna parega kyuki byDefault false hota hai
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
