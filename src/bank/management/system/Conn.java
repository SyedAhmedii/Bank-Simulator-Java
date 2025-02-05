package bank.management.system;
import java.sql.*;

public class Conn {
    Connection c; // Global variable banaya hai Connection class se taake har jagah istemaal hosake
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC Driver ko load karna
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "User_Name", "Password"); // Connection create karna
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

