package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date; // date sql package mai bhi hai or util package mai bhi tou java confuse hojata hai ke date kaha se uthaye isiliye yaha * nahi Date likhte hai

public class FastCash extends JFrame implements ActionListener {

    JButton deposit, withdrawl, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;

//constructor isiliye banate hai kyuke jaise he class ka object banaye tou constructor call hojaye
    FastCash(String pinnumber) {
        this.pinnumber = pinnumber; // particular transaction karne ke liye pinnumber chahiye hoga tabhi idher pinnumber pass kiya hai
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 760);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(245, 220, 200, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 13));
        image.add(text); // image ke upar chahiye tou image.add kiya 

        deposit = new JButton("Rs 500");
        deposit.setBounds(170, 337, 150, 30);
        deposit.setBackground(new Color(144, 238, 144));
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Rs 1000");
        withdrawl.setBounds(350, 337, 150, 30);
        withdrawl.setBackground(new Color(144, 238, 144));
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Rs 2000");
        fastcash.setBounds(170, 372, 150, 30);
        fastcash.setBackground(new Color(144, 238, 144));
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Rs 3000");
        ministatement.setBounds(350, 372, 150, 30);
        ministatement.setBackground(new Color(144, 238, 144));
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Rs 5000");
        pinchange.setBounds(170, 407, 150, 30);
        pinchange.setBackground(new Color(144, 238, 144));
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Rs 10,000");
        balanceenquiry.setBounds(170, 442, 150, 30);
        balanceenquiry.setBackground(new Color(144, 238, 144));
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("BACK");
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
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else {
            // ae.getSource button ka acess deraha hai ham button se text utha rahe hai substring isiliye lagaya hai taake shuru ke 3 characters khatam hojaye
            String amount = ((JButton)ae.getSource()).getText().substring(3);
            Conn c = new Conn();
            try{
                // Pehle check karna parega ke balance kitna hai apka actual uske ke liye query se data mangwaye gy or Resultset object mai store karegy
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0;
                while(rs.next()){
                    // ye loop aik aik karke rows compare karwata rahe ga
                    if(rs.getString("type").equals("Deposit")){
                        //Integer.parseInt isiliye lagaya hai taake string interger mai convert hojaye
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(ae.getSource() != exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Sucessfully");
                
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new FastCash("");
    }
}
