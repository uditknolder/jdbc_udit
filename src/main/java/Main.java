
import javax.xml.transform.Result;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
            final String USER = "root";
            final String PASS = "Knoldus@2012";

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);


            System.out.println("1.Find the total amount to be paid at the time of checkout for a particular cart. As shown in above table. e.g. Query should return a single integer as total amount.");
            Statement stmt1 = con.createStatement();
            ResultSet r1 = stmt1.executeQuery("select sum(amount) as total from cart");
            while (r1.next())
                System.out.println("Total = " + r1.getString(1));

            System.out.println("\n\n");
            System.out.println("2. Find the product p_name which as sold the most.");
            Statement stmt2 = con.createStatement();
            ResultSet r2 = stmt2.executeQuery("select p_name from product where p_id=(select p_id from cart where qty=(select Max(qty) from cart));");
            while (r2.next())
                System.out.println("p_name with max qty = " + r2.getString(1));


            System.out.println("\n\n");
            System.out.println("3.Find all the products which are not yet sold.");
            Statement stmt3 = con.createStatement();
            ResultSet r3 = stmt3.executeQuery("select p_name from product where p_id NOT IN(Select p_id from cart)");
            while (r3.next())
                System.out.println(r3.getString(1));

            System.out.println("\n\n");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}