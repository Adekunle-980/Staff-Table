/*********************************
*  ITC – 5201 Database Programming Using Java – Assignment    4                                                 	         *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

* (including web sites) or distributed to other students/social media.                                                        *
                                                                                                                                                                             
*  Name: Pelumi Owoshagba 	Student ID: N01574587 Date: 11/22/2023
*  Name: Chioma Kamalu 		Student ID: N01600998 Date: 11/22/2023
*  Name: Adekunle Omonihi  	Student ID: N01511618 Date: 11/22/2023
*****/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
    private static final String USERNAME = "N01600998";
    private static final String PASSWORD = "oracle";

    static {
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error loading Oracle JDBC driver", e);
        }
    }

    public static Connection getConnection() {
        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
