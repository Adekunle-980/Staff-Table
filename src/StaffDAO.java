/*********************************
*  ITC – 5201 Database Programming Using Java – Assignment    4                                                 	         *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

* (including web sites) or distributed to other students/social media.                                                        *
                                                                                                                                                                             
*  Name: Pelumi Owoshagba 	Student ID: N01574587 Date: 11/22/2023
*  Name: Chioma Kamalu 		Student ID: N01600998 Date: 11/22/2023
*  Name: Adekunle Omonihi  	Student ID: N01511618 Date: 11/22/2023
*****/
import java.sql.*;

public class StaffDAO {
    private Connection connection;

    public StaffDAO() {
        // Establish database connection
        connection = DatabaseConnector.getConnection();
        try {
            // Disable auto-commit
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error setting auto-commit to false", e);
        }
    }

    // Method to retrieve staff information based on ID

    public StaffRecord viewStaff(String id) {
        String query = "SELECT * FROM Staff WHERE TRIM(id) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id.trim());  // Trim the id before setting it in the prepared statement
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractStaffFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return null; // Return null if no record is found
    }



    // Method to insert staff information into the database
    public boolean insertStaff(StaffRecord staff) {
        String query = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, staff.getId().trim());;
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getMi());
            preparedStatement.setString(5, staff.getAddress());
            preparedStatement.setString(6, staff.getCity());
            preparedStatement.setString(7, staff.getState());
            preparedStatement.setString(8, staff.getTelephone());
            preparedStatement.setString(9, staff.getEmail());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record inserted successfully with ID: " + staff.getId());
                connection.commit(); // Commit the transaction
                return true;
            } else {
                System.out.println("Failed to insert record with ID: " + staff.getId());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            rollback(); // Rollback the transaction in case of an error
            return false;
        }
    }

    // Method to update staff information in the database
    public boolean updateStaff(StaffRecord staff) {
        String query = "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMi());
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getCity());
            preparedStatement.setString(6, staff.getState());
            preparedStatement.setString(7, staff.getTelephone());
            preparedStatement.setString(8, staff.getEmail());
            preparedStatement.setString(9, staff.getId().trim());

            preparedStatement.executeUpdate();
            connection.commit(); // Commit the transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(); // Rollback the transaction in case of an error
            return false;
        }
    }

    // Close the database connection when the DAO is no longer needed
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle closure errors
        }
    }

    // Helper method to extract staff information from a ResultSet
    private StaffRecord extractStaffFromResultSet(ResultSet resultSet) throws SQLException {
        StaffRecord staff = new StaffRecord();
        staff.setId(resultSet.getString("id"));
        staff.setLastName(resultSet.getString("lastName"));
        staff.setFirstName(resultSet.getString("firstName"));
        staff.setMi(resultSet.getString("mi"));
        staff.setAddress(resultSet.getString("address"));
        staff.setCity(resultSet.getString("city"));
        staff.setState(resultSet.getString("state"));
        staff.setTelephone(resultSet.getString("telephone"));
        staff.setEmail(resultSet.getString("email"));
        return staff;
    }

    // Rollback the transaction
    private void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle rollback errors
        }
    }
}
