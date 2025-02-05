package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdrawl, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;

//constructor isiliye banate hai kyuke jaise he class ka object banaye tou constructor call hojaye
    Transactions(String pinnumber) {
        this.pinnumber = pinnumber; // particular transaction karne ke liye pinnumber chahiye hoga tabhi idher pinnumber pass kiya hai
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 760);
        add(image);

        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(245, 220, 200, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 13));
        image.add(text); // image ke upar chahiye tou image.add kiya 

        deposit = new JButton("Deposit");
        deposit.setBounds(170, 337, 150, 30);
        deposit.setBackground(new Color(144, 238, 144));
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Withdrawl");
        withdrawl.setBounds(350, 337, 150, 30);
        withdrawl.setBackground(new Color(144, 238, 144));
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170, 372, 150, 30);
        fastcash.setBackground(new Color(144, 238, 144));
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(350, 372, 150, 30);
        ministatement.setBackground(new Color(144, 238, 144));
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(170, 407, 150, 30);
        pinchange.setBackground(new Color(144, 238, 144));
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(170, 442, 150, 30);
        balanceenquiry.setBackground(new Color(144, 238, 144));
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Exit");
        exit.setBounds(350, 407, 150, 30);
        exit.setBackground(new Color(255, 102, 102));
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        }else if(ae.getSource() == deposit){
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        }else if(ae.getSource() == withdrawl){
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        }else if(ae.getSource() == fastcash){
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        }else if(ae.getSource() == pinchange){
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        }else if(ae.getSource() == balanceenquiry){
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        }else if(ae.getSource() == ministatement){
            new MiniStatement(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Transactions("");
    }
}
