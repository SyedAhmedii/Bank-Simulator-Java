package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 760);
        add(image);

        JLabel text = new JLabel("Enter Amount You Want To Withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(200, 240, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(175, 280, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 410, 150, 30);
        withdraw.setBackground(new Color(144, 238, 144));
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 445, 150, 30);
        back.setBackground(new Color(255, 102, 102));
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) { // Agar withdraw button pe click hua
            String number = amount.getText(); // Amount field se amount le rahe hain
            Date date = new Date(); // Current date le rahe hain
            if (number.equals("")) { // Agar amount field khali ho
                JOptionPane.showMessageDialog(null, "Please Enter Amount To Withdraw"); // User ko message dikhana ke amount daalein
            } else {
                try {
                    Conn conn = new Conn(); // Database se connection bana rahe hain
                    ResultSet rs = conn.s.executeQuery("SELECT SUM(CASE WHEN type = 'Deposit' THEN amount ELSE -amount END) AS balance FROM bank WHERE pin = '" + pinnumber + "'"); // Balance calculate karne ke liye query execute kar rahe hain
                    int balance = 0; // Balance initialize kar rahe hain
                    if (rs.next()) { // Agar result set mein koi data mile
                        balance = rs.getInt("balance"); // Balance ko result set se le rahe hain
                    }
                    int withdrawAmount = Integer.parseInt(number); // User ke entered withdraw amount ko integer mein convert kar rahe hain
                    if (withdrawAmount > balance) { // Agar withdrawal amount balance se zyada ho
                        JOptionPane.showMessageDialog(null, "Insufficient Balance"); // Insufficient balance ka message dikhana
                    } else {
                        String query = "insert into bank values ('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + number + "')"; // Withdrawal transaction ko database mein insert kar rahe hain
                        conn.s.executeUpdate(query); // Query execute kar rahe hain
                        JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrawn Successfully"); // Success message dikhana
                        setVisible(false); // Current window ko hide kar rahe hain
                        new Transactions(pinnumber).setVisible(true); // Transactions window ko show kar rahe hain
                    }
                } catch (Exception e) {
                    System.out.println(e); // Agar koi exception aaye toh usse print kar rahe hain
                }
            }
        } else if (ae.getSource() == back) { // Agar back button pe click ho
            setVisible(false); // Current window ko hide kar rahe hain
            new Transactions(pinnumber).setVisible(true); // Transactions window ko show kar rahe hain
        }
    }

    public static void main(String args[]) {
        new Withdrawl("");
    }
}
