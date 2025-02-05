package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 760);
        add(image);

        back = new JButton("Back");
        back.setBounds(355, 420, 150, 30);
        back.setBackground(new Color(255, 102, 102));
        back.addActionListener(this);
        image.add(back);

        Conn c = new Conn();
        int balance = 0; // bahar isiliye move kiya taake try or catch ke bahar ajaye or bahr bhi istemaal karsake
        try {
            // Pehle check karna parega ke balance kitna hai apka actual uske ke liye query se data mangwaye gy or Resultset object mai store karegy
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                // ye loop aik aik karke rows compare karwata rahe ga
                if (rs.getString("type").equals("Deposit")) {
                    // Integer.parseInt isiliye lagaya hai taake string interger mai convert hojaye
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {  // catch block ko properly close kiya gaya hai
            System.out.println(e);
        }
        
        JLabel text = new JLabel("Your Current Account Balance Is: Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 270, 400, 30);
        image.add(text);
        
        
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        // aik he button hai tou check lagane ki zaroorat nahi hai 
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String args[]) {

        new BalanceEnquiry("");
    }
}
