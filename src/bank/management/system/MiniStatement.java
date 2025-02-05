package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statment");
        setLayout(null);

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("STATE BANK");
        bank.setFont(new Font("Raleway", Font.BOLD, 16));
        bank.setBounds(135, 30, 120, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 90, 300, 20);
        add(card);

        // total balance show karane ki label
        JLabel balance = new JLabel("");
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '" + pinnumber + "'");
            while (rs.next()) {
                card.setText("Your Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12)); // set Text ki help se values dalwa sakte hai label ke ander
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) { // next() ka istemaal kiya hai taake resultset ke pehli row access hosake or aage moveon hosake
                // append karna hai data ko tabhi pehle getText kiya hai phir usko string mai convert karwa kr set karwaye gy html tag ka istemaal gap dene ke liye kiya hai karsakte hai java mai &nbsp; html ka tag hai spaces ke liye use hota hai beech mai
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if (rs.getString("type").equals("Deposit")) {
                    //Integer.parseInt isiliye lagaya hai taake string interger mai convert hojaye
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your Total Balance Is: Rs " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        mini.setBounds(20, 120, 400, 200);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}
